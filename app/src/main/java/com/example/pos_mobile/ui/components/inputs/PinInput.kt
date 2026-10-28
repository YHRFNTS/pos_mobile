package com.example.pos_mobile.ui.components.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable PIN / OTP Input Boxes.
 * Displays individual boxes for code digits (e.g. 4, 6 or 8 digits).
 */
@Composable
fun PinInput(
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 4,
    groupSeparated: Boolean = false
) {
    BasicTextField(
        value = code,
        onValueChange = {
            if (it.length <= length) {
                onCodeChange(it.uppercase())
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                val halfLength = length / 2
                for (i in 0 until length) {
                    if (groupSeparated && (i == halfLength) && (i > 0)) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    val char = if (i < code.length) code[i].toString() else ""
                    val isFocused = i == code.length

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .border(
                                width = if (isFocused) 2.dp else 1.dp,
                                color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}

@Preview(name = "Pin Input Preview", showBackground = true)
@Composable
fun PinInputPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PinInput(code = "3GD9", onCodeChange = {}, length = 4)
                PinInput(code = "G8F23L21", onCodeChange = {}, length = 8, groupSeparated = true)
            }
        }
    }
}
