package com.example.listadder.Dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddItemDialogContent(
    name: String = "", // Provide default values for preview
    onNameChange: (String) -> Unit = {},
    nameError: Boolean = false,
    quality: String = "",
    onQualityChange: (String) -> Unit = {},
    qualityError: Boolean = false,
    qualityTextError: String = ""
) {
    Column {
        InputField(
            value = name,
            onValueChange = onNameChange,
            label = "Name",
            isError = nameError,
            errorMessage = "Name cannot be null"
        )
        Spacer(
            modifier = Modifier.heightIn(
                10.dp
            )
        )
        InputField(
            value = quality,
            onValueChange = onQualityChange,
            label = "Quality",
            isError = qualityError,
            errorMessage = qualityTextError
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddItemDialog() {
    AddItemDialogContent();
}
