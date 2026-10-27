package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Price Badge showing price, promotional price, and crossed out original price.
 */
@Composable
fun PriceBadge(
    price: String,
    modifier: Modifier = Modifier,
    discountPrice: String? = null,
    originalPrice: String? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = price,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        if (discountPrice != null) {
            Text(
                text = discountPrice,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        if (originalPrice != null) {
            Text(
                text = originalPrice,
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(name = "Price Badge Preview", showBackground = true)
@Composable
fun PriceBadgePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            PriceBadge(price = "$10", discountPrice = "$30", originalPrice = "$100")
        }
    }
}
