package com.live.tasknew.roomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.live.tasknew.ImageResponseClass
import java.io.Serializable

@Entity
data class Course(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "data") val data: ImageResponseClass?
//    @ColumnInfo(name = "name") val name: String?,
//    @ColumnInfo(name = "phone") val phone: String?
) : Serializable
