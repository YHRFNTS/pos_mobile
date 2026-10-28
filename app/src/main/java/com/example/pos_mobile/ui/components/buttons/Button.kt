package com.example.pos_mobile.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ButtonVariant {
    Primary,
    Secondary,
    Outlined
}

/**
 * Reusable Normal Button component.
 * Stateless with state hoisting.
 */
@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val iconContent: @Composable (() -> Unit)? = leadingIcon?.let {
        { Icon(imageVector = it, contentDescription = null) }
    }

    val trailingIconContent: @Composable (() -> Unit)? = trailingIcon?.let {
        { Icon(imageVector = it, contentDescription = null) }
    }

    when (variant) {
        ButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                if (iconContent != null) {
                    iconContent()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = text)
                if (trailingIconContent != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIconContent()
                }
            }
        }
        ButtonVariant.Secondary -> {
            FilledTonalButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                if (iconContent != null) {
                    iconContent()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = text)
                if (trailingIconContent != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIconContent()
                }
            }
        }
        ButtonVariant.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled
            ) {
                if (iconContent != null) {
                    iconContent()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = text)
                if (trailingIconContent != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    trailingIconContent()
                }
            }
        }
    }
}

@Preview(name = "Button Preview", showBackground = true)
@Composable
fun ButtonPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text("Enabled Buttons", style = MaterialTheme.typography.titleLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Secondary)
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Primary)
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Outlined)
                }
                Text("Disabled Buttons", style = MaterialTheme.typography.titleLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Secondary, enabled = false)
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Primary, enabled = false)
                    Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Outlined, enabled = false)
                }
            }
        }
    }
}
