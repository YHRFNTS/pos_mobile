package com.example.pos_mobile.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pos_mobile.ui.theme.Variables

@Composable
fun GetStartedButton(
    modifier: Modifier = Modifier,
    progress: Float = 0.6f,
    percentageText: String = "%60",
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .width(79.dp)
            .height(83.dp)
            .background(
                color = Variables.SchemesPrimaryContainer,
                shape = RoundedCornerShape(Variables.CornerLarge)
            )
            .padding(start = 12.dp, top = 14.dp, end = 12.dp, bottom = 14.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (content != null) {
            content()
        } else {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(55.dp),
                    color = Color(0xFF65558F),
                    trackColor = Color(0xFFE8DEF8),
                    strokeWidth = 6.dp
                )
                Text(
                    text = percentageText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF21005D)
                )
            }
        }
    }
}
