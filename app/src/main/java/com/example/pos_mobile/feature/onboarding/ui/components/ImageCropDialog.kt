package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
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
import androidx.compose.ui.window.Dialog
import com.example.pos_mobile.core.designsystem.components.buttons.Button
import com.example.pos_mobile.core.designsystem.components.buttons.ButtonVariant

/**
 * Image Crop Dialog Overlay (Overlay/Onboarding/Image crop).
 */
@Composable
fun ImageCropDialog(
    onUploadAnotherClick: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    logoUri: String? = null
) {
    Dialog(onDismissRequest = onCancelClick) {
        Surface(
            modifier = modifier
                .width(460.dp)
                .clip(RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Main Crop Viewport Container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF282B36)),
                    contentAlignment = Alignment.Center
                ) {
                    // Circular Crop Mask
                    Box(
                        modifier = Modifier
                            .size(240.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF232A53)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "TAÏSO",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }

                    // 4 White Drag Handle Anchor Circles on Corners
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .align(Alignment.TopStart)
                    )
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .align(Alignment.TopEnd)
                    )
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .align(Alignment.BottomStart)
                    )
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .align(Alignment.BottomEnd)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Actions Row: [Subir otra imagen] ... [Cancelar] [Guardar]
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        text = "Subir otra imagen",
                        onClick = onUploadAnotherClick,
                        variant = ButtonVariant.Outlined,
                        leadingIcon = Icons.Default.Upload,
                        modifier = Modifier.height(42.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onCancelClick) {
                            Text(
                                text = "Cancelar",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            text = "Guardar",
                            onClick = { onSaveClick(logoUri ?: "cropped_logo_uri") },
                            variant = ButtonVariant.Primary,
                            modifier = Modifier.height(42.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Image Crop Dialog Preview", showBackground = true)
@Composable
fun ImageCropDialogPreview() {
    MaterialTheme {
        ImageCropDialog(
            onUploadAnotherClick = {},
            onCancelClick = {},
            onSaveClick = {}
        )
    }
}
