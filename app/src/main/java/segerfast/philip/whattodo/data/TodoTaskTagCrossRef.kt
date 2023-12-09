package segerfast.philip.whattodo.data

import androidx.room.Entity

@Entity(primaryKeys = ["todoTaskId", "tagId"])
data class TodoTaskTagCrossRef(
    val todoTaskId: Int,
    val tagId: Int
)