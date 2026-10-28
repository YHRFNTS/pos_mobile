package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.core.designsystem.components.buttons.Button
import com.example.pos_mobile.core.designsystem.components.buttons.ButtonVariant
import com.example.pos_mobile.feature.onboarding.ui.ColorTarget

/**
 * Screen 4 / Step 2 Branding: Color Customization ("¡Se está viendo bien!").
 */
@Composable
fun ColorCustomizationContent(
    primaryColor: Color,
    secondaryColor: Color,
    onOpenColorPicker: (ColorTarget) -> Unit,
    onSkipClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier,
    businessLogoName: String = "TAÏSO"
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(480.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¡Se está viendo bien!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Personaliza los colores de tu empresa. Estos se usarán en todo el sistema",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Main Row: Logo Circle Preview on Left + Color Selectors on Right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left: Circular Logo Avatar
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(primaryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = businessLogoName,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = secondaryColor
                    )
                }

                // Right: Color Pickers
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Colores",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Dual Swatch Card preview
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color(0xFFE0E0E0)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Row(modifier = Modifier.height(84.dp)) {
                            // Primary Color Half
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(primaryColor)
                                    .clickable { onOpenColorPicker(ColorTarget.PRIMARY) }
                            )
                            // Secondary Color Half
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .background(secondaryColor)
                                    .clickable { onOpenColorPicker(ColorTarget.SECONDARY) }
                            )
                        }
                    }

                    // Primary Color Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenColorPicker(ColorTarget.PRIMARY) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(primaryColor)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                        )
                        Text(
                            text = "Color primario",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Secondary Color Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenColorPicker(ColorTarget.SECONDARY) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(secondaryColor)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp))
                        )
                        Text(
                            text = "Color secundario",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Bottom Buttons Row: Omitir / Continuar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onSkipClick) {
                    Text(
                        text = "Omitir",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    text = "Continuar",
                    onClick = onContinueClick,
                    variant = ButtonVariant.Primary,
                    modifier = Modifier.height(44.dp)
                )
            }
        }
    }
}

@Preview(name = "Color Customization Content Preview", showBackground = true)
@Composable
fun ColorCustomizationContentPreview() {
    MaterialTheme {
        Surface {
            ColorCustomizationContent(
                primaryColor = Color(0xFF2B3A67),
                secondaryColor = Color(0xFFFFFFFF),
                onOpenColorPicker = {},
                onSkipClick = {},
                onContinueClick = {}
            )
        }
    }
}
