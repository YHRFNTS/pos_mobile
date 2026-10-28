package com.example.pos_mobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val SoraFont = GoogleFont("Sora")
val RobotoFont = GoogleFont("Roboto")

val SoraFontFamily = FontFamily(
    Font(googleFont = SoraFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = SoraFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = SoraFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = SoraFont, fontProvider = provider, weight = FontWeight.Bold)
)

val RobotoFontFamily = FontFamily(
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = RobotoFont, fontProvider = provider, weight = FontWeight.Bold)
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
