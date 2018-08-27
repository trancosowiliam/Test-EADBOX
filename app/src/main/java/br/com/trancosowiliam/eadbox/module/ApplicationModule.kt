package br.com.trancosowiliam.eadbox.module

import br.com.trancosowiliam.eadbox.categorieslist.CategorieListContract
import br.com.trancosowiliam.eadbox.categorieslist.CategorieListPresenter
import br.com.trancosowiliam.eadbox.courselist.CourseListContract
import br.com.trancosowiliam.eadbox.courselist.CourseListPresenter
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    factory { CourseListPresenter(repository = get()) as CourseListContract.Presenter }
    factory { CategorieListPresenter(repository = get()) as CategorieListContract.Presenter }
}