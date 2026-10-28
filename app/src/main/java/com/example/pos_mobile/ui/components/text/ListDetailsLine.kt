package com.example.pos_mobile.ui.components.text

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.R
import com.example.pos_mobile.ui.theme.AppTheme

enum class ListDetailsLineSize {
    Small,
    Medium,
    Large,
}

@Composable
fun ListDetailsLine(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    size: ListDetailsLineSize = ListDetailsLineSize.Small,
    labelStyle: TextStyle = when (size) {
        ListDetailsLineSize.Small -> MaterialTheme.typography.labelSmall
        ListDetailsLineSize.Medium -> MaterialTheme.typography.labelLarge
        ListDetailsLineSize.Large -> MaterialTheme.typography.bodyLarge
    },
    valueStyle: TextStyle = when (size) {
        ListDetailsLineSize.Small -> MaterialTheme.typography.labelLarge
        ListDetailsLineSize.Medium -> MaterialTheme.typography.bodyLarge
        ListDetailsLineSize.Large -> MaterialTheme.typography.titleLarge
    },
    labelColor: Color = MaterialTheme.colorScheme.onSurface,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    lineColor: Color = MaterialTheme.colorScheme.outlineVariant,
    dashLength: Dp = 16.dp,
    dashGap: Dp = 12.dp,
    strokeWidth: Dp = 1.5.dp,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = labelStyle,
            color = labelColor,
        )

        DashedLeaderLine(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            color = lineColor,
            dashLength = dashLength,
            dashGap = dashGap,
            strokeWidth = strokeWidth,
        )

        Text(
            text = value,
            style = valueStyle,
            color = valueColor,
        )
    }
}

@Composable
private fun DashedLeaderLine(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outlineVariant,
    dashLength: Dp = 16.dp,
    dashGap: Dp = 12.dp,
    strokeWidth: Dp = 1.5.dp,
) {
    Canvas(
        modifier = modifier.height(strokeWidth),
    ) {
        val pathEffect = PathEffect.dashPathEffect(
            floatArrayOf(dashLength.toPx(), dashGap.toPx()),
            0f,
        )
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = strokeWidth.toPx(),
            pathEffect = pathEffect,
            cap = StrokeCap.Square,
        )
    }
}

@Preview(name = "List Details Line Preview", showBackground = true)
@Composable
fun ListDetailsLinePreview() {
    AppTheme(dynamicColor = false) {
        Surface(
            color = Color.White,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp),
            ) {
                ListDetailsLine(
                    label = stringResource(R.string.list_details_subtotal),
                    value = stringResource(R.string.list_details_sample_price),
                    size = ListDetailsLineSize.Small,
                )

                ListDetailsLine(
                    label = stringResource(R.string.list_details_subtotal),
                    value = stringResource(R.string.list_details_sample_price),
                    size = ListDetailsLineSize.Medium,
                )

                ListDetailsLine(
                    label = stringResource(R.string.list_details_subtotal),
                    value = stringResource(R.string.list_details_sample_price),
                    size = ListDetailsLineSize.Large,
                )
            }
        }
    }
}
