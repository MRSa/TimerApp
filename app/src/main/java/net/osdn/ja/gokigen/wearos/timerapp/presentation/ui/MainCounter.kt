package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import net.osdn.ja.gokigen.wearos.timerapp.counter.CounterModel
import net.osdn.ja.gokigen.wearos.timerapp.counter.CounterStatus
import net.osdn.ja.gokigen.wearos.timerapp.counter.TimeStringConvert

@Composable
fun MainCounter(counterManager: CounterModel)
{
    val totalTimeValue = counterManager.counter.longValue - counterManager.startTime
    val lapTimeValue = counterManager.counter.longValue - if (counterManager.lastLapTime <= 0 ) { counterManager.startTime } else { counterManager.lastLapTime }
    val finishTimeValue = counterManager.stopTime - counterManager.startTime

    val timeString = if (counterManager.isShowCounterTotal()) {
        // メインカウンタはトータル時間を表示する
        when (counterManager.buttonStatus.value) {
            CounterStatus.START -> {
                // 実行中
                TimeStringConvert.getTimeString(totalTimeValue)
            }
            CounterStatus.STOP -> {
                // 開始前
                TimeStringConvert.getTimeString(0)
            }
            CounterStatus.FINISHED -> {
                // カウント終了(結果表示)
                TimeStringConvert.getTimeString(finishTimeValue)
            }
        }
    }
    else
    {
        // メインカウンタは、ラップタイムを表示する
        when (counterManager.buttonStatus.value) {
            CounterStatus.START -> {
                // 実行中
                "[${counterManager.lapCount.intValue}] ${TimeStringConvert.getTimeString(lapTimeValue)}"
            }
            CounterStatus.STOP -> {
                // 開始前
                TimeStringConvert.getTimeString(0)
            }
            CounterStatus.FINISHED -> {
                // カウント終了
                "[${counterManager.lapCount.intValue}] ${TimeStringConvert.getTimeString(lapTimeValue)}"
            }
        }
    }

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = Color.White, // MaterialTheme.colors.primary,
        fontSize = 18.sp,
        text = timeString.toString()
    )

/*
    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        //textAlign = TextAlign.Center,
        //color = Color.White, // MaterialTheme.colors.primary,
        //fontSize = 18.sp,
        text = AnnotatedString(timeValue.toString()),
        style = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
        ),
        onClick = { counterManager.toggleShowCounter() }
    )
*/
/*
    Row(
        verticalAlignment = Alignment.Bottom
    )
    {
        Text(
            modifier = Modifier
                .padding(start = 6.dp)
                .weight(1.0f),
            textAlign = TextAlign.End,
            color = Color.White, // MaterialTheme.colors.primary,
            text = "[${counterManager.lapCount.intValue}]",
            fontSize = 14.sp,
        )
        Text(
            modifier = Modifier
                .weight(3.0f),
            textAlign = TextAlign.Center,
            color = Color.White, // MaterialTheme.colors.primary,
            text = "$timeValue",
            fontSize = 18.sp,
        )
    }
*/
}
