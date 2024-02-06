package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.AbstractComposeView
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import net.osdn.ja.gokigen.wearos.timerapp.counter.CounterModel
import net.osdn.ja.gokigen.wearos.timerapp.presentation.theme.TimerAppTheme

class ViewRoot @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AbstractComposeView(context, attrs, defStyleAttr)
{
    private val appContext: Context
    init
    {
        this.appContext = context
    }
    @Composable
    override fun Content()
    {
        val navController = rememberSwipeDismissableNavController()
        val counterModel = remember { CounterModel(appContext) }

        TimerAppTheme {
                NavigationMain(appContext, navController, counterModel)
        }
        Log.v(TAG, " ... ViewRoot ...")
    }

    companion object
    {
        private val TAG = ViewRoot::class.java.simpleName
    }
}
