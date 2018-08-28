package br.com.trancosowiliam.eadbox.module

import br.com.trancosowiliam.eadbox.categorieslist.CategoryListContract
import br.com.trancosowiliam.eadbox.categorieslist.CategoryListPresenter
import br.com.trancosowiliam.eadbox.course.CourseContract
import br.com.trancosowiliam.eadbox.course.CoursePresenter
import br.com.trancosowiliam.eadbox.courselist.CourseListContract
import br.com.trancosowiliam.eadbox.courselist.CourseListPresenter
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    factory { CourseListPresenter(repository = get()) as CourseListContract.Presenter }
    factory { CategoryListPresenter(repository = get(), categoryDao = get()) as CategoryListContract.Presenter }
    factory { CoursePresenter() as CourseContract.Presenter }
}