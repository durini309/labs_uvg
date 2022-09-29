package com.durini.roomdemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
     @PrimaryKey(autoGenerate = true) val id: Int? = null,
    // @ColumnInfo(name = "full_name")
    val fullname: String,
    val country: String,
    val age: Int
)
