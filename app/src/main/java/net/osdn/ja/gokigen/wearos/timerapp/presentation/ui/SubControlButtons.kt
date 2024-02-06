package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import net.osdn.ja.gokigen.wearos.timerapp.R
import net.osdn.ja.gokigen.wearos.timerapp.counter.ICounterStatus


///////////////////////////////////////////////////
//  サブのボタンエリアの描画＆制御
//   (JoggingTimer互換のボタン)
///////////////////////////////////////////////////

@Composable
fun BtnSubStop(navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStop")

    // ストップ状態時のボタン
    Row() {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
            onClick = { navController.navigate("RecordListScreen") },
            enabled = true,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_list_24),
                contentDescription = "List",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンターのスタート  ////////////////////
        Button(
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
fun BtnSubStart(counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStart")

    // スタート状態時のボタン
    Row() {
        ////////////////////  カウンターのストップ  ////////////////////
        Button(
            onClick = { counterStatus.stop() },
            enabled = true
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_stop_24),
                contentDescription = "Stop",
                tint = Color.LightGray
            )
        }

        ////////////////////  タイムスタンプ記録  ////////////////////
        Button(
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
fun BtnSubFinished(navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnFinish")

    // カウントストップ時のボタン
    Row() {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
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
        Button(
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
        Button(
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
