package segerfast.philip.whattodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_tasks")
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val completed: Boolean,
    val createdTimestamp: Long,
    val due: Long?
)
