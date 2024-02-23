package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ButtonDefaults
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.material.Button
import net.osdn.ja.gokigen.wearos.timerapp.R
import net.osdn.ja.gokigen.wearos.timerapp.counter.ICounterStatus

///////////////////////////////////////////////////
//  サブのボタンエリアの描画＆制御
//   (JoggingTimer互換のボタン)
///////////////////////////////////////////////////

// ----- use horologist-compose-material Button
//   https://stackoverflow.com/questions/78034795/how-to-handle-a-long-click-event-on-wear-compose

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun BtnSubStop(context: Context, navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStop")

    // ストップ状態時のボタン
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
            id = R.drawable.baseline_list_24,
            contentDescription = "List",
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            onClick = { navController.navigate("RecordListScreen") },
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
            )

        ////////////////////  カウンターのスタート  ////////////////////
        Button(
            id = R.drawable.baseline_play_arrow_24,
            contentDescription = "Start",
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            onClick = { counterStatus.start() },
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun BtnSubStart(context: Context, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnStart")

    // スタート状態時のボタン
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ////////////////////  カウンターのストップ(長押しの研究...)  ////////////////////
        Button(
            id = R.drawable.baseline_stop_24,
            contentDescription = "Stop",
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            onClick = {
                // UIスレッドで実行が必要、ボタンは長押しで止まることをToastで知らせる
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.long_press_to_stop),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
            onLongClick = {
                Log.v("STOP", "STOP: onLongClick (2)")
                counterStatus.stop()
            },
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )

        ////////////////////  タイムスタンプ記録  ////////////////////
        Button(
            id = R.drawable.baseline_flag_24,
            contentDescription = "Lap",
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            onClick = { counterStatus.timeStamp() },
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun BtnSubFinished(context: Context, navController: NavHostController, counterStatus: ICounterStatus)
{
    Log.v("BTN", "btnFinish")

    // カウントストップ時のボタン
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ////////////////////  記録一覧画面へ遷移  ////////////////////
        Button(
            id = R.drawable.baseline_list_24,
            contentDescription = "List",
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            onClick = { navController.navigate("RecordListScreen") },
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )

        ////////////////////  カウンターのリセット  ////////////////////
        Button(
            id = R.drawable.baseline_refresh_24,
            contentDescription = "Reset",
            onClick = { counterStatus.reset() },
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )

        ////////////////////  カウンターのスタート  ////////////////////
        Button(
            id = R.drawable.baseline_play_arrow_24,
            contentDescription = "Start",
            onClick = { counterStatus.start() },
            colors = ButtonDefaults.primaryButtonColors(
                backgroundColor =  Color.Black,
                contentColor = Color.LightGray
            ),
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .padding(2.dp)
                .background(color = Color.Black),
            enabled = true,
        )
    }
}
