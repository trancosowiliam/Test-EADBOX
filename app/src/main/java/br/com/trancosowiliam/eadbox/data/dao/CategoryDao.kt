package br.com.trancosowiliam.eadbox.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Update
    fun update(vararg category: Category)

    @Query("SELECT * FROM category")
    fun getAll(): List<Category>
}