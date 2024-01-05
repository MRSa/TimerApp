package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import net.osdn.ja.gokigen.wearos.timerapp.counter.CounterModel
import net.osdn.ja.gokigen.wearos.timerapp.presentation.theme.TimerAppTheme
import net.osdn.ja.gokigen.wearos.timerapp.presentation.ui.MainScreen

@Composable
fun NavigationMain(context: Context, navController: NavHostController, counterModel: CounterModel)
{
    // ----- 画面の切り替え(ナビゲーション)部分 ------
    TimerAppTheme {
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "MainScreen" // "PreferenceScreen"
        ) {
            composable(
                route = "MainScreen"
            ) {
                // メイン画面
                MainScreen(navController = navController, counterManager = counterModel)
            }
            composable(
                route = "RecordListScreen"
            ) {
                // 記録一覧画面
                backStackEntry ->
                    RecordListScreen(navController = navController)
            }
            composable(
                route = "PreferenceScreen"
            ) {
                // 設定画面
                backStackEntry ->
                    PreferenceScreen(navController = navController)
            }
            composable(
                route = "DetailRecordScreen/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType }
                )
            ) {
                // 記録詳細画面
                backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                DetailRecordScreen(context = context, navController = navController, id = id)
            }
        }
    }
}
