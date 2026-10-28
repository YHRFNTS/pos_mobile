package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pos_mobile.core.designsystem.components.selection.ColorSwatch

/**
 * Color Picker Dialog Overlay (Overlay/Color picker).
 */
@Composable
fun ColorPickerDialog(
    selectedColor: Color,
    savedColors: List<Color>,
    hexInput: String,
    onColorSelected: (Color) -> Unit,
    onHexInputChanged: (String) -> Unit,
    onAddSavedColor: (Color) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var hue by remember { mutableFloatStateOf(240f) }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier
                .width(340.dp)
                .clip(RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFF3EEF6)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Saturation / Value Gradient Canvas Field
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    val sat = (offset.x / size.width).coerceIn(0f, 1f)
                                    val valNorm = 1f - (offset.y / size.height).coerceIn(0f, 1f)
                                    val color = Color.hsv(hue, sat, valNorm)
                                    onColorSelected(color)
                                }
                            }
                    ) {
                        // Base Hue Color Gradient
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.White, Color.hsv(hue, 1f, 1f))
                            )
                        )
                        // Black Shade Gradient Overlay
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Hue Spectrum Slider Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(CircleShape)
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    hue = (offset.x / size.width).coerceIn(0f, 1f) * 360f
                                    onColorSelected(Color.hsv(hue, 0.8f, 0.9f))
                                }
                            }
                    ) {
                        val hueColors = listOf(
                            Color.Red, Color.Yellow, Color.Green,
                            Color.Cyan, Color.Blue, Color.Magenta, Color.Red
                        )
                        drawRect(brush = Brush.horizontalGradient(hueColors))
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // 3. Alpha / Opacity Slider Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp)
                        .clip(CircleShape)
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.Transparent, selectedColor)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 4. Hex Input Controls Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Selector Format ("Hex v")
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFE8E2EC),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = "Hex", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null, modifier = Modifier.size(16.dp))
                        }
                    }

                    // Hex Text Input Field (# 4F46E5)
                    OutlinedTextField(
                        value = "# $hexInput",
                        onValueChange = { input ->
                            val cleanHex = input.removePrefix("#").trim()
                            onHexInputChanged(cleanHex)
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp)
                    )

                    // Percentage Box ("100%")
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFE8E2EC),
                        modifier = Modifier
                            .height(40.dp)
                            .width(54.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Text(text = "100%", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 5. Saved Colors Row with Design System ColorSwatch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Saved colors:",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.clickable { onAddSavedColor(selectedColor) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Añadir color",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Add",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                ColorSwatch(
                    colors = savedColors,
                    selectedColor = selectedColor,
                    onColorSelected = onColorSelected,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(name = "Color Picker Dialog Preview", showBackground = true)
@Composable
fun ColorPickerDialogPreview() {
    MaterialTheme {
        ColorPickerDialog(
            selectedColor = Color(0xFF4F46E5),
            savedColors = listOf(
                Color(0xFFEF4444), Color(0xFFF97316), Color(0xFFFACC15),
                Color(0xFF22C55E), Color(0xFF06B6D4), Color(0xFF3B82F6),
                Color(0xFF6366F1), Color(0xFFA855F7), Color(0xFFEC4899)
            ),
            hexInput = "4F46E5",
            onColorSelected = {},
            onHexInputChanged = {},
            onAddSavedColor = {},
            onDismissRequest = {}
        )
    }
}
