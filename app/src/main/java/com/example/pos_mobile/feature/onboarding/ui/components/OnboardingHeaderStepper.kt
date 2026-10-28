package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Custom Onboarding Pill Dot Stepper Header.
 * Renders active step as elongated rounded pill `[===]` and inactive steps as dots `o`.
 */
@Composable
fun OnboardingHeaderStepper(
    currentStepIndex: Int,
    modifier: Modifier = Modifier,
    totalSteps: Int = 6,
    onBackClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.Center)
        ) {
            for (step in 1..totalSteps) {
                val isActive = step == currentStepIndex
                val isCompleted = step < currentStepIndex

                val pillWidth by animateDpAsState(
                    targetValue = if (isActive) 24.dp else 10.dp,
                    label = "pillWidthAnimation"
                )

                val pillColor = when {
                    isActive -> Color(0xFF6B538C) // Primary brand color pill
                    isCompleted -> Color(0xFF8E7BB0)
                    else -> Color(0xFFD6CDE2)
                }

                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .width(pillWidth)
                        .clip(CircleShape)
                        .background(pillColor)
                )
            }
        }
    }
}

@Preview(name = "Onboarding Header Stepper Preview", showBackground = true)
@Composable
fun OnboardingHeaderStepperPreview() {
    MaterialTheme {
        Surface {
            OnboardingHeaderStepper(
                currentStepIndex = 1,
                totalSteps = 6,
                onBackClick = {}
            )
        }
    }
}
