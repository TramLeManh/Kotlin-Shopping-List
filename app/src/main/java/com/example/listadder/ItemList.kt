package com.example.listadder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.listadder.Dialog.AddItemDialogContent
import com.example.listadder.model.shoppingItem
import com.example.listadder.ui.theme.ListAdderTheme


@Composable

fun ItemList() {
    var showDialog by remember {
        mutableStateOf(false);
    }
    var number by remember {
        mutableStateOf(1.0)
    }
    var qualityTextError by remember {
        mutableStateOf(" ")
    }
    var name by remember {
        mutableStateOf(" ")
    }
    var quality by remember {
        mutableStateOf(" ")
    }
    var items by remember {
        mutableStateOf(listOf<shoppingItem>()) // Use mutableListOf
    }
    var nameError by remember {
        mutableStateOf(false)
    }
    var qualityError by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp), // Add some padding
            onClick = {
                showDialog = true
            }) {
            Text("Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            items(items) {
                shoppingItem.Template(it, {}, delete = { items = deleteItem(it, items) })
            }
        }
    }
    if (showDialog) {
        AlertDialog(onDismissRequest = {
            showDialog = false
        }, title = {
            Text("Add Item")
        }, text = {
            AddItemDialogContent(
                name = name,// Product name
                onNameChange = { name = it; nameError = false },// Function when input change
                nameError = nameError,//Name error state
                quality = quality,// Product quality
                onQualityChange = {
                    quality = it; qualityError = false
                }, // Function when input change
                qualityError = qualityError,
                qualityTextError = qualityTextError
            )
        }, confirmButton = {
            Button(onClick = {
                try {
                    number = quality.toDoubleOrNull()!!;
                    if (name.isBlank() || quality.isBlank()) {
                        throw Exception()
                    }
                    items = items + (shoppingItem(name = name, amount = number))
                    name = " "
                    quality = " "
                    showDialog = false
                } catch (_: Exception) {
                    if (name.isBlank()) { // Check if name is blank (empty or whitespace only)
                        nameError = true
                    }
                    if (quality.isBlank()) { // Check if quality is blank (empty or whitespace only)
                        qualityTextError = "Quality can not be null"
                        qualityError = true
                    } else if (quality.toDoubleOrNull() == null) {
                        qualityTextError = "Input is not a number"
                        qualityError = true
                    }
                }
            }) {
                Text("Add")
            }
        }, dismissButton = {
            Button(onClick = {
                showDialog = false
            }) {
                Text("Cancel")
                name = " "
                quality = " "
            }
        })

    }


}

fun deleteItem(item: shoppingItem, items: List<shoppingItem>): List<shoppingItem> {
    return items - item
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListAdderTheme {
        ItemList()
    }
}


