package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.R

enum class MovementType {
    Venta, Restock, Transferencia
}

private data class PillStyle(
    val iconRes: Int,
    val backgroundColor: Color,
    val textColor: Color,
    val iconTint: Color,
    val border: BorderStroke?,
    val label: String
)

/**
 * Reusable Movement Type Pill ("Venta", "Restock", "Transferencia").
 */
@Composable
fun MovementTypePill(
    type: MovementType,
    modifier: Modifier = Modifier
) {
    val style = when (type) {
        MovementType.Venta -> PillStyle(
            iconRes = R.drawable.cart_outlined,
            backgroundColor = Color.White,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            iconTint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.22f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            label = "Venta"
        )
        MovementType.Restock -> PillStyle(
            iconRes = R.drawable.box_add_filled,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            iconTint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.22f),
            border = null,
            label = "Restock"
        )
        MovementType.Transferencia -> PillStyle(
            iconRes = R.drawable.transfer_icon_outline,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
            iconTint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.22f),
            border = null,
            label = "Transferencia"
        )
    }

    Surface(
        shape = CircleShape,
        color = style.backgroundColor,
        border = style.border,
    ) {
        Box(
            modifier = Modifier.padding(start = 45.dp, end = 33.dp, top = 4.dp, bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(style.iconRes),
                contentDescription = null,
                tint = style.iconTint,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (-25).dp, y = 0.dp)
                    .size(0.dp)
                    .requiredSize(32.dp)
                    .rotate(20f)
            )
            Text(
                text = style.label,
                style = MaterialTheme.typography.labelMedium,
                color = style.textColor,
            )
        }
    }
}

@Preview(name = "Movement Type Pill Preview", showBackground = true)
@Composable
fun MovementTypePillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                MovementTypePill(type = MovementType.Venta)
                MovementTypePill(type = MovementType.Restock)
                MovementTypePill(type = MovementType.Transferencia)
            }
        }
    }
}

