package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

/**
 * Onboarding background container with soft gradient and floating 3D glass shape drawables.
 */
@Composable
fun OnboardingBackground(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFAF7FD),
            Color(0xFFF4EDFB),
            Color(0xFFECE3F7)
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradientBrush)
    ) {
        // Floating 3D Glass Shapes from Drawables

        // Top-Left: Glass Octagon / Diamond
        Image(
            painter = painterResource(id = R.drawable.glass_diamond_2),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopStart)
                .offset(x = (-20).dp, y = 40.dp)
        )

        // Mid-Left: Glass Cubes Block
        Image(
            painter = painterResource(id = R.drawable.glass_cubes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-30).dp, y = (-20).dp)
        )

        // Bottom-Left: Glass Spring / Wire Spiral
        Image(
            painter = painterResource(id = R.drawable.glass_phone_wire),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(170.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-20).dp, y = (-20).dp)
        )

        // Top-Right: Glass Diamond / Gem
        Image(
            painter = painterResource(id = R.drawable.glass_diamond),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .offset(x = 20.dp, y = 50.dp)
        )

        // Mid-Right: Glass Toroid (Torus Ring)
        Image(
            painter = painterResource(id = R.drawable.glass_toroid),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 60.dp, y = 40.dp)
        )

        // Bottom-Right: Glass Stellar Star
        Image(
            painter = painterResource(id = R.drawable.glass_stellar),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(190.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 20.dp, y = (-10).dp)
        )

        content()
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingBackgroundPreview() {
    OnboardingBackground {
    }
}
