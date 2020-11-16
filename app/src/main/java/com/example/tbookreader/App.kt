package com.example.tbookreader

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.example.tbookreader.constant.AppConst.channelIdDownload
import com.example.tbookreader.constant.AppConst.channelIdReadAloud
import com.example.tbookreader.constant.AppConst.channelIdWeb
import com.example.tbookreader.constant.PreferKey
import com.example.tbookreader.data.AppDatabase
import com.example.tbookreader.help.ActivityHelp
import com.example.tbookreader.help.AppConfig
import com.example.tbookreader.help.CrashHandler
import com.example.tbookreader.help.ReadBookConfig
import com.example.tbookreader.lib.theme.ColorUtils
import com.example.tbookreader.lib.theme.ThemeStore
import com.example.tbookreader.utils.getCompatColor
import com.example.tbookreader.utils.getPrefInt
import com.example.tbookreader.utils.putPrefInt
import com.jeremyliao.liveeventbus.LiveEventBus

@Suppress("DEPRECATION")
class App : MultiDexApplication() {

    companion object {
        @JvmStatic
        lateinit var INSTANCE: App
            private set

        @JvmStatic
        lateinit var db: AppDatabase
            private set
    }

    var versionCode = 0
    var versionName = ""

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        CrashHandler().init(this)
        db = AppDatabase.createDatabase(INSTANCE)
        packageManager.getPackageInfo(packageName, 0)?.let {
            versionCode = it.versionCode
            versionName = it.versionName
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createChannelId()
        applyDayNight()
        LiveEventBus
                .config()
                .supportBroadcast(this)
                .lifecycleObserverAlwaysActive(true)
                .autoClear(false)

        registerActivityLifecycleCallbacks(ActivityHelp)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES, Configuration.UI_MODE_NIGHT_NO -> applyDayNight()
        }
    }

    /*更新主题*/
    fun applyTheme() {
        when {
            AppConfig.isEInkMode -> {
                ThemeStore.editTheme(this)
                        .coloredNavigationBar(true)
                        .primaryColor(Color.WHITE)
                        .accentColor(Color.BLACK)
                        .backgroundColor(Color.WHITE)
                        .bottomBackground(Color.WHITE)
                        .apply()
            }
            AppConfig.isNightTheme -> {
                val primary =
                        getPrefInt(PreferKey.cNPrimary, getCompatColor(R.color.md_blue_grey_600))
                val accent =
                        getPrefInt(PreferKey.cNAccent, getCompatColor(R.color.md_deep_orange_800))
                var background =
                        getPrefInt(PreferKey.cNBackground, getCompatColor(R.color.md_grey_900))
                if (ColorUtils.isColorLight(background)) {
                    background = getCompatColor(R.color.md_grey_900)
                    putPrefInt(PreferKey.cNBackground, background)
                }
                var bBackground =
                        getPrefInt(PreferKey.cNBBackground, getCompatColor(R.color.md_grey_850))
                if (ColorUtils.isColorLight(bBackground)) {
                    bBackground = getCompatColor(R.color.md_grey_850)
                    putPrefInt(PreferKey.cNBBackground, bBackground)
                }
                ThemeStore.editTheme(this)
                        .coloredNavigationBar(true)
                        .primaryColor(ColorUtils.withAlpha(primary, 1f))
                        .accentColor(ColorUtils.withAlpha(accent, 1f))
                        .backgroundColor(ColorUtils.withAlpha(background, 1f))
                        .bottomBackground(ColorUtils.withAlpha(bBackground, 1f))
                        .apply()
            }
            else -> {
                val primary =
                        getPrefInt(PreferKey.cPrimary, getCompatColor(R.color.md_indigo_800))
                val accent =
                        getPrefInt(PreferKey.cAccent, getCompatColor(R.color.md_red_600))
                var background =
                        getPrefInt(PreferKey.cBackground, getCompatColor(R.color.md_grey_100))
                if (!ColorUtils.isColorLight(background)) {
                    background = getCompatColor(R.color.md_grey_100)
                    putPrefInt(PreferKey.cBackground, background)
                }
                var bBackground =
                        getPrefInt(PreferKey.cBBackground, getCompatColor(R.color.md_grey_200))
                if (!ColorUtils.isColorLight(bBackground)) {
                    bBackground = getCompatColor(R.color.md_grey_200)
                    putPrefInt(PreferKey.cBBackground, bBackground)
                }
                ThemeStore.editTheme(this)
                        .coloredNavigationBar(true)
                        .primaryColor(ColorUtils.withAlpha(primary, 1f))
                        .accentColor(ColorUtils.withAlpha(accent, 1f))
                        .backgroundColor(ColorUtils.withAlpha(background, 1f))
                        .bottomBackground(ColorUtils.withAlpha(bBackground, 1f))
                        .apply()
            }
        }
    }

    fun applyDayNight() {
        ReadBookConfig.upBg()
    }

    /*初始化夜间模式*/
    private fun initNightMode() {
        val targetMode =
                if (AppConfig.isNightTheme) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
        AppCompatDelegate.setDefaultNightMode(targetMode)
    }

    /*创建通知ID*/
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannelId() {
        (getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager)?.let {
            //用唯一的ID创建渠道对象
            val downloadChannel = NotificationChannel(
                    channelIdDownload,
                    "Offline download",
                    NotificationManager.IMPORTANCE_LOW
            )
//            初始化channel
            downloadChannel.enableLights(false)
            downloadChannel.enableVibration(false)
            downloadChannel.setSound(null, null)
//            用唯一的ID创建渠道对象
            val readAloudChannel = NotificationChannel(
                    channelIdReadAloud,
                    "Speech",
                    NotificationManager.IMPORTANCE_LOW
            )

            readAloudChannel.enableLights(false)
            readAloudChannel.enableVibration(false)
            readAloudChannel.setSound(null, null)

            val webChannel = NotificationChannel(
                    channelIdWeb,
                    "Web service",
                    NotificationManager.IMPORTANCE_LOW
            )
            webChannel.enableLights(false)
            webChannel.enableVibration(false)
            webChannel.setSound(null, null)

            //向notification manager提交channel
            it.createNotificationChannels(listOf(downloadChannel, readAloudChannel, webChannel))
        }
    }
}