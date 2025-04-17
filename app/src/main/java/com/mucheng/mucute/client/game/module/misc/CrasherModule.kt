package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.InterceptablePacket
import com.mucheng.mucute.client.game.Module
import com.mucheng.mucute.client.game.ModuleCategory
import org.cloudburstmc.math.vector.Vector3i
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket

class CrasherModule : Module("crash", ModuleCategory.Misc) {
    override fun beforePacketBound(interceptablePacket: InterceptablePacket) {
        if (!isEnabled) {
            return
        } else {
            val subChunkRequestPacket = SubChunkRequestPacket().apply {
                dimension = 0
                subChunkPosition = Vector3i.ZERO
                positionOffsets = Array(3000000) { Vector3i.ZERO }.toMutableList()
            }

            session.clientBound(subChunkRequestPacket)

        }
    }
}