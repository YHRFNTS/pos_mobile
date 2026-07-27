package com.example.pos_mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import com.example.pos_mobile.ui.components.navigation.BusinessIcon
import com.example.pos_mobile.ui.components.navigation.GetStartedButton
import com.example.pos_mobile.ui.components.navigation.ModeToggleButton
import com.example.pos_mobile.ui.components.navigation.NavAvatarButton
import com.example.pos_mobile.ui.components.navigation.NavIconButton
import com.example.pos_mobile.ui.components.navigation.NavItem
import com.example.pos_mobile.ui.components.navigation.NavRail
import com.example.pos_mobile.ui.components.navigation.TopBar
import com.example.pos_mobile.ui.theme.Variables

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(1280.dp)
            .height(800.dp)
            .background(color = Variables.SchemesBackground)
    ) {
        NavRail(
            bottomContent = {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp))
                NavAvatarButton()
            }
        ) {
            BusinessIcon()
            HorizontalDivider()
            ModeToggleButton()
            NavIconButton()
            NavItem(label = "Inicio", icon = Icons.Default.Home, selected = true)
            NavItem(label = "Ventas", icon = Icons.Default.ShoppingBag, selected = false)
            NavItem(label = "Clientes", icon = Icons.Default.People, selected = false)
            NavItem(label = "Recursos\nHumanos", icon = Icons.Default.Badge, selected = false)
            NavItem(label = "Configuración", icon = Icons.Default.Settings, selected = false)
            Spacer(modifier = Modifier.height(49.dp))
            GetStartedButton()
        }
        Column {
            TopBar()
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 800,
    device = Devices.TABLET
)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}