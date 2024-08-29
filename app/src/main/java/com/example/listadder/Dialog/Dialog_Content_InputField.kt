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
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputField(
    value: String = " ",
    onValueChange: (String) -> Unit = { },
    label: String = "test",
    isError: Boolean = true,
    errorMessage: String = "Error"
) {
    Column {
        //Add error on the input
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

@Preview(showBackground = true)
@Composable
fun InputFieldTest() {
    InputField();
}
