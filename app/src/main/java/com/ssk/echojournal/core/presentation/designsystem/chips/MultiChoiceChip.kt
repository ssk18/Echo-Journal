package com.ssk.echojournal.core.presentation.designsystem.chips

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.echojournal.core.presentation.designsystem.theme.EchoJournalTheme

@Composable
fun MultiChoiceChip(
    modifier: Modifier = Modifier,
    displayText: String,
    isClearVisible: Boolean,
    onClearClick: () -> Unit,
    isHighlighted: Boolean,
    isDropDownVisible: Boolean,
    dropDownMenu: @Composable () -> Unit,
    leadingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    val containerColor = if (isHighlighted) {
        MaterialTheme.colorScheme.surface
    } else {
        Color.Transparent
    }

    val borderColor = if (isHighlighted) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = modifier
            .then(
                if (isHighlighted) {
                    Modifier.shadow(
                        elevation = 4.dp,
                        shape = CircleShape
                    )
                } else {
                    Modifier
                }
            )
            .border(
                width = 0.5.dp,
                color = borderColor,
                shape = CircleShape
            )
            .background(containerColor)
            .clickable(
                onClick = onClick
            )
            .animateContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            leadingContent?.invoke()
            Text(
                text = displayText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            AnimatedVisibility(
                visible = isClearVisible,
            ) {
                IconButton(
                    onClick = onClearClick,

                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",

                    )
                }
            }

        }
        if (isDropDownVisible) {
            dropDownMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiChoiceChipPreview() {
    EchoJournalTheme {
        MultiChoiceChip(
            displayText = "Hello world!",
            isClearVisible = true,
            onClearClick = {},
            isHighlighted = false,
            isDropDownVisible = false,
            dropDownMenu = {},
            leadingContent = null,
            onClick = {}
        )
    }
}