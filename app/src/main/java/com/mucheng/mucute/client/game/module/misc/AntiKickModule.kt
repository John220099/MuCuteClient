package com.mucheng.mucute.client.game.module.misc

import com.mucheng.mucute.client.game.InterceptablePacket
import com.mucheng.mucute.client.game.Module
import com.mucheng.mucute.client.game.ModuleCategory
import org.cloudburstmc.protocol.bedrock.packet.DisconnectPacket

class AntiKickModule : Module("AntiKick", ModuleCategory.Misc) {

override fun beforeClientBound(interceptablePacket: InterceptablePacket) {
    if (!isEnabled) {
      return
    }        
    val packet = interceptablePacket.packet    
    
    if (packet is DisconnectPacket) {
        return 
        }         
    } 
}
