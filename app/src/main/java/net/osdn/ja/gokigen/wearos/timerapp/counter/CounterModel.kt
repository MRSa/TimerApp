package net.osdn.ja.gokigen.wearos.timerapp.counter

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.wear.ongoing.OngoingActivity
import androidx.wear.ongoing.Status
import net.osdn.ja.gokigen.wearos.timerapp.INotifyLauncher
import net.osdn.ja.gokigen.wearos.timerapp.MainActivity
import net.osdn.ja.gokigen.wearos.timerapp.MainActivity.Companion.CHANNEL_ID
import net.osdn.ja.gokigen.wearos.timerapp.R

class CounterModel(private val context: Context) : ICounterStatus, INotifyLauncher
{
    var buttonStatus = mutableStateOf(CounterStatus.STOP)
        private set
    var counter = mutableLongStateOf(0)
        private set
    var lapCount = mutableIntStateOf(0)
        private set
    var showCounterTotal = mutableStateOf(false)
        private set
    var startTime = 0L
        private set
    var lastLapTime = 0L
        private set
    var stopTime = 0L
        private set

    private fun timerCounter()
    {
        try
        {
            //Log.v(TAG, "timerCounter() : Start")
            val thread = Thread {
                while (buttonStatus.value == CounterStatus.START)
                {
                    counter.longValue = System.currentTimeMillis()
                    try
                    {
                        Thread.sleep(100)
                    }
                    catch (e: Exception)
                    {
                        e.printStackTrace()
                    }
                }
            }
            thread.start()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun start()
    {
        try
        {
            buttonStatus.value = CounterStatus.START
            startTime = System.currentTimeMillis()
            (lapCount.intValue)++
            counter.longValue = startTime
            timerCounter()
            launchNotify(R.drawable.baseline_directions_run_24,
                context.getString(R.string.app_name),
                context.getString(R.string.notification_description),
                true
            )
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun stop()
    {
        try
        {
            buttonStatus.value = CounterStatus.FINISHED
            stopTime = System.currentTimeMillis()

            launchNotify(R.drawable.baseline_directions_run_24,
                context.getString(R.string.app_name),
                context.getString(R.string.notification_description),
                false
            )
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun timeStamp()
    {
        try
        {
            buttonStatus.value = CounterStatus.START
            (lapCount.intValue)++
            lastLapTime = System.currentTimeMillis()
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun reset()
    {
        try
        {
            buttonStatus.value = CounterStatus.STOP
            lapCount.intValue = 0
            startTime = 0
            stopTime = 0
            lastLapTime = 0
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    override fun toggleShowCounter()
    {
        showCounterTotal.value = !(showCounterTotal.value)
        Log.v(TAG, "showCounterTotal : ${showCounterTotal.value}")
    }

    override fun isShowCounterTotal(): Boolean
    {
        return (showCounterTotal.value)
    }

    override fun launchNotify(icon: Int, title: String, description: String, isShow: Boolean)
    {
        try
        {
            Log.v(TAG, "launchNotify(${title} : ${description})")
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true)

            val ongoingActivityStatus = Status.Builder()
                .addTemplate(onGoingTextTemplate())
                .build()

            val intent = Intent(context, MainActivity::class.java)

            val activityPendingIntent = PendingIntent
                .getActivity(context, 0, intent, (PendingIntent.FLAG_UPDATE_CURRENT) or (PendingIntent.FLAG_IMMUTABLE))

            val ongoingActivity =
                OngoingActivity.Builder(
                    context, R.string.app_name, builder
                )
                    .setAnimatedIcon(R.drawable.baseline_directions_run_24)
                    .setStaticIcon(R.drawable.baseline_directions_run_24)
                    .setTouchIntent(activityPendingIntent)
                    .setStatus(ongoingActivityStatus)
                    .build()

            ongoingActivity.apply(context)

            val notificationManager = NotificationManagerCompat.from(context)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.v(TAG, " Permission denied to notify.")
                //eActivityCompat.requestPermissions()
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }

            if (isShow)
            {
                // Notificationの発報
                notificationManager.notify(R.string.app_name, builder.build())
            }
            else
            {
                // Notificationの解除
                notificationManager.cancel(R.string.app_name)
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
    }

    private fun onGoingTextTemplate() : String
    {
        return (context.getString(R.string.notification_description))
    }


    companion object
    {
        private val TAG = CounterModel::class.java.simpleName
    }
}
