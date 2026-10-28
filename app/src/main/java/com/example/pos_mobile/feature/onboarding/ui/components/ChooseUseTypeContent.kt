package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.components.buttons.Button
import com.example.pos_mobile.core.designsystem.components.buttons.ButtonVariant

/**
 * Screen 1 / Welcome screen: Choose use type ("Toma el control total de tu negocio").
 */
@Composable
fun ChooseUseTypeContent(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Side: 3D Shop Bag Illustration
        Box(
            modifier = Modifier
                .weight(0.9f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.shop_bag),
                contentDescription = "Ilustración Onboarding",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }

        // Right Side: Title, Subtitle, CTA Button
        Column(
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Toma el\ncontrol total\nde tu negocio",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 48.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Empieza a cobrar, gestionar tu inventario y coordinar a tus cajeros de forma segura en cuestión de minutos.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                text = "Comenzar ahora",
                onClick = onStartClick,
                variant = ButtonVariant.Primary,
                modifier = Modifier
                    .width(220.dp)
                    .height(52.dp)
            )
        }
    }
}

@Preview(name = "Choose Use Type Preview", widthDp = 900, heightDp = 500, showBackground = true)
@Composable
fun ChooseUseTypeContentPreview() {
    MaterialTheme {
        Surface {
            ChooseUseTypeContent(onStartClick = {})
        }
    }
}
