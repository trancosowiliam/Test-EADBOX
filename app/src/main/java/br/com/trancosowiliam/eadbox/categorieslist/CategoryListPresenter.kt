package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CategoryListPresenter(private val repository: CourseRepository) : CategoryListContract.Presenter {
    override lateinit var view: CategoryListContract.View

    override fun loadCategories() {
        view.showLoading(true)

        repository.getCategories({ categories ->
            val list = categories.filter {
                it.coursesCount > 0
            }.sortedByDescending {
                it.coursesCount
            }

            if(list.isEmpty()) {
                view.showMessage("Nenhum item foi encontrado")
            }else {
                view.showCategories(list)
                view.showLoading(false)
            }
        }, {
            view.showMessage("Nenhum item foi encontrado")
        })
    }
}