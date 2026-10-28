package com.example.pos_mobile.core.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Liquor
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pos_mobile.R
import com.example.pos_mobile.core.designsystem.theme.categoryAmberContainer
import com.example.pos_mobile.core.designsystem.theme.categoryAmberPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryBlueContainer
import com.example.pos_mobile.core.designsystem.theme.categoryBluePrimary
import com.example.pos_mobile.core.designsystem.theme.categoryBrownContainer
import com.example.pos_mobile.core.designsystem.theme.categoryBrownPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryCyanContainer
import com.example.pos_mobile.core.designsystem.theme.categoryCyanPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryGreenContainer
import com.example.pos_mobile.core.designsystem.theme.categoryGreenPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryIndigoContainer
import com.example.pos_mobile.core.designsystem.theme.categoryIndigoPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryOrangeContainer
import com.example.pos_mobile.core.designsystem.theme.categoryOrangePrimary
import com.example.pos_mobile.core.designsystem.theme.categoryPinkContainer
import com.example.pos_mobile.core.designsystem.theme.categoryPinkPrimary
import com.example.pos_mobile.core.designsystem.theme.categoryPurpleContainer
import com.example.pos_mobile.core.designsystem.theme.categoryPurplePrimary
import com.example.pos_mobile.core.designsystem.theme.categoryRedContainer
import com.example.pos_mobile.core.designsystem.theme.categoryRedPrimary
import com.example.pos_mobile.core.designsystem.theme.categorySlateContainer
import com.example.pos_mobile.core.designsystem.theme.categorySlatePrimary
import com.example.pos_mobile.core.designsystem.theme.categoryTealContainer
import com.example.pos_mobile.core.designsystem.theme.categoryTealPrimary

enum class CategoryColor(
    val id: String,
    @StringRes val labelRes: Int,
    val primary: Color,
    val container: Color
) {
    RED("red", R.string.category_color_red, categoryRedPrimary, categoryRedContainer),
    ORANGE("orange", R.string.category_color_orange, categoryOrangePrimary, categoryOrangeContainer),
    AMBER("amber", R.string.category_color_amber, categoryAmberPrimary, categoryAmberContainer),
    GREEN("green", R.string.category_color_green, categoryGreenPrimary, categoryGreenContainer),
    TEAL("teal", R.string.category_color_teal, categoryTealPrimary, categoryTealContainer),
    CYAN("cyan", R.string.category_color_cyan, categoryCyanPrimary, categoryCyanContainer),
    BLUE("blue", R.string.category_color_blue, categoryBluePrimary, categoryBlueContainer),
    INDIGO("indigo", R.string.category_color_indigo, categoryIndigoPrimary, categoryIndigoContainer),
    PURPLE("purple", R.string.category_color_purple, categoryPurplePrimary, categoryPurpleContainer),
    PINK("pink", R.string.category_color_pink, categoryPinkPrimary, categoryPinkContainer),
    BROWN("brown", R.string.category_color_brown, categoryBrownPrimary, categoryBrownContainer),
    SLATE("slate", R.string.category_color_slate, categorySlatePrimary, categorySlateContainer);

    companion object {
        fun fromId(id: String): CategoryColor =
            entries.find { it.id == id } ?: SLATE
    }
}

enum class CategoryIcon(
    val id: String,
    @StringRes val labelRes: Int,
    val icon: ImageVector
) {
    RESTAURANT("restaurant", R.string.category_icon_restaurant, Icons.Default.Restaurant),
    FAST_FOOD("fast_food", R.string.category_icon_fast_food, Icons.Default.Fastfood),
    PIZZA("pizza", R.string.category_icon_pizza, Icons.Default.LocalPizza),
    BAKERY("bakery", R.string.category_icon_bakery, Icons.Default.BakeryDining),
    DESSERT("dessert", R.string.category_icon_dessert, Icons.Default.Icecream),
    COFFEE("coffee", R.string.category_icon_coffee, Icons.Default.Coffee),
    DRINKS("drinks", R.string.category_icon_drinks, Icons.Default.Liquor),

    GROCERIES("groceries", R.string.category_icon_groceries, Icons.Default.ShoppingBasket),
    FRESH("fresh", R.string.category_icon_fresh, Icons.Default.Eco),
    DAIRY("dairy", R.string.category_icon_dairy, Icons.Default.Kitchen),
    CLEANING("cleaning", R.string.category_icon_cleaning, Icons.Default.CleaningServices),

    CLOTHING("clothing", R.string.category_icon_clothing, Icons.Default.Checkroom),
    ACCESSORIES("accessories", R.string.category_icon_accessories, Icons.Default.ShoppingBag),
    GIFTS("gifts", R.string.category_icon_gifts, Icons.Default.CardGiftcard),
    STATIONERY("stationery", R.string.category_icon_stationery, Icons.Default.AutoStories),

    PHARMACY("pharmacy", R.string.category_icon_pharmacy, Icons.Default.MedicalServices),
    BEAUTY("beauty", R.string.category_icon_beauty, Icons.Default.Spa),
    PETS("pets", R.string.category_icon_pets, Icons.Default.Pets),
    HARDWARE("hardware", R.string.category_icon_hardware, Icons.Default.Build),
    ELECTRONICS("electronics", R.string.category_icon_electronics, Icons.Default.Devices),

    SERVICES("services", R.string.category_icon_services, Icons.Default.ContentCut),
    PROMO("promo", R.string.category_icon_promo, Icons.Default.Percent),
    FEATURED("featured", R.string.category_icon_featured, Icons.Default.Star),
    DEFAULT("default", R.string.category_icon_default, Icons.Default.LocalOffer);

    companion object {
        fun fromId(id: String): CategoryIcon =
            entries.find { it.id == id } ?: DEFAULT
    }
}
