package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.InterceptablePacket
import com.mucheng.mucute.client.game.Module
import com.mucheng.mucute.client.game.ModuleCategory
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket
import org.cloudburstmc.protocol.bedrock.packet.TransferPacket

class AntiDisconnectModule : Module("AntiDisconnect", ModuleCategory.Misc) {

    private val BlockDisconnectPacket by boolValue("Block_DisconnectPacket", true)
    private val BlockTransferPacket by boolValue("Block_TransferPacket", false)

    override fun beforePacketBound(interceptablePacket: InterceptablePacket): Boolean { 
        if (!isEnabled) {
            return
        }
        val packet = interceptablePacket.packet
            if (packet is DisconnectPacket) {
            if (BlockDisconnectPacket) {
                interceptablePacket.intercept()
                return false
            }
        }
            if (packet is TransferPacket) {
            if (BlockTransferPacket) {
                interceptablePacket.intercept()
                return false
            }
        }
    }
}
