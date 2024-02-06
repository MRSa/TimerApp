package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon
import net.osdn.ja.gokigen.wearos.timerapp.R
import net.osdn.ja.gokigen.wearos.timerapp.counter.ICounterStatus

///////////////////////////////////////////////////
//  サブのボタンエリアの描画＆制御
//   (JoggingTimer互換のボタン)
///////////////////////////////////////////////////

@Composable
fun BtnSubStop(context: Context, navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStop")

    // ストップ状態時のボタン
    Row() {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { navController.navigate("RecordListScreen") },
            enabled = true,
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_list_24),
                contentDescription = "List",
                tint = Color.LightGray
            )
        }

        ////////////////////  カウンターのスタート  ////////////////////
        Button(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = { counterStatus.start() },
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BtnSubStart(context: Context, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStart")

    // スタート状態時のボタン
    Row() {
        ////////////////////  カウンターのストップ(長押しの研究...)  ////////////////////
        Button(
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black)
                .combinedClickable(
                    enabled = true,
                    onClick = {
                        // UIスレッドで実行が必要、ボタンは長押しで止まることをToastで知らせる
                        Toast.makeText(
                            context,
                            context.getString(R.string.long_press_to_stop),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onLongClick = {
                        Log.v("STOP", "STOP: onLongClick (2)")
                        counterStatus.stop()
                    }
                ),
            shape = RoundedCornerShape(10.dp),
            // onClick = { counterStatus.stop() },
            onClick = {
                Log.v("STOP", " --- onClick(Sub) ---")
            },
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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
fun BtnSubFinished(context: Context, navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnFinish")

    // カウントストップ時のボタン
    Row() {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
            onClick = { navController.navigate("RecordListScreen") },
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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
            colors = ButtonDefaults.primaryButtonColors(backgroundColor =  Color.Black),
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
