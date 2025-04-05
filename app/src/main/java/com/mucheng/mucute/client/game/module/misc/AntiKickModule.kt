package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.InterceptablePacket
import com.mucheng.mucute.client.game.Module
import com.mucheng.mucute.client.game.ModuleCategory
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket
import org.cloudburstmc.protocol.bedrock.data.DisconnectFailReason

class AntiKickModule : Module("AntiKick", ModuleCategory.Misc) {

override fun afterPacketBound(packet: BedrockPacket) {
    if (!isEnabled) {
      return
    }
    
    val packet = BedrockPacket
    
    if (packet is DisconnectPacket && DisconnectFailReason) {
        return 
        }         
    } 
}
