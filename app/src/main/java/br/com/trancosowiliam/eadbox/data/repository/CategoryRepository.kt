package br.com.trancosowiliam.eadbox.data.repository.remote

import br.com.trancosowiliam.eadbox.asyncExec
import br.com.trancosowiliam.eadbox.data.dao.EadboxDatabase
import br.com.trancosowiliam.eadbox.data.model.Category

class CategoryRepository(private val remoteRepository: CourseRemoteRepository, private val database: EadboxDatabase) {

    fun getAll(callback:(List<Category>)-> Unit) {
        remoteRepository.getCategories({ categories ->
            val list = categories.filter {
                it.coursesCount > 0
            }.sortedByDescending {
                it.coursesCount
            }

            callback(list)
            saveCategories(list)
        }, {
            asyncExec(exec = {
                database.getCategoryDao().getAll()
            }, callback = {categories ->
                callback(categories)
            })
        })
    }

    private fun saveCategories(categories: List<Category>) {
        asyncExec (exec = {
            database.getCategoryDao().insert(*categories.toTypedArray())
        }){}
    }
}