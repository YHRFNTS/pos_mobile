package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.components.buttons.Button
import com.example.pos_mobile.core.designsystem.components.buttons.ButtonVariant
import com.example.pos_mobile.core.designsystem.components.media.FileUploadCard
import com.example.pos_mobile.core.designsystem.components.media.FileUploadState

/**
 * Screen 3 / Step 2: Logo Upload ("Es hora de ponerle cara a tu negocio").
 */
@Composable
fun LogoUploadContent(
    fileUploadState: FileUploadState,
    onSelectLogoClick: () -> Unit,
    onSkipClick: () -> Unit,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(440.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Es hora de ponerle cara\na tu negocio",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 38.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Sube tu logotipo y empieza una experiencia más profesional",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Dashed File Upload Card from Design System
            FileUploadCard(
                state = fileUploadState,
                onFileSelect = onSelectLogoClick,
                title = stringResource(R.string.file_upload_title),
                subtitle = stringResource(R.string.file_upload_subtitle)
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                    variant = ButtonVariant.Secondary,
                    modifier = Modifier.height(44.dp)
                )
            }
        }
    }
}

@Preview(name = "Logo Upload Content Preview", showBackground = true)
@Composable
fun LogoUploadContentPreview() {
    MaterialTheme {
        Surface {
            LogoUploadContent(
                fileUploadState = FileUploadState.Default,
                onSelectLogoClick = {},
                onSkipClick = {},
                onContinueClick = {}
            )
        }
    }
}
