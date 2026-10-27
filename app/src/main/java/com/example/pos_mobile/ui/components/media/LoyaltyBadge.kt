package com.example.pos_mobile.ui.components.media

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Loyalty / Gift Badge component.
 */
@Composable
fun LoyaltyBadge(
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .size(56.dp)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Preview(name = "Loyalty Badge Preview", showBackground = true)
@Composable
fun LoyaltyBadgePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row {
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
