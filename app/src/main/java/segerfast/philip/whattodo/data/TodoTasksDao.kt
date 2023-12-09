package segerfast.philip.whattodo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodoTask(task: TodoTask)

    @Update
    fun updateTodoTask(task: TodoTask)

    @Delete
    fun deleteTodoTask(task: TodoTask)

    @Query("SELECT * FROM todo_tasks")
    fun getTodoTasks(): Flow<List<TodoTask>>

    @Query(
        """
        UPDATE todo_tasks
        SET completed = :completed
        WHERE id = :taskId
    """
    )
    fun markTodoTaskAsCompleted(taskId: Int, completed: Boolean)

    @Query("SELECT * FROM todo_tasks WHERE id = :taskId")
    fun getTodoTask(taskId: Int): TodoTask

}
