package segerfast.philip.whattodo.data

import androidx.room.PrimaryKey

data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val color: Int
)
