package com.example.pos_mobile.core.designsystem.components.media

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.theme.AppTheme

enum class FileUploadState {
    Default,
    Active,
    Uploading
}

private val SphereRotationEasing = CubicBezierEasing(0.07f, 0.99f, 0.81f, 0.11f)

private fun Modifier.dashedBorder(
    strokeWidth: Dp = 2.dp,
    color: Color,
    cornerRadius: Dp = 16.dp,
    dashLength: Dp = 10.dp,
    gapLength: Dp = 6.dp,
): Modifier = this.drawWithContent {
    drawContent()
    val strokeWidthPx = strokeWidth.toPx()
    val halfStroke = strokeWidthPx / 2f
    val cornerRadiusPx = cornerRadius.toPx()
    val dashPx = dashLength.toPx()
    val gapPx = gapLength.toPx()

    drawRoundRect(
        color = color,
        topLeft = Offset(halfStroke, halfStroke),
        size = Size(size.width - strokeWidthPx, size.height - strokeWidthPx),
        cornerRadius = CornerRadius(cornerRadiusPx - halfStroke),
        style = Stroke(
            width = strokeWidthPx,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPx, gapPx), 0f),
        ),
    )
}

/**
 * Reusable File Upload Card with Default, Active/Selected, and Uploading states.
 * Stateless with state hoisting.
 */
@Composable
fun FileUploadCard(
    state: FileUploadState,
    onFileSelect: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.file_upload_title),
    subtitle: String = stringResource(R.string.file_upload_subtitle),
    uploadingText: String = stringResource(R.string.file_upload_uploading),
) {
    val backgroundColor = when (state) {
        FileUploadState.Default -> Color.White
        FileUploadState.Active, FileUploadState.Uploading -> Color(0xFFD6E4FF)
    }

    val borderColor = when (state) {
        FileUploadState.Default -> Color(0xFFD0C9D6)
        FileUploadState.Active, FileUploadState.Uploading -> Color(0xFF2B5F8E)
    }

    Surface(
        onClick = onFileSelect,
        modifier = modifier
            .fillMaxWidth()
            .dashedBorder(
                strokeWidth = 2.5.dp,
                color = borderColor,
                cornerRadius = 16.dp,
            ),
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (state) {
                FileUploadState.Default -> {
                    Icon(
                        painter = painterResource(id = R.drawable.upload_outline),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(76.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                FileUploadState.Active -> {
                    Icon(
                        painter = painterResource(id = R.drawable.upload_outline),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(76.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                FileUploadState.Uploading -> {
                    val infiniteTransition = rememberInfiniteTransition(label = "SphereUploadRotation")
                    val rotation by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f, // Clockwise 360°
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = 1497,
                                delayMillis = 0,
                                easing = SphereRotationEasing
                            ),
                            repeatMode = RepeatMode.Restart
                        ),
                        label = "SphereRotationDegrees"
                    )

                    Image(
                        painter = painterResource(id = R.drawable.glass_sphere_blue),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .graphicsLayer {
                                rotationZ = rotation
                            },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = uploadingText,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1D1B20),
                    )
                }
            }
        }
    }
}

@Preview(name = "File Upload Card Preview", showBackground = true)
@Composable
fun FileUploadCardPreview() {
    AppTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = Color.White,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                FileUploadCard(
                    state = FileUploadState.Default,
                    onFileSelect = {}
                )
                FileUploadCard(
                    state = FileUploadState.Active,
                    onFileSelect = {}
                )
                FileUploadCard(
                    state = FileUploadState.Uploading,
                    onFileSelect = {}
                )
            }
        }
    }
}
