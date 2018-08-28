package br.com.trancosowiliam.eadbox.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.com.trancosowiliam.eadbox.data.model.Course

@Dao
interface CourseDao {
    @Insert
    fun insert(course:Course)

    @Update
    fun update(vararg courses: Course)

    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Query("SELECT * FROM course WHERE categoryId=:categoryId")
    fun findCoursesForCategory(categoryId: Int): List<Course>
}