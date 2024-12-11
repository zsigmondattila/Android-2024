package com.example.recipehub.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L,

    @SerializedName("json_data")
    val json: String,

    val isBookmarked: Boolean = false
)