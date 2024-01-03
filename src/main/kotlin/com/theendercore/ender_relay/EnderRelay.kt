package com.theendercore.ender_relay

import eu.pb4.factorytools.api.item.FactoryBlockItem
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")
object EnderRelay {
    const val MODID = "ender_relay"

    @JvmField
    val LOGGER: Logger = LoggerFactory.getLogger(EnderRelay::class.simpleName)

    val ENDER_RELAY: EnderRelayBlock = Registry.register(
        Registries.BLOCK, id("ender_relay"), EnderRelayBlock(FabricBlockSettings.copyOf(Blocks.LODESTONE))
    )

    val ENDER_RELAY_ITEM: Item = Registry.register(
        Registries.ITEM, id("ender_relay"), FactoryBlockItem(ENDER_RELAY, Item.Settings())
    )

    fun commonInit() {
        LOGGER.info("The End is stirring")

        PolymerResourcePackUtils.addModAssets(MODID)
        PolymerResourcePackUtils.markAsRequired()
    }

    fun id(path: String) = Identifier(MODID, path)
}
