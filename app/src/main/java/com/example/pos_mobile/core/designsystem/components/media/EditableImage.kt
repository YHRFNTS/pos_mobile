package com.example.pos_mobile.core.designsystem.components.media

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

/**
 * Reusable Editable Image / Circular Avatar component with edit pencil overlay button.
 */
@Composable
fun EditableImage(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    @DrawableRes imageRes: Int? = null,
    title: String? = null,
    showEditButton: Boolean = true,
    onEditClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier.size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(Color(0xFF2C355E)),
            contentAlignment = Alignment.Center
        ) {
            val resolvedPainter = when {
                painter != null -> painter
                imageRes != null -> painterResource(id = imageRes)
                else -> painterResource(id = R.drawable.business_icon)
            }
            Image(
                painter = resolvedPainter,
                contentDescription = title ?: stringResource(R.string.edit_image),
                contentScale = ContentScale.Crop,
                colorFilter = if (painter == null && imageRes == null) ColorFilter.tint(Color.White) else null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (painter == null && imageRes == null) 24.dp else 0.dp)
                    .clip(CircleShape)
            )
        }

        if (showEditButton && (onEditClick != null)) {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_image),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview(name = "Editable Image Preview", showBackground = true)
@Composable
fun EditableImagePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            EditableImage(showEditButton = true, onEditClick = {})
        }
    }
}

