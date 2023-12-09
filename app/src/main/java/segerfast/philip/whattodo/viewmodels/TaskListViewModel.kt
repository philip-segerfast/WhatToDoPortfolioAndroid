package segerfast.philip.whattodo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import segerfast.philip.whattodo.data.TodoTasksDao
import segerfast.philip.whattodo.data.TodoTask
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(private val taskDao: TodoTasksDao) : ViewModel() {

    val todoItems = taskDao.getTodoTasks()

    fun createTodoItem(name: String) {
        val todoTask = TodoTask(
            description = name,
            completed = false,
            createdTimestamp = System.currentTimeMillis(),
            due = null
        )

        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertTodoTask(todoTask)
        }
    }

    fun markTaskAsCompleted(itemId: Int, value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.markTodoTaskAsCompleted(itemId, value)
        }
    }

    fun deleteTodo(item: TodoTask) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteTodoTask(item)
        }
    }

}