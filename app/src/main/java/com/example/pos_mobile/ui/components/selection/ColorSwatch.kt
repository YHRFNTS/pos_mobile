package com.example.pos_mobile.ui.components.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun Color.contrastingContentColor(
    darkContent: Color = Color(0xFF1D1B20), // Negro suave de Material 3
    lightContent: Color = Color.White
): Color {
    return if (this.luminance() > 0.5f) darkContent else lightContent
}

/**
 * Reusable Color Swatch selection component.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSwatch(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        colors.forEach { color ->
            val isSelected = color == selectedColor
            val checkColor = color.contrastingContentColor()

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color)
                    .then(
                        if (isSelected) {
                            Modifier.border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(6.dp)
                            )
                        } else Modifier
                    )
                    .clickable { onColorSelected(color) },
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Seleccionado",
                        tint = checkColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(name = "Color Swatch Preview", showBackground = true)
@Composable
fun ColorSwatchPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            ColorSwatch(
                colors = listOf(
                    Color(0xFF4CAF50),
                    Color(0xFFF44336),
                    Color(0xFFFFEB3B),
                    Color(0xFF9C27B0),
                    Color(0xFF795548)
                ),
                selectedColor = Color(0xFFFFEB3B),
                onColorSelected = {}
            )
        }
    }
}
