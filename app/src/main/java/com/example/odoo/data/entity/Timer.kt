package com.example.odoo.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity
data class Timer(
    @SerializedName("time")
    var time: Int,
    @SerializedName("project")
    val project: String,
    @SerializedName("task")
    val task: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("favorite")
    val favorite: Boolean,
    var timeInSeconds: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
