package com.example.listadder.Dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listadder.model.shoppingItem

class Diaglog(var dismissDialog: () -> Unit = {}) {
    @Composable
    fun Template(
        itemsAction: (item: shoppingItem) -> Unit = { _ -> },
        item: shoppingItem? = null
    ) {// lấy input function từ ngoài vào
        var number by remember {
            mutableDoubleStateOf(0.0)
        }
        var qualityTextError by remember {
            mutableStateOf(" ")
        }
        //If object appear edit it else add it
        var productName by remember {
            mutableStateOf(item?.name ?: " ")
        }
        var amount by remember {
            mutableStateOf(item?.amount?.toString() ?: " ")//initial value
        }
        var isNameError by remember {
            mutableStateOf(false)
        }
        var isQualityError by remember {
            mutableStateOf(false)
        }
        AlertDialog(onDismissRequest = {
            dismissDialog()
        }, title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(if (item != null) "Edit Item" else "Add Item")
            }
        }, text = {
            AddItemDialogContent(
                name = productName,// Product name initial
                onNameChange = {
                    productName = it; isNameError = false
                },// Function when input change
                nameError = isNameError,//Name error state
                quality = amount,// set initial
                onQualityChange = {
                    amount = it; isQualityError = false
                }, // Function when input change
                qualityError = isQualityError,
                qualityTextError = qualityTextError
            )
        }, confirmButton = {
            Button(onClick = {
                try {
                    number = amount.toDoubleOrNull()!!;
                    if (productName.isBlank() || amount.isBlank() || number < 0) {
                        throw Exception()
                    }
                    itemsAction(
                        shoppingItem(
                            productName,
                            number
                        )
                    )//pass the value to the function for the List to add
                    productName = " "
                    amount = " "
                    dismissDialog()
                } catch (_: Exception) {
                    if (productName.isBlank()) { // Check if name is blank (empty or whitespace only)
                        isNameError = true
                    }
                    if (amount.isBlank()) { // Check if quality is blank (empty or whitespace only)
                        qualityTextError = "Quality can not be null"
                        isQualityError = true
                    } else if (amount.toDoubleOrNull() == null) {
                        qualityTextError = "Input is not a number"
                        isQualityError = true
                    } else if (number < 0) {
                        qualityTextError = "Input is not a positive number"
                        isQualityError = true
                    }
                }
            }) {
                Text(if (item == null) "Add" else "Save")
            }
        }, dismissButton = {
            Button(onClick = {
                dismissDialog()
                productName = " "
                amount = " "
            }) {
                Text("Cancel")

            }
        })
    }

    @Composable
    fun Save(itemsAction: (item: shoppingItem) -> Unit) {
        Template(itemsAction);
    }

    @Composable
    fun Edit(itemsAction: (item: shoppingItem) -> Unit = {}, item: shoppingItem? = null) {
        Template(itemsAction, item = item);
    }
}


@Preview(showBackground = true)
@Composable
fun Diaglog() {
    val dialogView: Diaglog = Diaglog { };
    dialogView.Edit({});
}
