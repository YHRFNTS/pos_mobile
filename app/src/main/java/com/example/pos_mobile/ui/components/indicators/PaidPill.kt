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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

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
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ) {
            Text(
                text = stringResource(R.string.paid),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
            )
        }
    } else {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.small,
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.outline
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.small)
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(
                    text = stringResource(R.string.pending),
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
                PaidPill(isPaid = false)
            }
        }
    }
}
