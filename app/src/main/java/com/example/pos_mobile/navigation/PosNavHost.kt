package com.example.pos_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pos_mobile.feature.onboarding.ui.OnboardingRoute

@Composable
fun PosNavHost(
    modifier: Modifier = Modifier
) {
    OnboardingRoute(
        onNavigateToAuth = {},
        onOnboardingComplete = {}
    )
}
