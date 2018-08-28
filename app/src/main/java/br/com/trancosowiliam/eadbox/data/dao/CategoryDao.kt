package br.com.trancosowiliam.eadbox.data.dao

import android.arch.persistence.room.*
import br.com.trancosowiliam.eadbox.data.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg category: Category)

    @Update
    fun update(vararg category: Category)

    @Query("SELECT * FROM category WHERE coursesCount > 0")
    fun getAll(): List<Category>
}