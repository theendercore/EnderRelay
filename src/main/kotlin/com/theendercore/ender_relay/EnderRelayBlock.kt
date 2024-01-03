package com.theendercore.ender_relay

import com.mojang.serialization.MapCodec
import eu.pb4.factorytools.api.block.FactoryBlock
import eu.pb4.factorytools.api.virtualentity.BaseModel
import eu.pb4.factorytools.api.virtualentity.LodItemDisplayElement
import eu.pb4.polymer.virtualentity.api.ElementHolder
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.BlockWithEntity
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.CompassItem
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.joml.Vector3f


class EnderRelayBlock(settings: Settings) : Block(settings), FactoryBlock {
    override fun onUse(
        state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val sPlayer = player as ServerPlayerEntity
            val stack = sPlayer.getStackInHand(hand)
            if (stack.item is CompassItem && stack.hasNbt()) {
                sPlayer.sendSystemMessage(Text.literal("Hello there special compass user"))
            }
        }
        return super.onUse(state, world, pos, player, hand, hit)
    }

    override fun getPolymerBlock(state: BlockState): Block = Blocks.BARRIER
    override fun getCodec(): MapCodec<out BlockWithEntity>? = null

    override fun createElementHolder(world: ServerWorld, pos: BlockPos, initialBlockState: BlockState): ElementHolder =
        Model(initialBlockState)


    class Model(state: BlockState) : BaseModel() {
        private val main: LodItemDisplayElement = LodItemDisplayElement.createSimple(state.block.asItem())

        init {
            main.setDisplaySize(1f, 1f)
            main.scale = Vector3f(2f)
            this.addElement(this.main)
        }
    }
}