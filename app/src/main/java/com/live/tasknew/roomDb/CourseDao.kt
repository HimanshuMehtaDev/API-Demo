package com.live.tasknew.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.live.tasknew.ImageResponseClass

@Dao
interface CourseDao {

    @Insert
    fun insertRecord(users: Course?)

    @Query("SELECT EXISTS(SELECT * FROM Course WHERE id = :id)")
    fun isExist(id: Int): Boolean

    @Query("Delete from Course where id= :id")
    fun deleteRecord(id: Int)

    @Query("select * from Course where id= :id")
    fun getById(id: Int): Course?

    @Query("update Course set data=:data where id=:id ")
    fun updateRecord(data: ImageResponseClass?, id: Int)

//    @Query("update Course set data=:data where id=:id ")
//    fun updateRecord(data: ImageResponseClass?, id: Int)

    @Query("SELECT * FROM Course GROUP BY data ORDER BY id ASC")
    fun getAllRecords(): Array<Course>

//    @Query("SELECT * FROM Course GROUP BY data ORDER BY id ASC")
//    fun getAllRecords(): Array<Course>

}