package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
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

enum class MovementType {
    Venta, Restock, Transferencia
}

private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

/**
 * Reusable Movement Type Pill ("Venta", "Restock", "Transferencia").
 */
@Composable
fun MovementTypePill(
    type: MovementType,
    modifier: Modifier = Modifier
) {
    val (icon, bg, fg, label) = when (type) {
        MovementType.Venta -> Quadruple(Icons.Default.ShoppingCart, Color(0xFFF2F2F2), Color(0xFF616161), "Venta")
        MovementType.Restock -> Quadruple(Icons.Default.Inventory2, Color(0xFFEDE7F6), Color(0xFF512DA8), "Restock")
        MovementType.Transferencia -> Quadruple(Icons.AutoMirrored.Filled.CompareArrows, Color(0xFFE1F5FE), Color(0xFF0288D1), "Transferencia")
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = bg,
        contentColor = fg
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(name = "Movement Type Pill Preview", showBackground = true)
@Composable
fun MovementTypePillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MovementTypePill(type = MovementType.Venta)
                MovementTypePill(type = MovementType.Restock)
                MovementTypePill(type = MovementType.Transferencia)
            }
        }
    }
}
