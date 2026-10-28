package com.example.pos_mobile.core.designsystem.components.selection

import androidx.annotation.DrawableRes
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

/**
 * Reusable Category Pill with soft pastel colors and optional category icon resource.
 */
@Composable
fun CategoryPill(
    label: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFFFFDF7A),
    contentColor: Color = Color(0xFF423200),
    @DrawableRes iconRes: Int? = null,
    icon: @Composable (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = containerColor,
        contentColor = contentColor,
    ) {
        Box(
            modifier = Modifier.padding(start = 48.dp, end = 24.dp, top = 2.dp, bottom = 2.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (icon != null) {
                icon()
            } else if (iconRes != null) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = contentColor.copy(alpha = 0.22f),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = (-30).dp, y = 0.dp)
                        .size(0.dp)
                        .requiredSize(44.dp)
                        .rotate(15f),
                )
            }

            Text(
                text = label,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor,
            )
        }
    }
}

@Preview(name = "Category Pill Preview", showBackground = true)
@Composable
fun CategoryPillPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                CategoryPill(
                    label = "Papelería",
                    containerColor = Color(0xFFFFDF7A),
                    contentColor = Color(0xFF423200),
                    iconRes = R.drawable.ic_paperclip,
                )
                CategoryPill(
                    label = "Manualidades",
                    containerColor = Color(0xFFEA8DF7),
                    contentColor = Color(0xFF6B007B),
                    iconRes = R.drawable.ic_palette,
                )
                CategoryPill(
                    label = "Snacks",
                    containerColor = Color(0xFFF79997),
                    contentColor = Color(0xFF800010),
                    iconRes = R.drawable.ic_snacks,
                )
            }
        }
    }
}
