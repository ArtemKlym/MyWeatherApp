package com.artemklymenko.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyAlertDialog(
    title: String,
    content: @Composable () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    ) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            confirmButton = {
                Button(onClick = { onConfirm() }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = { onCancel() }) {
                    Text("Cancel")
                }
            },
            title = { Text(text = title) },
            text = {
                content()
            },
            tonalElevation = 16.dp
        )
    }
}