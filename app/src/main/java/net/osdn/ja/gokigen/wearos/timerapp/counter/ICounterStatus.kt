package net.osdn.ja.gokigen.wearos.timerapp.counter

interface ICounterStatus
{
    fun start()
    fun stop()
    fun timeStamp()
    fun reset()
    fun toggleShowCounter()
    fun isShowCounterTotal() : Boolean
}