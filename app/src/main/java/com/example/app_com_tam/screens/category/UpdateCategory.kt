import android.util.Log
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
import androidx.compose.runtime.collectAsState
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
import com.example.app_com_tam.model.TypeDish
import com.example.app_com_tam.screens.category.DialogDelete
import com.example.app_com_tam.ui.theme.Black_Medium
import com.example.app_com_tam.ui.theme.Dark_Charcoa
import com.example.app_com_tam.ui.theme.White
import com.example.app_com_tam.viewModel.TypeDishViewModel

data class FoodItem(val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen2(navController: NavController, typeDishViewModel: TypeDishViewModel) {
    val cates by typeDishViewModel.typeDishs.collectAsState(initial = emptyList())
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogEdit by remember { mutableStateOf(false) }
    var selectedTypeDish by remember { mutableStateOf<TypeDish?>(null) }
    var selectedTypeDishUpdate by remember { mutableStateOf<TypeDish?>(null) }

    if (showDialogDelete) {
        DialogDelete(
            onConfirmation = {
                selectedTypeDish?.let { typeDishViewModel.deleteTypeDish(it) }
                showDialogDelete = false
            },
            onDismiss = { showDialogDelete = false }
        )
    }

    if (showDialogEdit) {
        selectedTypeDishUpdate?.let { typeDish ->
            DialogEdit(
                onConfirmation = { updatedName ->
                    val updatedTypeDish = typeDish.copy(nameType = updatedName)
                    typeDishViewModel.updateTypeDish(updatedTypeDish)
                    showDialogEdit = false
                },
                onDismiss = { showDialogEdit = false },
                nameCateUpdate = typeDish.nameType
            )
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize().background(color = Dark_Charcoa)
            .padding(top = 70.dp)
    ) {
        items(cates) { cate ->
            TypeDishRow(
                typeDish = cate,
                onEditClick = {
                    selectedTypeDishUpdate = cate
                    showDialogEdit = true
                },
                onDeleteClick = {
                    selectedTypeDish = cate
                    showDialogDelete = true
                }
            )
        }
    }
}

@Composable
fun TypeDishRow(
    typeDish: TypeDish,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = Black_Medium)
            .padding(16.dp).padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = typeDish.nameType, style = MaterialTheme.typography.bodyLarge, color = White)
        Row {
            IconButton(onClick = onEditClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_create_24),
                    contentDescription = "Edit",
                    tint = White
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_24),
                    contentDescription = "Delete",
                    tint = White
                )
            }
        }
    }
}

@Composable
fun DialogEdit(
    onConfirmation: (String) -> Unit,
    onDismiss: () -> Unit,
    nameCateUpdate: String
) {
    var nameCate by remember { mutableStateOf(nameCateUpdate) }

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
                    value = nameCate,
                    onValueChange = { nameCate = it },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onConfirmation(nameCate)
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White)
                    ) {
                        Text("Lưu")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFB703),
                            contentColor = Color.White)
                    ) {
                        Text("Hủy")
                    }
                }
            }
        )
    }
}
