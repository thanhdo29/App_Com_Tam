import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.app_com_tam.R
import com.example.app_com_tam.screens.category.DialogDelete

data class FoodItem(val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen2(navController: NavController) {
    var showDialogDelete by remember { mutableStateOf(false) }
    if (showDialogDelete) {
        DialogDelete(
            onConfirmation = {

                showDialogDelete = false
            },
            onDismiss = { showDialogDelete = false }
        )
    }
    var showDialogEdit by remember { mutableStateOf(false) }
    if (showDialogEdit) {
        DialogEdit(
            currentName: String,
            onConfirmation = {

                showDialogEdit = false
            },
            onDismiss = { showDialogEdit = false }
        )
    }
    val foodList = listOf(
        FoodItem("Pizza"),
        FoodItem("Burger"),
        FoodItem("Sushi"),
        FoodItem("Pasta"),
        FoodItem("Salad")
    )
    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(foodList) { foodItem ->
            FoodItemRow(foodItem,
                onEditClick = { showDialogEdit=true },
                onDeleteClick = {showDialogDelete=true}
            )
        }
    }


}
@Composable
fun FoodItemRow(foodItem: FoodItem, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = foodItem.name, style = MaterialTheme.typography.bodyLarge)
        Row {
            IconButton(onClick = onEditClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_create_24),
                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Delete"
                )
            }
        }
    }
}
@Composable
fun DialogEdit(
    currentName: String,
    onConfirmation: () -> Unit,
    onDismiss: () -> Unit

) {

    var newName by remember { mutableStateOf(currentName) }

    Dialog(onDismissRequest = onDismiss) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Sửa loại món ăn",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            text = {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Tên mới") }
                )
            },

            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick =onConfirmation,


                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        ),
                    ) {
                        Text("Lưu")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f),

                        ) {
                        Text("Hủy")
                    }
                }

            }
        )
    }
}

