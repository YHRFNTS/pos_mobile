package com.example.pos_mobile.feature.onboarding.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pos_mobile.feature.onboarding.ui.components.BusinessInfoContent
import com.example.pos_mobile.feature.onboarding.ui.components.ChooseUseTypeContent
import com.example.pos_mobile.feature.onboarding.ui.components.ColorCustomizationContent
import com.example.pos_mobile.feature.onboarding.ui.components.ColorPickerDialog
import com.example.pos_mobile.feature.onboarding.ui.components.ImageCropDialog
import com.example.pos_mobile.feature.onboarding.ui.components.LogoUploadContent
import com.example.pos_mobile.feature.onboarding.ui.components.OnboardingBackground
import com.example.pos_mobile.feature.onboarding.ui.components.OnboardingHeaderStepper

/**
 * Main Stateful Onboarding Screen.
 */
@Composable
fun OnboardingRoute(
    onNavigateToAuth: () -> Unit = {},
    onOnboardingComplete: () -> Unit = {},
    viewModel: OnboardingViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    OnboardingScreen(
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                OnboardingUiEvent.OnLoginClick -> onNavigateToAuth()
                else -> viewModel.onEvent(event)
            }
        }
    )
}

/**
 * Main Stateless Onboarding Screen.
 */
@Composable
fun OnboardingScreen(
    uiState: OnboardingUiState,
    onEvent: (OnboardingUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        OnboardingBackground(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header Stepper Indicator (for steps 1+)
                if (uiState.currentStep != OnboardingStep.CHOOSE_USE_TYPE) {
                    OnboardingHeaderStepper(
                        currentStepIndex = uiState.currentWizardStepIndex,
                        totalSteps = uiState.totalWizardSteps,
                        onBackClick = { onEvent(OnboardingUiEvent.OnBackClick) }
                    )
                }

                // Step Content Switcher with smooth transition
                Box(modifier = Modifier.weight(1f)) {
                    AnimatedContent(
                        targetState = uiState.currentStep,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "onboardingStepTransition"
                    ) { step ->
                        when (step) {
                            OnboardingStep.CHOOSE_USE_TYPE -> {
                                ChooseUseTypeContent(
                                    onStartClick = { onEvent(OnboardingUiEvent.OnStartClick) }
                                )
                            }

                            OnboardingStep.BUSINESS_INFO -> {
                                BusinessInfoContent(
                                    businessName = uiState.businessName,
                                    onBusinessNameChange = { name -> onEvent(OnboardingUiEvent.OnBusinessNameChange(name)) },
                                    businessNameError = uiState.businessNameError,
                                    selectedCategory = uiState.selectedCategory,
                                    onCategorySelected = { category -> onEvent(OnboardingUiEvent.OnCategorySelected(category)) },
                                    availableCategories = uiState.availableCategories,
                                    isCategoryDropdownExpanded = uiState.isCategoryDropdownExpanded,
                                    onCategoryDropdownToggle = { expanded -> onEvent(OnboardingUiEvent.OnCategoryDropdownToggle(expanded)) },
                                    onContinueClick = { onEvent(OnboardingUiEvent.OnContinueClick) },
                                    onLoginClick = { onEvent(OnboardingUiEvent.OnLoginClick) }
                                )
                            }

                            OnboardingStep.LOGO_UPLOAD -> {
                                LogoUploadContent(
                                    fileUploadState = uiState.fileUploadState,
                                    onSelectLogoClick = {
                                        onEvent(OnboardingUiEvent.OnSelectLogoUri("sample_logo_uri"))
                                    },
                                    onSkipClick = { onEvent(OnboardingUiEvent.OnSkipClick) },
                                    onContinueClick = { onEvent(OnboardingUiEvent.OnContinueClick) }
                                )
                            }

                            OnboardingStep.COLOR_CUSTOMIZATION -> {
                                ColorCustomizationContent(
                                    primaryColor = uiState.primaryColor,
                                    secondaryColor = uiState.secondaryColor,
                                    onOpenColorPicker = { target -> onEvent(OnboardingUiEvent.OnOpenColorPicker(target)) },
                                    onSkipClick = { onEvent(OnboardingUiEvent.OnSkipClick) },
                                    onContinueClick = { onEvent(OnboardingUiEvent.OnContinueClick) },
                                    businessLogoName = uiState.businessName.ifEmpty { "TAÏSO" }
                                )
                            }
                        }
                    }
                }
            }

            // Image Crop Dialog Overlay
            if (uiState.showImageCropDialog) {
                ImageCropDialog(
                    logoUri = uiState.logoUri,
                    onUploadAnotherClick = { onEvent(OnboardingUiEvent.OnUploadAnotherImageClick) },
                    onCancelClick = { onEvent(OnboardingUiEvent.OnCropCancelClick) },
                    onSaveClick = { croppedUri -> onEvent(OnboardingUiEvent.OnCropSaveClick(croppedUri)) }
                )
            }

            // Color Picker Dialog Overlay
            if (uiState.showColorPickerDialog) {
                ColorPickerDialog(
                    selectedColor = uiState.selectedPickerColor,
                    savedColors = uiState.savedColors,
                    hexInput = uiState.hexColorInput,
                    onColorSelected = { color -> onEvent(OnboardingUiEvent.OnPickerColorSelected(color)) },
                    onHexInputChanged = { hex -> onEvent(OnboardingUiEvent.OnHexColorInputChanged(hex)) },
                    onAddSavedColor = { color -> onEvent(OnboardingUiEvent.OnAddColorToSaved(color)) },
                    onDismissRequest = { onEvent(OnboardingUiEvent.OnCloseColorPicker) }
                )
            }
        }
    }
}

@Preview(name = "Onboarding Screen Choose Use Type", widthDp = 900, heightDp = 500, showBackground = true)
@Composable
fun OnboardingScreenChooseUseTypePreview() {
    MaterialTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(currentStep = OnboardingStep.CHOOSE_USE_TYPE),
            onEvent = {}
        )
    }
}

@Preview(name = "Onboarding Screen Business Info", widthDp = 900, heightDp = 500, showBackground = true)
@Composable
fun OnboardingScreenBusinessInfoPreview() {
    MaterialTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(
                currentStep = OnboardingStep.BUSINESS_INFO,
                businessName = "Papelería El Orígen"
            ),
            onEvent = {}
        )
    }
}

@Preview(name = "Onboarding Screen Logo Upload", widthDp = 900, heightDp = 500, showBackground = true)
@Composable
fun OnboardingScreenLogoUploadPreview() {
    MaterialTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(
                currentStep = OnboardingStep.LOGO_UPLOAD,
                currentWizardStepIndex = 2
            ),
            onEvent = {}
        )
    }
}

@Preview(name = "Onboarding Screen Color Customization", widthDp = 900, heightDp = 500, showBackground = true)
@Composable
fun OnboardingScreenColorCustomizationPreview() {
    MaterialTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(
                currentStep = OnboardingStep.COLOR_CUSTOMIZATION,
                currentWizardStepIndex = 2
            ),
            onEvent = {}
        )
    }
}
