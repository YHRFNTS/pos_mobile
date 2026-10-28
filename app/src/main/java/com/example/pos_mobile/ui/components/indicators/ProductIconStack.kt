package com.example.pos_mobile.ui.components.indicators

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R
import java.io.File

/**
 * Reusable Product Icon Stack with 0 to 2 product image circles and Overflow badge (+X).
 * Supports local DB images (file paths, Bitmaps, ImageBitmaps, painters, or drawable IDs).
 * Clickable element with ripple effect.
 */
@Composable
fun ProductIconStack(
    modifier: Modifier = Modifier,
    productImages: List<Any?> = emptyList(),
    totalCount: Int? = null,
    overflowCount: Int = 0,
    onClick: (() -> Unit)? = null
) {
    val visibleImages = if (productImages.isEmpty()) {
        listOf<Any?>(null)
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
    image: Any?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        val bitmap = remember(image) {
            when (image) {
                is String -> BitmapFactory.decodeFile(image)?.asImageBitmap()
                is File -> BitmapFactory.decodeFile(image.absolutePath)?.asImageBitmap()
                else -> null
            }
        }

        when {
            image is ImageBitmap -> {
                Image(
                    bitmap = image,
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            image is ImageVector -> {
                Image(
                    imageVector = image,
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            image is Painter -> {
                Image(
                    painter = image,
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            image is Int -> {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            image is Bitmap -> {
                Image(
                    bitmap = image.asImageBitmap(),
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            bitmap != null -> {
                Image(
                    bitmap = bitmap,
                    contentDescription = "Producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            else -> {
                Icon(
                    painter = painterResource(id = R.drawable.cube_filled),
                    contentDescription = "Producto placeholder",
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
                    productImages = listOf(R.drawable.cube_filled),
                    onClick = {}
                )

                ProductIconStack(
                    productImages = listOf(R.drawable.cube_filled, R.drawable.cube_outlined),
                    onClick = {}
                )
                ProductIconStack(
                    productImages = listOf(
                        R.drawable.cube_filled,
                        R.drawable.cube_outlined,
                        R.drawable.cube_filled,
                        R.drawable.cube_outlined
                    ),
                    onClick = {}
                )
            }
        }
    }
}

