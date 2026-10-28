package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Price Badge showing price, promotional price, and crossed out original price.
 */
@Composable
fun PriceBadge(
    price: String,
    modifier: Modifier = Modifier,
    originalPrice: String? = null
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            )
        ) {
            append(price)
        }
        if (originalPrice != null) {
            append(" ")
            withStyle(
                SpanStyle(
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.outline,
                    textDecoration = TextDecoration.LineThrough,
                    baselineShift = BaselineShift.Subscript
                )
            ) {
                append(originalPrice)
            }
        }
    }

    Text(
        text = annotatedText,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(name = "Price Badge Preview", showBackground = true)
@Composable
fun PriceBadgePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            PriceBadge(price = "$10",originalPrice = "$100")
        }
    }
}
