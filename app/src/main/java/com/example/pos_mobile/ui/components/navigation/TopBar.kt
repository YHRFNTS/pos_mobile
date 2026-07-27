package com.example.pos_mobile.ui.components.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.ui.theme.Variables

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier
            .border(width = 1.dp, color = Variables.SchemesOutlineVariant)
            .padding(1.dp)
            .width(1184.dp)
            .height(68.dp)
            .padding(start = 37.dp, end = 67.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
