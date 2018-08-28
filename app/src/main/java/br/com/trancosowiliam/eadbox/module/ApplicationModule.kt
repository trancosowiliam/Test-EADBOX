package br.com.trancosowiliam.eadbox.module

import br.com.trancosowiliam.eadbox.categorieslist.CategoryListContract
import br.com.trancosowiliam.eadbox.categorieslist.CategoryListPresenter
import br.com.trancosowiliam.eadbox.courselist.CourseListContract
import br.com.trancosowiliam.eadbox.courselist.CourseListPresenter
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    factory { CourseListPresenter(repository = get()) as CourseListContract.Presenter }
    factory { CategoryListPresenter(repository = get()) as CategoryListContract.Presenter }
}