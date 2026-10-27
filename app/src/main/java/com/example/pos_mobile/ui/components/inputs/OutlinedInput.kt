package com.example.pos_mobile.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Standard Outlined Input TextField.
 */
@Composable
fun OutlinedInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    isError: Boolean = false,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        supportingText = if (supportingText != null) { { Text(supportingText) } } else null,
        isError = isError,
        enabled = enabled,
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(name = "Outlined Input Preview", showBackground = true)
@Composable
fun OutlinedInputPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                OutlinedInput(value = "Input text", onValueChange = {}, label = "Label")
            }
        }
    }
}
