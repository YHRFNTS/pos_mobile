package com.example.pos_mobile.feature.onboarding.ui

import androidx.compose.ui.graphics.Color

sealed interface OnboardingUiEvent {
    // Navigation / Flow actions
    data object OnStartClick : OnboardingUiEvent
    data object OnBackClick : OnboardingUiEvent
    data object OnSkipClick : OnboardingUiEvent
    data object OnContinueClick : OnboardingUiEvent
    data object OnLoginClick : OnboardingUiEvent

    // Business Info Step
    data class OnBusinessNameChange(val name: String) : OnboardingUiEvent
    data class OnCategorySelected(val category: String) : OnboardingUiEvent
    data class OnCategoryDropdownToggle(val expanded: Boolean) : OnboardingUiEvent

    // Logo Upload & Crop
    data class OnSelectLogoUri(val uri: String) : OnboardingUiEvent
    data object OnUploadAnotherImageClick : OnboardingUiEvent
    data object OnCropCancelClick : OnboardingUiEvent
    data class OnCropSaveClick(val croppedUri: String) : OnboardingUiEvent

    // Color Customization
    data class OnOpenColorPicker(val target: ColorTarget) : OnboardingUiEvent
    data object OnCloseColorPicker : OnboardingUiEvent
    data class OnPickerColorSelected(val color: Color) : OnboardingUiEvent
    data class OnHexColorInputChanged(val hex: String) : OnboardingUiEvent
    data class OnAddColorToSaved(val color: Color) : OnboardingUiEvent
}
