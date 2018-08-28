package br.com.trancosowiliam.eadbox.categorieslist

import br.com.trancosowiliam.eadbox.BasePresenter
import br.com.trancosowiliam.eadbox.BaseView
import br.com.trancosowiliam.eadbox.data.model.Category

interface CategorieListContract{
    interface Presenter : BasePresenter<View> {
        fun loadCategories()
    }

    interface View : BaseView<Presenter> {
        fun showCategories(categories: List<Category>)
        fun showLoading(show: Boolean)
    }
}