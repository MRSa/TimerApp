package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import net.osdn.ja.gokigen.wearos.timerapp.R
import net.osdn.ja.gokigen.wearos.timerapp.counter.ICounterStatus

///////////////////////////////////////////////////
//  メインのボタンエリアの描画＆制御
//   (JoggingTimer互換のボタン)
///////////////////////////////////////////////////

@Composable
fun BtnStop(navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStop")

    // ストップ状態時のボタン
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        IconButton(
            onClick = { navController.navigate("RecordListScreen") },
            enabled = true,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_list_24),
                contentDescription = "List",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンター表示の切り替え  ////////////////////
        IconButton(
            onClick = { counterStatus.toggleShowCounter() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sharp_swap_vert_24),
                contentDescription = "ChangeScreen",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンターのスタート  ////////////////////
        IconButton(
            onClick = { counterStatus.start() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                contentDescription = "Start",
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun BtnStart(counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStart")

    // スタート状態時のボタン
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ////////////////////  カウンターのストップ  ////////////////////
        IconButton(
            onClick = { counterStatus.stop() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_stop_24),
                contentDescription = "Stop",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンター表示の切り替え  ////////////////////
        IconButton(
            onClick = { counterStatus.toggleShowCounter() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sharp_swap_vert_24),
                contentDescription = "ChangeScreen",
                tint = Color.LightGray
            )
        }

        ////////////////////  タイムスタンプ記録  ////////////////////
        IconButton(
            onClick = { counterStatus.timeStamp() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_flag_24),
                contentDescription = "Lap",
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun BtnFinished(navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnFinish")

    // カウントストップ時のボタン
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        IconButton(
            onClick = { navController.navigate("RecordListScreen") },
            enabled = true,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_list_24),
                contentDescription = "List",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンターのリセット  ////////////////////
        IconButton(
            onClick = { counterStatus.reset() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_refresh_24),
                contentDescription = "Reset",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンターのスタート  ////////////////////
        IconButton(
            onClick = { counterStatus.start() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                contentDescription = "Start",
                tint = Color.LightGray
            )
        }
    }
}
