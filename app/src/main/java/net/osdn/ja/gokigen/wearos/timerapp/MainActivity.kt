package net.osdn.ja.gokigen.wearos.timerapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import net.osdn.ja.gokigen.wearos.timerapp.presentation.ui.ViewRoot
import java.io.File

class MainActivity : ComponentActivity()
{
    private lateinit var rootComponent : ViewRoot

    override fun onCreate(savedInstanceState: Bundle?)
    {
        try
        {
            ///////// SHOW SPLASH SCREEN : call before super.onCreate() /////////
            installSplashScreen()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        try
        {
            ///////// SET PERMISSIONS /////////
            if (!allPermissionsGranted())
            {
                val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                    if(!allPermissionsGranted())
                    {
                        // Abort launch application because required permissions was rejected.
                        Toast.makeText(this, getString(R.string.permission_not_granted), Toast.LENGTH_SHORT).show()
                        outputDebugLog("----- APPLICATION LAUNCH ABORTED -----")
                        finish()
                    }
                }
                requestPermission.launch(REQUIRED_PERMISSIONS)
            }
            else
            {
                setupEnvironments()
                createNotificationChannel()
            }
            rootComponent = ViewRoot(applicationContext)
            setContent {
                rootComponent.Content()
            }
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupEnvironments()
    {
        val thread = Thread {
            try
            {
                val subDirectory = File(filesDir, CACHE_DIR)
                if (!subDirectory.exists())
                {
                    if (!subDirectory.mkdirs())
                    {
                        outputDebugLog("----- Sub Directory $CACHE_DIR create failure...")
                    }
                }
            }
            catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
        try
        {
            thread.start()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    private fun createNotificationChannel()
    {
        try
        {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    private fun outputDebugLog(data: String)
    {
        if (isDebugLog)
        {
            runOnUiThread {
                Log.v(TAG, data)
            }
        }
    }

    companion object
    {
        private val TAG = MainActivity::class.java.simpleName
        private const val isDebugLog = true

        const val CHANNEL_ID = "net.osdn.ja.gokigen.joggingtimer"
        //private const val MAIN_TEXT = "JoggingTimer"

        private const val CACHE_DIR : String = "/caches/"
        private val REQUIRED_PERMISSIONS =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Wear OS 4 以上
                arrayOf(
                    Manifest.permission.VIBRATE,
                    Manifest.permission.WAKE_LOCK,
                    Manifest.permission.POST_NOTIFICATIONS,
                )
            }
            else
            {
                // Wear OS 3 まで
                arrayOf(
                    Manifest.permission.VIBRATE,
                    Manifest.permission.WAKE_LOCK,
                )
            }
    }
}
