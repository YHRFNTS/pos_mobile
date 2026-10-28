package com.example.pos_mobile.core.designsystem.components.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.theme.AppTheme
import com.example.pos_mobile.core.designsystem.theme.extendedColorScheme

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
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        if (isCurrent) MaterialTheme.colorScheme.surfaceContainerHighest else Color.Transparent
                    )
                    .then(
                        if (onStepClick != null) Modifier.clickable { onStepClick(i) } else Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isCompleted -> MaterialTheme.extendedColorScheme.sucess.color
                                isCurrent -> MaterialTheme.colorScheme.secondary
                                else -> MaterialTheme.colorScheme.surfaceContainerHigh
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = stringResource(R.string.step_completed, i),
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Text(
                            text = i.toString(),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                            color = if (isCurrent) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (i < totalSteps) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 2.dp,
                    color = if (i < currentStep) MaterialTheme.extendedColorScheme.sucess.color else MaterialTheme.colorScheme.surfaceContainerHighest
                )
            }
        }
    }
}

@Preview(name = "Wizard Stepper Preview", showBackground = true)
@Composable
fun WizardStepperPreview() {
    AppTheme(dynamicColor = false) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WizardStepper(totalSteps = 4, currentStep = 1)
                WizardStepper(totalSteps = 4, currentStep = 2)
            }
        }
    }
}
