package br.com.trancosowiliam.eadbox.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course


@Database(entities = [Category::class, Course::class], version = 1)
abstract class EadboxDatabase : RoomDatabase() {
    abstract fun courseDao() : CourseDao
    abstract fun categoryDao() : CategoryDao
}