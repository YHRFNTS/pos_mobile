package com.example.pos_mobile.feature.onboarding.ui

import androidx.compose.ui.graphics.Color
import com.example.pos_mobile.core.designsystem.components.media.FileUploadState

enum class OnboardingStep {
    CHOOSE_USE_TYPE,
    BUSINESS_INFO,
    LOGO_UPLOAD,
    COLOR_CUSTOMIZATION
}

enum class ColorTarget {
    PRIMARY,
    SECONDARY
}

data class OnboardingUiState(
    val currentStep: OnboardingStep = OnboardingStep.CHOOSE_USE_TYPE,
    val totalWizardSteps: Int = 6,
    val currentWizardStepIndex: Int = 1, // 1-based index for stepper indicator

    // Business Info (Step 1)
    val businessName: String = "",
    val businessNameError: String? = null,
    val selectedCategory: String = "",
    val isCategoryDropdownExpanded: Boolean = false,
    val availableCategories: List<String> = listOf(
        "Papelería",
        "Abarrotes",
        "Restaurante / Cafetería",
        "Ropa y Calzado",
        "Electrónica",
        "Salud y Belleza",
        "Ferretería",
        "Servicios",
        "Otro"
    ),

    // Logo Upload (Step 2)
    val logoUri: String? = null,
    val croppedLogoUri: String? = null,
    val fileUploadState: FileUploadState = FileUploadState.Default,
    val isUploadingLogo: Boolean = false,

    // Color Customization (Step 2 Branding)
    val primaryColor: Color = Color(0xFF4F46E5),
    val secondaryColor: Color = Color(0xFFFFFFFF),
    val savedColors: List<Color> = listOf(
        Color(0xFFEF4444), // Red
        Color(0xFFF97316), // Orange
        Color(0xFFFACC15), // Yellow
        Color(0xFF22C55E), // Green
        Color(0xFF06B6D4), // Cyan
        Color(0xFF3B82F6), // Blue
        Color(0xFF6366F1), // Indigo
        Color(0xFFA855F7), // Purple
        Color(0xFFEC4899), // Pink
        Color(0xFF8B5CF6), // Light Purple
        Color(0xFF0EA5E9), // Sky Blue
        Color(0xFF84CC16)  // Lime
    ),

    // Overlays / Modals
    val showImageCropDialog: Boolean = false,
    val showColorPickerDialog: Boolean = false,
    val colorPickerTarget: ColorTarget? = null,
    val selectedPickerColor: Color = Color(0xFF4F46E5),
    val hexColorInput: String = "4F46E5",
    val colorAlphaPercent: Int = 100,

    // Status
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
