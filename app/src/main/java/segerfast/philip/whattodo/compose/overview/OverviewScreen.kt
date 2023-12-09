package segerfast.philip.whattodo.compose.overview

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import segerfast.philip.whattodo.data.TodoTask
import segerfast.philip.whattodo.viewmodels.TaskListViewModel

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    onTodoClick: (TodoTask) -> Unit = {},
    viewModel: TaskListViewModel = hiltViewModel()
) {
    val todoItems by viewModel.todoItems.collectAsStateWithLifecycle(initialValue = emptyList())

    OverviewScreenContent(
        todoTasks = todoItems,
        onCreateTodo = viewModel::createTodoItem,
        onMarkTodoAsCompleted = viewModel::markTaskAsCompleted,
        onDeleteItem = viewModel::deleteTodo
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OverviewScreenContent(
    todoTasks: List<TodoTask>,
    onCreateTodo: (description: String) -> Unit,
    onMarkTodoAsCompleted: (todoId: Int, value: Boolean) -> Unit,
    onDeleteItem: (TodoTask) -> Unit
) {
    val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContents(
                onCreateTodo = onCreateTodo
            )
        }
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.weight(1f)) {
                TodoItems(
                    todoTasks,
                    onItemMarkedAsCompleted = { item, completed -> onMarkTodoAsCompleted(item.id, completed) },
                    onDeleteItem = onDeleteItem
                )
            }
        }
    }
}

@Composable
fun ColumnScope.BottomSheetContents(
    onCreateTodo: (title: String) -> Unit
) {
    DebugArea()
    NewTodoArea(
        onCreateTodo = onCreateTodo
    )
}

@Composable
fun DebugArea() {
//    Column {
//        Divider()
//        Row(Modifier.padding(4.dp)) {
//            Button(onClick = onClear) {
//                Text(text = "Clear")
//            }
//        }
//    }
}

@Composable
fun TodoItems(
    todoTasks: List<TodoTask>,
    onItemMarkedAsCompleted: (item: TodoTask, completed: Boolean) -> Unit,
    onDeleteItem: (TodoTask) -> Unit
) {
    LazyColumn {
        items(todoTasks, { it.id }) { todoItem ->
            TodoItemContent(
                todoTask = todoItem,
                onMarkAsCompleted = { completed -> onItemMarkedAsCompleted(todoItem, completed) },
                onDeleteItem = { onDeleteItem(todoItem) }
            )
        }
    }
}

@Composable
fun TodoItemContent(
    todoTask: TodoTask,
    onMarkAsCompleted: (Boolean) -> Unit,
    onDeleteItem: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .border(1.dp, Color.Black.copy(0.2f), RoundedCornerShape(4.dp))
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = todoTask.completed, onCheckedChange = { onMarkAsCompleted(it) })
        Text(
            modifier = Modifier.weight(1f),
            text = todoTask.description
        )
        IconButton(onClick = onDeleteItem) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
        }
    }
}

@Composable
fun NewTodoArea(onCreateTodo: (description: String) -> Unit) {
    var todoName by remember { mutableStateOf("") }
    val btnCreateEnabled by remember { derivedStateOf { todoName.isNotBlank() } }

    fun createTodo() {
        onCreateTodo(todoName)
        todoName = ""
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = todoName,
            onValueChange = { todoName = it }
        )
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = { createTodo() },
            enabled = btnCreateEnabled
        ) {
            Text("Create")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OverviewScreenContentPreview() {
    OverviewScreenContent(
        todoTasks = listOf(TodoTask(0, "Todo item #1", false, 0, null)),
        onCreateTodo = {},
        onMarkTodoAsCompleted = { _, _ -> },
        onDeleteItem = {}
    )
}

@Preview
@Composable
fun TodoItemContentPreview() {
    TodoItemContent(
        todoTask = TodoTask(0, "This is a description", false, System.currentTimeMillis() - 3000, null),
        onMarkAsCompleted = {},
        onDeleteItem = {}
    )
}