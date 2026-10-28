package com.example.pos_mobile.ui.components.indicators

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

/**
 * Data model representing a product image source.
 * Can be loaded from a [Painter], a drawable resource ID ([imageRes]), a pre-loaded [ImageBitmap], or a local file [path].
 */
data class ProductImage(
    val painter: Painter? = null,
    @DrawableRes val imageRes: Int? = null,
    val bitmap: ImageBitmap? = null,
    val path: String? = null
)

/**
 * Reusable Product Icon Stack with 0 to 2 product image circles and Overflow badge (+X).
 * Supports loading images via optional painter, imageRes, bitmap, or local file path via [ProductImage].
 * Clickable element with ripple effect.
 */
@Composable
fun ProductIconStack(
    modifier: Modifier = Modifier,
    productImages: List<ProductImage> = emptyList(),
    totalCount: Int? = null,
    overflowCount: Int = 0,
    onClick: (() -> Unit)? = null
) {
    val visibleImages = if (productImages.isEmpty()) {
        listOf<ProductImage?>(null)
    } else {
        productImages.take(2)
    }

    val effectiveOverflow = when {
        totalCount != null -> maxOf(0, totalCount - visibleImages.size)
        productImages.size > 2 -> productImages.size - 2
        else -> overflowCount
    }

    val clickableModifier = if (onClick != null) {
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .then(clickableModifier)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-10).dp)
    ) {
        visibleImages.forEach { image ->
            ProductImageItem(image = image)
        }

        if (effectiveOverflow > 0) {
            Text(
                modifier = Modifier.padding(start = 18.dp),
                text = "+$effectiveOverflow",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProductImageItem(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    @DrawableRes imageRes: Int? = null,
    bitmap: ImageBitmap? = null,
    path: String? = null,
    image: ProductImage? = null
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        val loadedBitmap = remember(image?.path ?: path) {
            val filePath = image?.path ?: path
            filePath?.let { BitmapFactory.decodeFile(it)?.asImageBitmap() }
        }

        val resolvedPainter = when {
            painter != null -> painter
            imageRes != null -> painterResource(id = imageRes)
            image?.painter != null -> image.painter
            image?.imageRes != null -> painterResource(id = image.imageRes)
            else -> null
        }

        val resolvedBitmap = bitmap ?: image?.bitmap ?: loadedBitmap

        when {
            resolvedPainter != null -> {
                Image(
                    painter = resolvedPainter,
                    contentDescription = stringResource(R.string.product_image_description),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            resolvedBitmap != null -> {
                Image(
                    bitmap = resolvedBitmap,
                    contentDescription = stringResource(R.string.product_image_description),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            else -> {
                Icon(
                    painter = painterResource(id = R.drawable.cube_filled),
                    contentDescription = stringResource(R.string.product_placeholder_description),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(name = "Product Icon Stack Preview", showBackground = true)
@Composable
fun ProductIconStackPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                ProductIconStack(
                    overflowCount = 2,
                    onClick = {}
                )
                ProductIconStack(
                    productImages = listOf(ProductImage(imageRes = R.drawable.cube_filled)),
                    onClick = {}
                )

                ProductIconStack(
                    productImages = listOf(
                        ProductImage(imageRes = R.drawable.cube_filled),
                        ProductImage(imageRes = R.drawable.cube_outlined)
                    ),
                    onClick = {}
                )
                ProductIconStack(
                    productImages = listOf(
                        ProductImage(imageRes = R.drawable.cube_filled),
                        ProductImage(imageRes = R.drawable.cube_outlined),
                        ProductImage(imageRes = R.drawable.cube_filled),
                        ProductImage(imageRes = R.drawable.cube_outlined)
                    ),
                    onClick = {}
                )
            }
        }
    }
}
