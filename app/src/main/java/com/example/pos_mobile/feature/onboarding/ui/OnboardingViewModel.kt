package com.example.pos_mobile.feature.onboarding.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pos_mobile.core.designsystem.components.media.FileUploadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    fun onEvent(event: OnboardingUiEvent) {
        when (event) {
            OnboardingUiEvent.OnStartClick -> {
                _uiState.update {
                    it.copy(
                        currentStep = OnboardingStep.BUSINESS_INFO,
                        currentWizardStepIndex = 1
                    )
                }
            }

            OnboardingUiEvent.OnBackClick -> {
                handleBackNavigation()
            }

            OnboardingUiEvent.OnContinueClick -> {
                handleContinueNavigation()
            }

            OnboardingUiEvent.OnSkipClick -> {
                handleSkipNavigation()
            }

            OnboardingUiEvent.OnLoginClick -> {
                // Trigger login flow or callback
            }

            is OnboardingUiEvent.OnBusinessNameChange -> {
                _uiState.update {
                    it.copy(
                        businessName = event.name,
                        businessNameError = if (event.name.isBlank()) "El nombre del negocio es obligatorio" else null
                    )
                }
            }

            is OnboardingUiEvent.OnCategorySelected -> {
                _uiState.update {
                    it.copy(
                        selectedCategory = event.category,
                        isCategoryDropdownExpanded = false
                    )
                }
            }

            is OnboardingUiEvent.OnCategoryDropdownToggle -> {
                _uiState.update {
                    it.copy(isCategoryDropdownExpanded = event.expanded)
                }
            }

            is OnboardingUiEvent.OnSelectLogoUri -> {
                _uiState.update {
                    it.copy(
                        logoUri = event.uri,
                        showImageCropDialog = true,
                        fileUploadState = FileUploadState.Active
                    )
                }
            }

            OnboardingUiEvent.OnUploadAnotherImageClick -> {
                _uiState.update {
                    it.copy(
                        showImageCropDialog = false,
                        fileUploadState = FileUploadState.Default
                    )
                }
            }

            OnboardingUiEvent.OnCropCancelClick -> {
                _uiState.update {
                    it.copy(showImageCropDialog = false)
                }
            }

            is OnboardingUiEvent.OnCropSaveClick -> {
                _uiState.update {
                    it.copy(
                        croppedLogoUri = event.croppedUri,
                        showImageCropDialog = false,
                        currentStep = OnboardingStep.COLOR_CUSTOMIZATION,
                        fileUploadState = FileUploadState.Default
                    )
                }
            }

            is OnboardingUiEvent.OnOpenColorPicker -> {
                val initialColor = if (event.target == ColorTarget.PRIMARY) {
                    _uiState.value.primaryColor
                } else {
                    _uiState.value.secondaryColor
                }
                _uiState.update {
                    it.copy(
                        showColorPickerDialog = true,
                        colorPickerTarget = event.target,
                        selectedPickerColor = initialColor,
                        hexColorInput = colorToHex(initialColor)
                    )
                }
            }

            OnboardingUiEvent.OnCloseColorPicker -> {
                _uiState.update {
                    it.copy(
                        showColorPickerDialog = false,
                        colorPickerTarget = null
                    )
                }
            }

            is OnboardingUiEvent.OnPickerColorSelected -> {
                _uiState.update { state ->
                    val updatedState = when (state.colorPickerTarget) {
                        ColorTarget.PRIMARY -> state.copy(primaryColor = event.color)
                        ColorTarget.SECONDARY -> state.copy(secondaryColor = event.color)
                        null -> state
                    }
                    updatedState.copy(
                        selectedPickerColor = event.color,
                        hexColorInput = colorToHex(event.color)
                    )
                }
            }

            is OnboardingUiEvent.OnHexColorInputChanged -> {
                val hex = event.hex.removePrefix("#").trim()
                val parsedColor = parseHexColor(hex)
                _uiState.update { state ->
                    var newState = state.copy(hexColorInput = hex)
                    if (parsedColor != null) {
                        newState = when (state.colorPickerTarget) {
                            ColorTarget.PRIMARY -> newState.copy(primaryColor = parsedColor, selectedPickerColor = parsedColor)
                            ColorTarget.SECONDARY -> newState.copy(secondaryColor = parsedColor, selectedPickerColor = parsedColor)
                            null -> newState
                        }
                    }
                    newState
                }
            }

            is OnboardingUiEvent.OnAddColorToSaved -> {
                _uiState.update { state ->
                    if (!state.savedColors.contains(event.color)) {
                        state.copy(savedColors = state.savedColors + event.color)
                    } else {
                        state
                    }
                }
            }
        }
    }

    private fun handleBackNavigation() {
        _uiState.update { state ->
            when (state.currentStep) {
                OnboardingStep.CHOOSE_USE_TYPE -> state
                OnboardingStep.BUSINESS_INFO -> state.copy(
                    currentStep = OnboardingStep.CHOOSE_USE_TYPE,
                    currentWizardStepIndex = 1
                )
                OnboardingStep.LOGO_UPLOAD -> state.copy(
                    currentStep = OnboardingStep.BUSINESS_INFO,
                    currentWizardStepIndex = 1
                )
                OnboardingStep.COLOR_CUSTOMIZATION -> state.copy(
                    currentStep = OnboardingStep.LOGO_UPLOAD,
                    currentWizardStepIndex = 2
                )
            }
        }
    }

    private fun handleContinueNavigation() {
        _uiState.update { state ->
            when (state.currentStep) {
                OnboardingStep.CHOOSE_USE_TYPE -> state.copy(
                    currentStep = OnboardingStep.BUSINESS_INFO,
                    currentWizardStepIndex = 1
                )
                OnboardingStep.BUSINESS_INFO -> {
                    if (state.businessName.isBlank()) {
                        state.copy(businessNameError = "El nombre del negocio es obligatorio")
                    } else {
                        state.copy(
                            currentStep = OnboardingStep.LOGO_UPLOAD,
                            currentWizardStepIndex = 2
                        )
                    }
                }
                OnboardingStep.LOGO_UPLOAD -> state.copy(
                    currentStep = OnboardingStep.COLOR_CUSTOMIZATION,
                    currentWizardStepIndex = 2
                )
                OnboardingStep.COLOR_CUSTOMIZATION -> state
            }
        }
    }

    private fun handleSkipNavigation() {
        _uiState.update { state ->
            when (state.currentStep) {
                OnboardingStep.LOGO_UPLOAD -> state.copy(
                    currentStep = OnboardingStep.COLOR_CUSTOMIZATION,
                    currentWizardStepIndex = 2
                )
                OnboardingStep.COLOR_CUSTOMIZATION -> state
                else -> state
            }
        }
    }

    private fun colorToHex(color: Color): String {
        val r = (color.red * 255).toInt()
        val g = (color.green * 255).toInt()
        val b = (color.blue * 255).toInt()
        return String.format("%02X%02X%02X", r, g, b)
    }

    private fun parseHexColor(hex: String): Color? {
        return try {
            val colorInt = android.graphics.Color.parseColor("#$hex")
            Color(colorInt)
        } catch (_: Exception) {
            null
        }
    }
}
