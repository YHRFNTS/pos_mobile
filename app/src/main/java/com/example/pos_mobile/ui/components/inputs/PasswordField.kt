package com.example.pos_mobile.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

/**
 * Reusable Password Field with Lock Icon & Toggle Eye Visibility.
 */
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Contraseña",
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = {
            Icon(painter = painterResource(R.drawable.lock_filled), contentDescription = null)
        },
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(name = "Password Field Preview", showBackground = true)
@Composable
fun PasswordFieldPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                PasswordField(
                    value = "miContraseña123",
                    onValueChange = {},
                    isPasswordVisible = false,
                    onTogglePasswordVisibility = {}
                )
            }
        }
    }
}
