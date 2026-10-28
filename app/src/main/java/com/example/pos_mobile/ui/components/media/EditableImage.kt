package com.example.pos_mobile.ui.components.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pos_mobile.R

/**
 * Reusable Editable Image / Circular Avatar component with edit pencil overlay button.
 */
@Composable
fun EditableImage(
    modifier: Modifier = Modifier,
    title: String = "TAÏSO",
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
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center
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
