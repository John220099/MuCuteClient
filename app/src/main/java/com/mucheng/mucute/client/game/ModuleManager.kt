package com.mucheng.mucute.client.game


import android.content.Context
import android.net.Uri
import com.mucheng.mucute.client.application.AppContext
import com.mucheng.mucute.client.game.module.combat.*
import com.mucheng.mucute.client.game.module.effect.*
import com.mucheng.mucute.client.game.module.misc.*
import com.mucheng.mucute.client.game.module.visual.*
import com.mucheng.mucute.client.game.module.particle.*
import com.mucheng.mucute.client.game.module.motion.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import java.io.File

object ModuleManager {

    private val _modules: MutableList<Module> = ArrayList()

    val modules: List<Module> = _modules

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    init {
        with(_modules) {
            add(AntiDisconnectModule()) 
            add(CrasherModule()) 
            add(CrashModule())
            add(FlyModule())
            add(ESPModule())
            add(ZoomModule())
            add(AirJumpModule())
            add(NoClipModule())
            add(NightVisionModule())
            add(HasteModule())
            add(SpeedModule())
            add(JetPackModule())
            add(LevitationModule())
            add(HighJumpModule())
            add(SlowFallingModule())
            add(PoseidonModule())
            add(AntiKnockbackModule())
            add(RegenerationModule())
            add(BhopModule())
            add(SprintModule())
            add(NoHurtCameraModule())
            add(AutoWalkModule())
            add(AntiAFKModule())
            add(DesyncModule())
            add(PositionLoggerModule())
            add(MotionFlyModule())
            add(FreeCameraModule())
            add(KillauraModule())
            add(NauseaModule())
            add(HealthBoostModule())
            add(JumpBoostModule())
            add(ResistanceModule())
            add(FireResistanceModule())
            add(SwiftnessModule())
            add(InstantHealthModule())
            add(StrengthModule())
            add(InstantDamageModule())
            add(InvisibilityModule())
            add(SaturationModule())
            add(AbsorptionModule())
            add(BlindnessModule())
            add(AntiCrystalModule())
            add(CriticModule())
            add(HungerModule())
            add(WeaknessModule())
            add(PoisonModule())
            add(WitherModule())
            add(FatalPoisonModule())
            add(ConduitPowerModule())
            add(BadOmenModule())
            add(VillageHeroModule())
            add(DarknessModule())
            add(TimeShiftModule())
            add(WeatherControllerModule())
            add(FakeDeathModule())
            add(ExplosionParticleModule())
            add(BubbleParticleModule())
            add(HeartParticleModule())
            add(FakeXPModule())
            add(DustParticleModule())
            add(EyeOfEnderDeathParticleModule())
            add(FizzParticleModule())
            add(BreezeWindExplosionParticleModule())
            add(HitAndRunModule())
            add(HitboxModule())
            add(CrystalSmashModule())
            add(TriggerBotModule())
            add(NoChatModule())
            add(SpeedDisplayModule())
            add(PositionDisplayModule())
            add(CommandHandlerModule())
            add(NetworkInfoModule())
            add(MiningFatigueModule())
            add(WorldStateModule())
        }
    }

    fun saveConfig() {
        val configsDir = AppContext.instance.filesDir.resolve("configs")
        configsDir.mkdirs()

        val config = configsDir.resolve("UserConfig.json")
        val jsonObject = buildJsonObject {
            put("modules", buildJsonObject {
                _modules.forEach {
                    if (it.private) {
                        return@forEach
                    }
                    put(it.name, it.toJson())
                }
            })
        }

        config.writeText(json.encodeToString(jsonObject))
    }

    fun loadConfig() {
        val configsDir = AppContext.instance.filesDir.resolve("configs")
        configsDir.mkdirs()

        val config = configsDir.resolve("UserConfig.json")
        if (!config.exists()) {
            return
        }

        val jsonString = config.readText()
        if (jsonString.isEmpty()) {
            return
        }

        val jsonObject = json.parseToJsonElement(jsonString).jsonObject
        val modules = jsonObject["modules"]!!.jsonObject
        _modules.forEach { module ->
            (modules[module.name] as? JsonObject)?.let {
                module.fromJson(it)
            }
        }
    }

    fun exportConfig(): String {
        val jsonObject = buildJsonObject {
            put("modules", buildJsonObject {
                _modules.forEach {
                    if (it.private) {
                        return@forEach
                    }
                    put(it.name, it.toJson())
                }
            })
        }
        return json.encodeToString(jsonObject)
    }

    fun importConfig(configStr: String) {
        try {
            val jsonObject = json.parseToJsonElement(configStr).jsonObject
            val modules = jsonObject["modules"]?.jsonObject ?: return

            _modules.forEach { module ->
                modules[module.name]?.let {
                    if (it is JsonObject) {
                        module.fromJson(it)
                    }
                }
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid config format")
        }
    }

    fun exportConfigToFile(context: Context, fileName: String): Boolean {
        return try {
            val configsDir = context.getExternalFilesDir("configs")
            configsDir?.mkdirs()

            val configFile = File(configsDir, "$fileName.json")
            configFile.writeText(exportConfig())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun importConfigFromFile(context: Context, uri: Uri): Boolean {
        return try {
            context.contentResolver.openInputStream(uri)?.use { input ->
                val configStr = input.bufferedReader().readText()
                importConfig(configStr)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}
