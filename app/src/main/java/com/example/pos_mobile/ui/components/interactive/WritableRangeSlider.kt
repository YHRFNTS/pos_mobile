package com.example.pos_mobile.ui.components.interactive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * Range Slider con saltos discretos (steps) y controles Stepper (- / +) para Min y Max.
 *
 * @param stepIncrement Cuánto sube/baja cada toque en los botones +/- y el salto del slider (ej. 50f).
 *                      Si es 0f, el slider es continuo y los botones +/- suben de 1 en 1.
 */
@Composable
fun WritableRangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1000f,
    stepIncrement: Float = 50f,
    currencySymbol: String = "$",
    onValueChangeFinished: (() -> Unit)? = null
) {
    val sliderSteps = remember(valueRange, stepIncrement) {
        if (stepIncrement > 0f) {
            val totalIntervals = ((valueRange.endInclusive - valueRange.start) / stepIncrement).roundToInt()
            (totalIntervals - 1).coerceAtLeast(0)
        } else {
            0
        }
    }

    val buttonDelta = if (stepIncrement > 0f) stepIncrement else 1f

    Column(modifier = modifier.fillMaxWidth()) {
        RangeSlider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = sliderSteps,
            onValueChangeFinished = onValueChangeFinished,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PriceStepperBadge(
                amount = value.start.roundToInt(),
                currencySymbol = currencySymbol,
                canDecrement = value.start > valueRange.start,
                canIncrement = value.start + buttonDelta <= value.endInclusive,
                onDecrement = {
                    val newMin = (value.start - buttonDelta).coerceAtLeast(valueRange.start)
                    onValueChange(newMin..value.endInclusive)
                    onValueChangeFinished?.invoke()
                },
                onIncrement = {
                    val newMin = (value.start + buttonDelta).coerceAtMost(value.endInclusive)
                    onValueChange(newMin..value.endInclusive)
                    onValueChangeFinished?.invoke()
                }
            )

            PriceStepperBadge(
                amount = value.endInclusive.roundToInt(),
                currencySymbol = currencySymbol,
                canDecrement = value.endInclusive - buttonDelta >= value.start,
                canIncrement = value.endInclusive < valueRange.endInclusive,
                onDecrement = {
                    val newMax = (value.endInclusive - buttonDelta).coerceAtLeast(value.start)
                    onValueChange(value.start..newMax)
                    onValueChangeFinished?.invoke()
                },
                onIncrement = {
                    val newMax = (value.endInclusive + buttonDelta).coerceAtMost(valueRange.endInclusive)
                    onValueChange(value.start..newMax)
                    onValueChangeFinished?.invoke()
                }
            )
        }
    }
}

/**
 * Sub-componente reutilizable para cada pastilla con botones [-] $Monto [+].
 */
@Composable
private fun PriceStepperBadge(
    amount: Int,
    currencySymbol: String,
    canDecrement: Boolean,
    canIncrement: Boolean,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)
        ) {
            IconButton(
                onClick = onDecrement,
                enabled = canDecrement,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Disminuir",
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = "$currencySymbol$amount",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 6.dp)
            )

            IconButton(
                onClick = onIncrement,
                enabled = canIncrement,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Preview(name = "Writable Range Slider Preview", showBackground = true)
@Composable
fun WritableRangeSliderPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            var range by remember { mutableStateOf(100f..500f) }
            WritableRangeSlider(
                value = range,
                onValueChange = { range = it },
                valueRange = 0f..1000f,
                stepIncrement = 50f
            )
        }
    }
}