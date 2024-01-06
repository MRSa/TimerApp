package net.osdn.ja.gokigen.wearos.timerapp

interface INotifyLauncher
{
    fun launchNotify(icon: Int, title: String, description: String, isShow: Boolean)
}
