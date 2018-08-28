package br.com.trancosowiliam.eadbox.data.dao

import android.arch.persistence.room.*
import br.com.trancosowiliam.eadbox.data.model.Course

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg course:Course)

    @Update
    fun update(vararg courses: Course)

    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Query("SELECT * FROM course WHERE categoryId=:categoryId")
    fun findCoursesForCategory(categoryId: String): List<Course>
}