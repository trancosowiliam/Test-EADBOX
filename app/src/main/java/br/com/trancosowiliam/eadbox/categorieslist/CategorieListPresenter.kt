package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CategorieListPresenter(private val repository: CourseRepository) : CategorieListContract.Presenter {
    override lateinit var view: CategorieListContract.View

    override fun loadCategories() {
        view.showLoading(true)

        repository.getCategories({
            view.showCategories(it)
            view.showLoading(false)
        }, {
            view.showLoading(false)
        })
    }
}