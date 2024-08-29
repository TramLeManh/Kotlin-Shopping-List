package com.example.listadder.Dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorMessage: String
) {
    Column {
        if (isError) {
            Box(
                contentAlignment = Alignment.BottomEnd, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    errorMessage, color = MaterialTheme.colorScheme.error
                )
            }
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            maxLines = 1,
            label = { Text(label) },
            isError = isError
        )
    }
}