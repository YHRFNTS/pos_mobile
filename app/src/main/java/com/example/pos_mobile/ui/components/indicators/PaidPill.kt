package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
 * Reusable Paid Pill ("Pagado" / "Pendiente").
 */
@Composable
fun PaidPill(
    isPaid: Boolean,
    modifier: Modifier = Modifier
) {
    if (isPaid) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFD0E1FD),
            contentColor = Color(0xFF1565C0)
        ) {
            Text(
                text = "Pagado",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    } else {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(12.dp),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.outline
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Pendiente",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(name = "Paid Pill Preview", showBackground = true)
@Composable
fun PaidPillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row {
                PaidPill(isPaid = true)
            }
        }
    }
}
