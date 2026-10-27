package com.example.pos_mobile.ui.components.indicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
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

/**
 * Reusable Movement Detail Item (Branch transfer row: Origin -> Arrow -> Destination).
 */
@Composable
fun MovementDetailRow(
    originBranch: String?,
    originQuantity: String?,
    destinationBranch: String?,
    destinationQuantity: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = originBranch ?: "---",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            if (originQuantity != null) {
                Text(
                    text = originQuantity,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F)
                )
            }
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.CompareArrows,
            contentDescription = "Transferencia",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(28.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            if (destinationQuantity != null) {
                Text(
                    text = destinationQuantity,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF388E3C)
                )
            }
            Text(
                text = destinationBranch ?: "---",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(name = "Movement Detail Row Preview", showBackground = true)
@Composable
fun MovementDetailRowPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            MovementDetailRow(
                originBranch = "Sucursal centro",
                originQuantity = "-50",
                destinationBranch = "Sucursal sur",
                destinationQuantity = "+50"
            )
        }
    }
}
