package com.example.pos_mobile.core.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Toggle/Dropdown Button component.
 * Displays text, leading icon, and trailing chevron arrow icon (expanded/collapsed).
 */
@Composable
fun ToggleDropdownButton(
    text: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Secondary,
    enabled: Boolean = true,
    leadingIcon: ImageVector = Icons.Default.Settings
) {
    val trailingChevron = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    Button(
        text = text,
        onClick = onToggle,
        modifier = modifier,
        variant = variant,
        enabled = enabled,
        leadingIcon = leadingIcon,
        trailingIcon = trailingChevron
    )
}

@Preview(name = "Toggle Dropdown Button Preview", showBackground = true)
@Composable
fun ToggleDropdownButtonPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ToggleDropdownButton(text = "Configuración", isExpanded = false, onToggle = {}, variant = ButtonVariant.Secondary)
                ToggleDropdownButton(text = "Configuración", isExpanded = true, onToggle = {}, variant = ButtonVariant.Primary)
            }
        }
    }
}
