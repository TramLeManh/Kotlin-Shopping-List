package com.example.listadder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.listadder.Dialog.Diaglog
import com.example.listadder.model.shoppingItem
import com.example.listadder.ui.theme.ListAdderTheme

enum class DialogTye() { ADD, EDIT }

@Composable
fun ItemList() {
    var type by remember {
        mutableStateOf(DialogTye.ADD)
    }
    var itemEdit by remember { mutableStateOf<shoppingItem?>(null) }
    var showDialog by remember {
        mutableStateOf(false);
    }
    var items by remember {
        mutableStateOf(listOf<shoppingItem>()) // Use mutableListOf
    }
    val dialogView: Diaglog = Diaglog { showDialog = false };
    Column() {
        Box(
            Modifier
                .fillMaxSize()
                .weight(2f)
                .padding(top = 30.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)
            ) {
                items(items) {
                    shoppingItem.Template(
                        it,
                        edit = { showDialog = true;type = DialogTye.EDIT;itemEdit = it },
                        delete = { items = items - it })
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Button( // Add some padding
                onClick = {
                    showDialog = true
                    type = DialogTye.ADD
                }) {
                Text("Add Item")
            }
        }
    }
    if (showDialog) {
        when (type) {
            DialogTye.ADD -> dialogView.Save { i ->
                items = items + i
                showDialog = false
            };
            DialogTye.EDIT -> dialogView.Edit(itemsAction = { updatedItem ->
                items = editListWithObject(
                    originalList = items,
                    itemToEdit = itemEdit!!,
                    newItem = updatedItem
                )
            }, item = itemEdit)
        }
        //Take out name, amount from the dialog and pass it to the shoppingItem list
    }
}

fun editListWithObject(
    originalList: List<shoppingItem>,
    itemToEdit: shoppingItem,
    newItem: shoppingItem
): List<shoppingItem> {
    return originalList.map { item ->
        if (item == itemToEdit) newItem else item
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


