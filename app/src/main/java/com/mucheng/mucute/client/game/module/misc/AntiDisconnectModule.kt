package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.InterceptablePacket
import com.mucheng.mucute.client.game.Module
import com.mucheng.mucute.client.game.ModuleCategory
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket

class AntiDisconnectModule : Module("AntiDisconnect", ModuleCategory.Misc) {

    override fun beforePacketBound(interceptablePacket: InterceptablePacket) { 
        if (!isEnabled) {
            return
        }

        val packet = interceptablePacket.packet
        if (packet is PlayerAuthInputPacket) {
            if (packet is DisconnectPacket) {
                interceptablePacket.intercept()
                return
            }
        }
    }
}
