package com.example.pos_mobile.ui.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.ui.theme.Variables

@Composable
fun ModeToggleButton(
    modifier: Modifier = Modifier,
    isDashboardSelected: Boolean = true,
    onModeChange: (Boolean) -> Unit = {},
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .width(64.dp)
            .height(85.dp)
            .background(
                color = Variables.SchemesSurfaceContainerHigh,
                shape = RoundedCornerShape(Variables.CornerSmall)
            )
            .padding(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (content != null) {
            content()
        } else {
            // Botón Superior (Modo Dashboard / Gestor)
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(39.dp)
                    .shadow(
                        elevation = if (isDashboardSelected) 2.dp else 0.dp,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        color = if (isDashboardSelected) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onModeChange(true) },
                contentAlignment = Alignment.Center
            ) {
                // Icono tipo Dashboard (Grid / Columnas de Figma)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 8.dp, height = 6.dp)
                                .background(
                                    color = if (isDashboardSelected) Color(0xFF65558F) else Color(0xFF79747E),
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                        Box(
                            modifier = Modifier
                                .size(width = 8.dp, height = 10.dp)
                                .background(
                                    color = if (isDashboardSelected) Color(0xFF65558F) else Color(0xFF79747E),
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(width = 7.dp, height = 19.dp)
                            .background(
                                color = if (isDashboardSelected) Color(0xFF65558F) else Color(0xFF79747E),
                                shape = RoundedCornerShape(3.dp)
                            )
                    )
                }
            }

            // Botón Inferior (Modo POS / Carrito de Compras)
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(39.dp)
                    .shadow(
                        elevation = if (!isDashboardSelected) 2.dp else 0.dp,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .background(
                        color = if (!isDashboardSelected) Color.White else Color.Transparent,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onModeChange(false) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Modo Ventas",
                    tint = if (!isDashboardSelected) Color(0xFF65558F) else Color(0xFF79747E),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
