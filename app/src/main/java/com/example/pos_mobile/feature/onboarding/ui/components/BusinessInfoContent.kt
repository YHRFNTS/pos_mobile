package com.example.pos_mobile.feature.onboarding.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.core.designsystem.components.buttons.Button
import com.example.pos_mobile.core.designsystem.components.buttons.ButtonVariant

/**
 * Screen 2 / Step 1: Business Information ("Cuéntanos sobre tu negocio").
 */
@Composable
fun BusinessInfoContent(
    businessName: String,
    onBusinessNameChange: (String) -> Unit,
    businessNameError: String?,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    availableCategories: List<String>,
    isCategoryDropdownExpanded: Boolean,
    onCategoryDropdownToggle: (Boolean) -> Unit,
    onContinueClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .width(420.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cuéntanos sobre tu negocio",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Ingresa los datos de tu empresa",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Field 1: Business Name
            OutlinedTextField(
                value = businessName,
                onValueChange = onBusinessNameChange,
                label = { Text("Nombre del negocio") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                isError = businessNameError != null,
                supportingText = if (businessNameError != null) { { Text(businessNameError) } } else null,
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Field 2: Category Dropdown
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = selectedCategory.ifEmpty { "Categoría" },
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = if (isCategoryDropdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCategoryDropdownToggle(!isCategoryDropdownExpanded) },
                    enabled = false, // Handled via box click
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = if (selectedCategory.isNotEmpty()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                DropdownMenu(
                    expanded = isCategoryDropdownExpanded,
                    onDismissRequest = { onCategoryDropdownToggle(false) },
                    modifier = Modifier.width(372.dp)
                ) {
                    availableCategories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = { onCategorySelected(category) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Continue Button
            Button(
                text = "Continuar",
                onClick = onContinueClick,
                variant = ButtonVariant.Primary,
                enabled = businessName.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Footer Link: ¿Ya tienes cuenta? Iniciar sesión
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¿Ya tienes cuenta? ",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Iniciar sesión",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}

@Preview(name = "Business Info Content Preview", showBackground = true)
@Composable
fun BusinessInfoContentPreview() {
    MaterialTheme {
        Surface {
            BusinessInfoContent(
                businessName = "Papelería El Orígen",
                onBusinessNameChange = {},
                businessNameError = null,
                selectedCategory = "Papelería",
                onCategorySelected = {},
                availableCategories = listOf("Papelería", "Abarrotes", "Restaurante"),
                isCategoryDropdownExpanded = false,
                onCategoryDropdownToggle = {},
                onContinueClick = {},
                onLoginClick = {}
            )
        }
    }
}
