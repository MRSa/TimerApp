package net.osdn.ja.gokigen.wearos.timerapp.counter

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf

class CounterModel : ICounterStatus
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

    companion object
    {
        private val TAG = CounterModel::class.java.simpleName
    }
}
