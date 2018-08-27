package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CategorieListPresenter(private val repository: CourseRepository) : CategorieListContract.Presenter {
    override fun loadCategories() {
        repository.getCategories({
            view.showCategories(it)
        }, {})
    }

    override lateinit var view: CategorieListContract.View
}