package com.example.pos_mobile.core.designsystem.components.text

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.core.designsystem.theme.AppTheme

@Composable
fun JumpingDotsText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    fontWeight: FontWeight? = null,
    color: Color = Color.Unspecified,
    jumpHeight: Dp = 5.dp
) {
    val cleanText = text.trimEnd('.', '…')
    val infiniteTransition = rememberInfiniteTransition(label = "JumpingDotsTransition")
    val jumpHeightPx = with(LocalDensity.current) { jumpHeight.toPx() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = cleanText,
            style = style,
            fontWeight = fontWeight,
            color = color
        )

        repeat(3) { index ->
            val offsetY by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0f at 0 using FastOutSlowInEasing
                        (-jumpHeightPx) at 200 using FastOutSlowInEasing
                        0f at 400 using FastOutSlowInEasing
                        0f at 1200
                    },
                    repeatMode = RepeatMode.Restart,
                    initialStartOffset = StartOffset(index * 150)
                ),
                label = "DotOffset_$index"
            )
            Text(
                text = ".",
                style = style,
                fontWeight = fontWeight,
                color = color,
                modifier = Modifier.graphicsLayer {
                    translationY = offsetY
                }
            )
        }
    }
}

@Composable
@Preview
fun JumpingDotsTextPreview(){
    AppTheme(dynamicColor = false) {
        Surface(modifier = Modifier.padding(16.dp)) {
            JumpingDotsText(text = "Hola Mundo...")
        }
    }
}