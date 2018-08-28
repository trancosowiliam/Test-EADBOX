package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.asyncExec
import br.com.trancosowiliam.eadbox.data.dao.CategoryDao
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CategoryListPresenter(private val repository: CourseRepository, private val categoryDao:CategoryDao) : CategoryListContract.Presenter {
    override lateinit var view: CategoryListContract.View

    override fun loadCategories() {
        view.showLoading(true)

        repository.getCategories({ categories ->
            val list = categories.filter {
                it.coursesCount > 0
            }.sortedByDescending {
                it.coursesCount
            }

            showListResult(list)
            saveCategories(list)
        }, {
            asyncExec(exec = {
                categoryDao.getAll()
            }, callback = {categories ->
                showListResult(categories)
            })
        })
    }

    private fun showListResult(categories:List<Category>) {
        if(categories.isEmpty()) {
            view.showMessage("Nenhum item foi encontrado")
        }else {
            view.showCategories(categories)
            view.showLoading(false)
        }
    }

    private fun saveCategories(categories: List<Category>) {
        asyncExec (exec = {
            categoryDao.insert(*categories.toTypedArray())
        }){}
    }
}