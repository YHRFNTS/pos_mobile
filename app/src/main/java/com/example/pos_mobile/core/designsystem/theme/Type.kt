package com.example.pos_mobile.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.pos_mobile.R

val SoraFontFamily = FontFamily(
    Font(R.font.sora, FontWeight.Normal),
    Font(R.font.sora_medium, FontWeight.Medium),
    Font(R.font.sora_semibold, FontWeight.SemiBold),
    Font(R.font.sora_bold, FontWeight.Bold)
)

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

private val defaultTypography = Typography()

val AppTypography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = SoraFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = SoraFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = SoraFontFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = SoraFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = SoraFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = SoraFontFamily),
    titleLarge = defaultTypography.titleLarge,
    titleMedium = defaultTypography.titleMedium,
    titleSmall = defaultTypography.titleSmall,
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = RobotoFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = RobotoFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = RobotoFontFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = RobotoFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = RobotoFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = RobotoFontFamily)
)
