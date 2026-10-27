package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Reusable Status Indicator Pill (e.g. "Activo").
 */
@Composable
fun IndicatorPill(
    modifier: Modifier = Modifier,
    label: String = "Activo",
    isActive: Boolean = true
) {
    val backgroundColor = if (isActive) Color(0xFFE8F5E9) else Color(0xFFF5F5F5)
    val textColor = if (isActive) Color(0xFF2E7D32) else Color(0xFF757575)

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        contentColor = textColor
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview(name = "Indicator Pill Preview", showBackground = true)
@Composable
fun IndicatorPillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row {
                IndicatorPill(label = "Activo", isActive = true)
            }
        }
    }
}
