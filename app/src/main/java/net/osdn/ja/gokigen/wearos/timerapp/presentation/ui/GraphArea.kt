package net.osdn.ja.gokigen.wearos.timerapp.presentation.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import net.osdn.ja.gokigen.wearos.timerapp.R

@Composable
fun GraphArea(navController: NavHostController, modifier: Modifier = Modifier)
{
    BoxWithConstraints(modifier = modifier) {
        // x,y軸の描画エリアを設定する
        val width = with(LocalDensity.current) { maxWidth.toPx() }
        val height = with(LocalDensity.current) { maxHeight.toPx() }
        val areaRect = Rect(left = 0f, top = 0f, right = width, bottom = height)

        Canvas(Modifier.fillMaxSize()) { // this: DrawScope
            // x,y軸を描画する
            drawAxis(area = areaRect)
        }
    }
}

// x,y軸を描画する
private fun DrawScope.drawAxis(area: Rect) {
    Log.w("AAA", "XXXX (${area.left},${area.top})-(${area.right},${area.bottom})")
    drawRect(
        color = Color.Blue,
        size = Size(width = (area.right - area.left), height = (area.bottom - area.top))
    )

    drawLine(
        color = Color.Yellow,
        start = Offset(area.left, area.top),
        end = Offset(area.left, area.bottom)
    )
    drawLine(
        color = Color.Yellow,
        start = Offset(area.left, area.bottom),
        end = Offset(area.right, area.bottom)
    )
    drawLine(
        color = Color.Yellow,
        start = Offset(area.left, area.top),
        end = Offset(area.right, area.top)
    )
    drawLine(
        color = Color.Yellow,
        start = Offset(area.left, (area.bottom - area.top) /2.0f),
        end = Offset(area.right, (area.bottom - area.top) /2.0f)
    )
    drawLine(
        color = Color.Yellow,
        start = Offset(area.right, area.top),
        end = Offset(area.right, area.bottom)
    )
}