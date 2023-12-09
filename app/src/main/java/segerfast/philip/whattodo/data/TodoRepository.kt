package segerfast.philip.whattodo.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(private val todoTasksDao: TodoTasksDao) {

    fun getTodoItems() = todoTasksDao.getTodoTasks()

    fun getTodoItem(todoItemId: Int) = todoTasksDao.getTodoTask(todoItemId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TodoRepository? = null

        fun getInstance(todoTasksDao: TodoTasksDao) =
            instance ?: synchronized(this) {
                instance ?: TodoRepository(todoTasksDao).also { instance = it }
            }
    }
}
