package com.example.pos_mobile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.ui.components.buttons.Button
import com.example.pos_mobile.ui.components.buttons.ButtonVariant
import com.example.pos_mobile.ui.components.buttons.IconButton
import com.example.pos_mobile.ui.components.buttons.IconButtonVariant
import com.example.pos_mobile.ui.components.buttons.ToggleDropdownButton
import com.example.pos_mobile.ui.components.indicators.IndicatorPill
import com.example.pos_mobile.ui.components.indicators.MovementDetailRow
import com.example.pos_mobile.ui.components.indicators.MovementType
import com.example.pos_mobile.ui.components.indicators.MovementTypePill
import com.example.pos_mobile.ui.components.indicators.PaidPill
import com.example.pos_mobile.ui.components.indicators.PriceBadge
import com.example.pos_mobile.ui.components.indicators.ProductIconStack
import com.example.pos_mobile.ui.components.indicators.WifiMarker
import com.example.pos_mobile.ui.components.indicators.WizardStepper
import com.example.pos_mobile.ui.components.inputs.CheckboxOption
import com.example.pos_mobile.ui.components.inputs.OutlinedInput
import com.example.pos_mobile.ui.components.inputs.PasswordField
import com.example.pos_mobile.ui.components.inputs.PinInput
import com.example.pos_mobile.ui.components.inputs.PriceInput
import com.example.pos_mobile.ui.components.interactive.CopyableText
import com.example.pos_mobile.ui.components.interactive.DropdownHeader
import com.example.pos_mobile.ui.components.interactive.WritableRangeSlider
import com.example.pos_mobile.ui.components.media.EditableImage
import com.example.pos_mobile.ui.components.media.FileUploadCard
import com.example.pos_mobile.ui.components.media.FileUploadState
import com.example.pos_mobile.ui.components.media.LoyaltyBadge
import com.example.pos_mobile.ui.components.selection.CategoryPill
import com.example.pos_mobile.ui.components.selection.ColorSwatch
import com.example.pos_mobile.ui.components.selection.NumberSpinner
import com.example.pos_mobile.ui.components.selection.PresentationCard
import com.example.pos_mobile.ui.components.selection.SelectionChip
import com.example.pos_mobile.ui.components.selection.VerticalPaymentToggle

