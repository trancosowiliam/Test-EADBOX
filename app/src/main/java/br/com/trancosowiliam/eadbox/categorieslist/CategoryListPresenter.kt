package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.repository.remote.CategoryRepository

class CategoryListPresenter(private val categoryRepository: CategoryRepository) : CategoryListContract.Presenter {
    override lateinit var view: CategoryListContract.View

    override fun loadCategories() {
        view.showLoading(true)
        categoryRepository.getAll {
            showListResult(it)
        }
    }

    private fun showListResult(categories:List<Category>) {
        if(categories.isEmpty()) {
            view.showMessage("Nenhum item foi encontrado")
        }else {
            view.showCategories(categories)
            view.showLoading(false)
        }
    }
}