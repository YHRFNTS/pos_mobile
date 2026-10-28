package com.example.pos_mobile.core.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class IconButtonVariant {
    Standard,
    Primary,
    Secondary,
    Outlined
}

/**
 * Reusable Icon Button component with design system variants.
 * Stateless with state hoisting.
 */
@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: IconButtonVariant = IconButtonVariant.Standard,
    enabled: Boolean = true
) {
    when (variant) {
        IconButtonVariant.Standard -> {
            IconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
        IconButtonVariant.Primary -> {
            IconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
            ) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
        IconButtonVariant.Secondary -> {
            FilledTonalIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
        IconButtonVariant.Outlined -> {
            OutlinedIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
    }
}

@Preview(name = "Icon Button Preview", showBackground = true)
@Composable
fun IconButtonPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Standard)
                IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Secondary)
                IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Primary)
                IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Outlined)
            }
        }
    }
}