/**
 * Composite Showcase Screen illustrating all components and their variants.
 * Fully responsive across Phone, Tablet, and Desktop screen sizes.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ComponentShowcase(
    modifier: Modifier = Modifier
) {
    // Hoisted interactive states for the showcase
    var spinnerValue by remember { mutableIntStateOf(3) }
    var selectedMaterialChip by remember { mutableStateOf("Madera") }
    var selectedPresentation by remember { mutableStateOf("Individual") }
    var selectedColor by remember { mutableStateOf(Color(0xFFFFEB3B)) }
    var isPaymentSelected by remember { mutableStateOf(true) }
    var textInputValue by remember { mutableStateOf("Input text") }
    var priceInputValue by remember { mutableStateOf("10") }
    var passwordValue by remember { mutableStateOf("miContraseña123") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var pinValue by remember { mutableStateOf("3GD9") }
    var isCheckboxChecked by remember { mutableStateOf(true) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var rangeValues by remember { mutableStateOf(100f..500f) }
    var wizardStep by remember { mutableIntStateOf(2) }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Design System Components",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            // Section 1: Buttons
            ShowcaseCard(title = "Buttons & Action Controls") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Normal Buttons", style = MaterialTheme.typography.titleSmall)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Secondary)
                        Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Primary)
                        Button(text = "Configuración", onClick = {}, leadingIcon = Icons.Default.Settings, variant = ButtonVariant.Outlined)
                    }

                    Text("Icon Buttons", style = MaterialTheme.typography.titleSmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Standard)
                        IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Secondary)
                        IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Primary)
                        IconButton(icon = Icons.Default.Settings, contentDescription = "Settings", onClick = {}, variant = IconButtonVariant.Outlined)
                    }

                    Text("Toggle Dropdown Button", style = MaterialTheme.typography.titleSmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        ToggleDropdownButton(
                            text = "Configuración",
                            isExpanded = isDropdownExpanded,
                            onToggle = { isDropdownExpanded = !isDropdownExpanded },
                            variant = ButtonVariant.Secondary
                        )
                        ToggleDropdownButton(
                            text = "Configuración",
                            isExpanded = !isDropdownExpanded,
                            onToggle = { isDropdownExpanded = !isDropdownExpanded },
                            variant = ButtonVariant.Primary
                        )
                    }
                }
            }

            // Section 2: Selection & Variants
            ShowcaseCard(title = "Selection & Product Variants") {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Number Spinner", style = MaterialTheme.typography.titleSmall)
                        NumberSpinner(
                            value = spinnerValue,
                            onValueChange = { spinnerValue = it }
                        )
                    }

                    Text("Material Selection Chips", style = MaterialTheme.typography.titleSmall)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Madera", "Metal", "Plástico").forEach { mat ->
                            SelectionChip(
                                label = mat,
                                selected = mat == selectedMaterialChip,
                                onClick = { selectedMaterialChip = mat }
                            )
                        }
                    }

                    Text("Color Swatch Selector", style = MaterialTheme.typography.titleSmall)
                    ColorSwatch(
                        colors = listOf(
                            Color(0xFF4CAF50),
                            Color(0xFFF44336),
                            Color(0xFFFFEB3B),
                            Color(0xFF9C27B0),
                            Color(0xFF795548)
                        ),
                        selectedColor = selectedColor,
                        onColorSelected = { selectedColor = it }
                    )

                    Text("Presentation Options", style = MaterialTheme.typography.titleSmall)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        PresentationCard(
                            title = "Individual",
                            price = "$5",
                            unitCost = "por pieza",
                            selected = selectedPresentation == "Individual",
                            onClick = { selectedPresentation = "Individual" },
                            modifier = Modifier.width(130.dp)
                        )
                        PresentationCard(
                            title = "Caja x12",
                            price = "$50",
                            unitCost = "$3.75 c/u",
                            selected = selectedPresentation == "Caja x12",
                            onClick = { selectedPresentation = "Caja x12" },
                            modifier = Modifier.width(130.dp)
                        )
                        PresentationCard(
                            title = "Mayoreo x100",
                            price = "$1,000",
                            unitCost = "$3.00 c/u",
                            selected = selectedPresentation == "Mayoreo x100",
                            onClick = { selectedPresentation = "Mayoreo x100" },
                            modifier = Modifier.width(130.dp)
                        )
                    }

                    Text("Vertical Payment Method Toggle", style = MaterialTheme.typography.titleSmall)
                    VerticalPaymentToggle(
                        label = "Efectivo",
                        icon = Icons.Default.Money,
                        selected = isPaymentSelected,
                        onClick = { isPaymentSelected = !isPaymentSelected }
                    )
                }
            }

            // Section 3: Inputs & Form
            ShowcaseCard(title = "Inputs & Form Fields") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedInput(
                        value = textInputValue,
                        onValueChange = { textInputValue = it },
                        label = "Label"
                    )

                    PriceInput(
                        value = priceInputValue,
                        onValueChange = { priceInputValue = it },
                        isError = priceInputValue.toIntOrNull()?.let { it < 15 } ?: true,
                        errorMessage = "monto insuficiente"
                    )

                    PasswordField(
                        value = passwordValue,
                        onValueChange = { passwordValue = it },
                        isPasswordVisible = isPasswordVisible,
                        onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible }
                    )

                    Text("PIN / OTP Code Input", style = MaterialTheme.typography.titleSmall)
                    PinInput(
                        code = pinValue,
                        onCodeChange = { pinValue = it },
                        length = 4
                    )

                    CheckboxOption(
                        checked = isCheckboxChecked,
                        onCheckedChange = { isCheckboxChecked = it },
                        label = "Recordar sesión"
                    )
                }
            }

            // Section 4: Indicators & Status
            ShowcaseCard(title = "Indicators & Status Badges") {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PriceBadge(price = "$10", originalPrice = "$100")
                        WifiMarker(isOnline = true)
                    }

                    HorizontalDivider()

                    Text("Pills & Status", style = MaterialTheme.typography.titleSmall)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        PaidPill(isPaid = true)
                        PaidPill(isPaid = false)
                        IndicatorPill(label = "Activo", isActive = true)
                        CategoryPill(label = "Papelería", containerColor = Color(0xFFFFECB3), contentColor = Color(0xFF5D4037))
                        CategoryPill(label = "Manualidades", containerColor = Color(0xFFF8BBD0), contentColor = Color(0xFF880E4F))
                        CategoryPill(label = "Snacks", containerColor = Color(0xFFFFCCBC), contentColor = Color(0xFFBF360C))
                    }

                    Text("Movement Types", style = MaterialTheme.typography.titleSmall)
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        MovementTypePill(type = MovementType.Venta)
                        MovementTypePill(type = MovementType.Restock)
                        MovementTypePill(type = MovementType.Transferencia)
                    }

                    Text("Wizard / Stepper Progress", style = MaterialTheme.typography.titleSmall)
                    WizardStepper(
                        totalSteps = 5,
                        currentStep = wizardStep,
                        onStepClick = { wizardStep = it }
                    )

                    Text("Movement Detail", style = MaterialTheme.typography.titleSmall)
                    MovementDetailRow(
                        originWarehouse = "Sucursal centro",
                        originQuantity = "-50",
                        destinationWarehouse = "Sucursal sur",
                        destinationQuantity = "+50"
                    )

                    Text("Product Icon Stack", style = MaterialTheme.typography.titleSmall)
                    ProductIconStack(overflowCount = 2)
                }
            }

            // Section 5: Media & Interactive Cards
            ShowcaseCard(title = "Media & Interactive Cards") {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    DropdownHeader(
                        title = "Precio",
                        isExpanded = isDropdownExpanded,
                        onToggle = { isDropdownExpanded = !isDropdownExpanded }
                    )

                    CopyableText(
                        text = "emanuel.trujillo@yahoo.com",
                        onCopy = {}
                    )

                    Text("Writable Range Slider", style = MaterialTheme.typography.titleSmall)
                    WritableRangeSlider(
                        value = rangeValues,
                        onValueChange = { rangeValues = it }
                    )

                    Text("File Upload Cards", style = MaterialTheme.typography.titleSmall)
                    FileUploadCard(state = FileUploadState.Default, onFileSelect = {})
                    FileUploadCard(state = FileUploadState.Active, onFileSelect = {})
                    FileUploadCard(state = FileUploadState.Uploading, onFileSelect = {})

                    Text("Editable Avatar & Loyalty Badges", style = MaterialTheme.typography.titleSmall)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        EditableImage(showEditButton = true, onEditClick = {})
                        LoyaltyBadge(
                            icon = Icons.Default.CardGiftcard,
                            backgroundColor = Color(0xFFFFE082),
                            contentColor = Color(0xFF5D4037)
                        )
                        LoyaltyBadge(
                            icon = Icons.Default.Star,
                            backgroundColor = Color(0xFFE1BEE7),
                            contentColor = Color(0xFF4A148C)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowcaseCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            HorizontalDivider()
            content()
        }
    }
}

@Preview(name = "Component Showcase - Phone Light", device = Devices.PHONE, showBackground = true)
@Composable
fun ComponentShowcasePhonePreview() {
    MaterialTheme {
        ComponentShowcase()
    }
}

@Preview(name = "Component Showcase - Tablet Light", device = Devices.TABLET, showBackground = true)
@Composable
fun ComponentShowcaseTabletPreview() {
    MaterialTheme {
        ComponentShowcase()
    }
}
