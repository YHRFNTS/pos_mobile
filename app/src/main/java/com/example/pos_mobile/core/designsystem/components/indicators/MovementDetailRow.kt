package com.example.pos_mobile.core.designsystem.components.indicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.theme.ExtendedColorScheme
import com.example.pos_mobile.core.designsystem.theme.extendedColorScheme

/**
 * Reusable Movement Detail Item (Branch transfer row: Origin -> Arrow -> Destination).
 */
@Composable
fun MovementDetailRow(
    originWarehouse: String?,
    originQuantity: String?,
    destinationWarehouse: String?,
    destinationQuantity: String?,
    modifier: Modifier = Modifier
) {
    val hasOrigin = !originWarehouse.isNullOrBlank() || !originQuantity.isNullOrBlank()
    val hasDestination = !destinationWarehouse.isNullOrBlank() || !destinationQuantity.isNullOrBlank()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasOrigin) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (!originWarehouse.isNullOrBlank()) {
                    Text(
                        text = originWarehouse,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }

                if (!originQuantity.isNullOrBlank()) {
                    Text(
                        text = originQuantity,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "---",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.triple_forward_icon),
            contentDescription = stringResource(R.string.movement_transfer_description),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(22.dp)
        )

        if (hasDestination) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (!destinationQuantity.isNullOrBlank()) {
                    Text(
                        text = destinationQuantity,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.extendedColorScheme.sucess.color,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                }

                if (!destinationWarehouse.isNullOrBlank()) {
                    Text(
                        text = destinationWarehouse,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        } else {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "---",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(name = "Movement Detail Row Preview", showBackground = true)
@Composable
fun MovementDetailRowPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp).width(230.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Entry (No origin)
                MovementDetailRow(
                    originWarehouse = null,
                    originQuantity = null,
                    destinationWarehouse = "Sucursal sur",
                    destinationQuantity = "+50"
                )

                // Exit (No destination)
                MovementDetailRow(
                    originWarehouse = "Sucursal centro",
                    originQuantity = "-50",
                    destinationWarehouse = null,
                    destinationQuantity = null
                )

                // Transfer (Both present)
                MovementDetailRow(
                    originWarehouse = "Sucursal centro",
                    originQuantity = "-50",
                    destinationWarehouse = "Sucursal sur",
                    destinationQuantity = "+50"
                )
            }
        }
    }
}
