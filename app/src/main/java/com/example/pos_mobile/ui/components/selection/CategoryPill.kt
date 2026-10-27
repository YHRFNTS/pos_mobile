package com.example.pos_mobile.ui.components.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Category Pill with icon/emoji and soft pastel colors.
 */
@Composable
fun CategoryPill(
    label: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFFFFECB3),
    contentColor: Color = Color(0xFF5D4037),
    icon: @Composable (() -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = containerColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (icon != null) {
                icon()
            }
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(name = "Category Pill Preview", showBackground = true)
@Composable
fun CategoryPillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryPill(label = "Papelería", containerColor = Color(0xFFFFECB3), contentColor = Color(0xFF5D4037))
                CategoryPill(label = "Manualidades", containerColor = Color(0xFFF8BBD0), contentColor = Color(0xFF880E4F))
                CategoryPill(label = "Snacks", containerColor = Color(0xFFFFCCBC), contentColor = Color(0xFFBF360C))
            }
        }
    }
}
