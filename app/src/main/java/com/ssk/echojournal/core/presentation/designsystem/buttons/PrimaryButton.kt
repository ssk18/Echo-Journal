package com.ssk.echojournal.core.presentation.designsystem.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssk.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.ssk.echojournal.core.presentation.designsystem.theme.buttonGradient

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .background(
                if (enabled) {
                    MaterialTheme.colorScheme.buttonGradient
                } else {
                    SolidColor(MaterialTheme.colorScheme.surfaceVariant)
                },
                shape = CircleShape
            ),
    ) {
        leadingIcon?.invoke()

        if (leadingIcon != null) {
            Spacer(modifier = Modifier.width(6.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (enabled) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.outline
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    EchoJournalTheme {
        PrimaryButton(
            title = "Confirm",
            onClick = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                )
            }
        )
    }
}