package kurpenov.kuat.marvelapp.screens.selectPersonScreen.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundElement(color: Color, modifier: Modifier = Modifier) {
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    val proportion = 0.5f

    Canvas(
        modifier = modifier,
    ) {
                                ///////////////////
                                //            A  //
                                //            |  // this triangle
                                //        B - c  //
                                ///////////////////

        val path = Path()
        path.moveTo(width.dp.toPx(), height.dp.toPx()*proportion) // A
        path.lineTo(0f, height.dp.toPx())                    // B
        path.lineTo(width.dp.toPx(), height.dp.toPx())          // C
        path.lineTo(width.dp.toPx(), height.dp.toPx()*proportion) // A
        path.close()
        drawPath(path = path, color = color)
    }
}
