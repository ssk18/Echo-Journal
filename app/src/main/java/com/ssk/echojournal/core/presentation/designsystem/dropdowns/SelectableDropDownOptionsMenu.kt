package com.ssk.echojournal.core.presentation.designsystem.dropdowns

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.ssk.echojournal.R
import com.ssk.echojournal.core.presentation.designsystem.theme.EchoJournalTheme

@Composable
fun <T> SelectableDropDownOptionsMenu(
    modifier: Modifier = Modifier,
    items: List<Selectable<T>>,
    key: (T) -> Any,
    itemDisplayText: (T) -> String,
    onDismiss: () -> Unit,
    onItemClick: (Selectable<T>) -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    dropDownOffset: IntOffset = IntOffset.Zero,
    maxDropDownHeight: Dp,
    dropDownExtras: SelectableOptionExtras? = null,
) {
    Popup(
        onDismissRequest = onDismiss,
        offset = dropDownOffset,
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 4.dp,
            modifier = modifier
                .heightIn(max = maxDropDownHeight)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
        ) {
            LazyColumn(
                modifier = Modifier
                    .animateContentSize()
                    .padding(6.dp)
            ) {
                items(
                    items = items,
                    key = { key(it.item) }
                ) {
                    Row(
                        modifier = Modifier
                            .animateItem()
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (it.selected) {
                                    MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.05f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            )
                            .clickable {
                                onItemClick(it)
                            }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        leadingIcon?.invoke()
                        Text(
                            text = itemDisplayText(it.item),
                            modifier = Modifier.weight(1f)
                        )
                        if (it.selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                if (dropDownExtras != null && dropDownExtras.text.isNotEmpty()) {
                    item {

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectableDropDownOptionsMenuPreview() {
    EchoJournalTheme {
        SelectableDropDownOptionsMenu(
            items = listOf(
                Selectable(
                    item = "Hello world 1",
                    selected = false
                ),
                Selectable(
                    item = "Hello world 2",
                    selected = true
                ),
                Selectable(
                    item = "Hello world 3",
                    selected = true
                ),
            ),
            key = { it },
            itemDisplayText = { it },
            onDismiss = {},
            onItemClick = {},
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                    contentDescription = null
                )
            },
            maxDropDownHeight = 500.dp,
        )
    }
}
