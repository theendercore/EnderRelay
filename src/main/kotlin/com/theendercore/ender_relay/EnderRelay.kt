package com.theendercore.ender_relay

import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

@Suppress("unused")
object EnderRelay {
    const val MODID = "ender_relay"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(EnderRelay::class.simpleName)

    fun commonInit() {
        LOGGER.info("Hello from Common")
    }

    fun id(path: String) = Identifier(MODID, path)
}
