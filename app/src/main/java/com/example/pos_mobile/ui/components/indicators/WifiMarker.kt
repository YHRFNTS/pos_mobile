package com.example.pos_mobile.ui.components.indicators

import androidx.compose.animation.VectorConverter
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Wifi1Bar
import androidx.compose.material.icons.filled.Wifi2Bar
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R
import com.example.pos_mobile.ui.theme.AppTheme

/**
 * Wifi connection states.
 */
enum class WifiState {
    ONLINE,
    OFFLINE,
    CONNECTING
}

/**
 * Reusable Wifi Marker showing connection state (En línea / Sin conexión / Conectando).
 */
@Composable
fun WifiMarker(
    state: WifiState,
    modifier: Modifier = Modifier
) {
    val text = when (state) {
        WifiState.ONLINE -> stringResource(R.string.wifi_status_online)
        WifiState.OFFLINE -> stringResource(R.string.wifi_status_offline)
        WifiState.CONNECTING -> stringResource(R.string.wifi_status_connecting)
    }

    val tint = when (state) {
        WifiState.ONLINE -> MaterialTheme.colorScheme.primary
        WifiState.CONNECTING -> MaterialTheme.colorScheme.secondary
        WifiState.OFFLINE -> MaterialTheme.colorScheme.outlineVariant
    }

    val textColor = when (state) {
        WifiState.ONLINE -> MaterialTheme.colorScheme.primary
        WifiState.CONNECTING -> MaterialTheme.colorScheme.secondary
        WifiState.OFFLINE -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    val infiniteTransition = rememberInfiniteTransition(label = "WifiIconCycle")
    val step by if (state == WifiState.CONNECTING) {
        infiniteTransition.animateValue(
            initialValue = 0,
            targetValue = 3,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "WifiStep"
        )
    } else {
        rememberUpdatedState(2)
    }

    val icon = when (state) {
        WifiState.ONLINE -> Icons.Default.Wifi
        WifiState.OFFLINE -> Icons.Default.WifiOff
        WifiState.CONNECTING -> when (step) {
            0 -> Icons.Default.Wifi1Bar
            1 -> Icons.Default.Wifi2Bar
            else -> Icons.Default.Wifi
        }
    }

    val scale by if (state == WifiState.CONNECTING) {
        infiniteTransition.animateFloat(
            initialValue = 0.9f,
            targetValue = 1.05f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "WifiConnectingScale"
        )
    } else {
        rememberUpdatedState(1.0f)
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = tint,
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    this.scaleX = scale
                    this.scaleY = scale
                }
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.graphicsLayer {
                this.alpha = if (state == WifiState.CONNECTING) alpha else 1.0f
            }
        )
    }
}

/**
 * Backwards-compatible overload for boolean isOnline.
 */
@Composable
fun WifiMarker(
    isOnline: Boolean,
    modifier: Modifier = Modifier
) {
    WifiMarker(
        state = if (isOnline) WifiState.ONLINE else WifiState.OFFLINE,
        modifier = modifier
    )
}

@Preview(name = "Wifi Marker Preview", showBackground = true)
@Composable
fun WifiMarkerPreview() {
    AppTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                WifiMarker(state = WifiState.ONLINE)
                WifiMarker(state = WifiState.OFFLINE)
                WifiMarker(state = WifiState.CONNECTING)
            }
        }
    }
}
