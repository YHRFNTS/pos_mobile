package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Wizard Stepper / Step Progress Indicator.
 */
@Composable
fun WizardStepper(
    totalSteps: Int,
    currentStep: Int,
    modifier: Modifier = Modifier,
    onStepClick: ((Int) -> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..totalSteps) {
            val isCompleted = i < currentStep
            val isCurrent = i == currentStep

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            isCompleted -> Color(0xFF4CAF50)
                            isCurrent -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.surfaceContainerHigh
                        }
                    )
                    .then(
                        if (onStepClick != null) Modifier.clickable { onStepClick(i) } else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Paso $i completado",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Text(
                        text = i.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isCurrent) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (i < totalSteps) {
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
                    thickness = 2.dp,
                    color = if (i < currentStep) Color(0xFF4CAF50) else MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}

@Preview(name = "Wizard Stepper Preview", showBackground = true)
@Composable
fun WizardStepperPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                WizardStepper(totalSteps = 4, currentStep = 2)
            }
        }
    }
}
