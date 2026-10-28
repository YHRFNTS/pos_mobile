package com.example.pos_mobile.core.designsystem.components.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.theme.AppTheme

/**
 * Reusable Vertical Payment / Toggle Item Card (e.g. "Efectivo").
 */
@Composable
fun VerticalPaymentToggle(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
) {
    val defaultBackgroundColor = when {
        selected && enabled -> MaterialTheme.colorScheme.secondary
        selected && !enabled -> MaterialTheme.colorScheme.secondaryContainer
        !selected && enabled -> MaterialTheme.colorScheme.surfaceContainer
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val defaultContentColor = when {
        selected && enabled -> MaterialTheme.colorScheme.surface
        selected && !enabled -> MaterialTheme.colorScheme.outline
        !selected && enabled -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.outline
    }

    val defaultShape = if (selected){
        MaterialTheme.shapes.medium
    } else {
        MaterialTheme.shapes.large
    }

    val backgroundColor = containerColor ?: defaultBackgroundColor
    val finalContentColor = contentColor ?: defaultContentColor

    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.width(135.dp).fillMaxWidth(),
        shape = defaultShape,
        color = backgroundColor,
        contentColor = finalContentColor,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 13.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
            )
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Preview(name = "Vertical Payment Toggle Preview", showBackground = true)
@Composable
fun VerticalPaymentTogglePreview() {
    val cashIcon = ImageVector.vectorResource(id = R.drawable.cash_icon)
    AppTheme(dynamicColor = false) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                VerticalPaymentToggle(
                    label = "Efectivo",
                    icon = cashIcon,
                    selected = false,
                    enabled = true,
                    onClick = {},
                )
                VerticalPaymentToggle(
                    label = "Efectivo",
                    icon = cashIcon,
                    selected = false,
                    enabled = false,
                    onClick = {},
                )
                VerticalPaymentToggle(
                    label = "Efectivo",
                    icon = cashIcon,
                    selected = true,
                    enabled = true,
                    onClick = {},
                )
                VerticalPaymentToggle(
                    label = "Efectivo",
                    icon = cashIcon,
                    selected = true,
                    enabled = false,
                    onClick = {},
                )
            }
        }
    }
}

