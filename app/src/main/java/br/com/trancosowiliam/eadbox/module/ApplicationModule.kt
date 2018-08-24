package br.com.trancosowiliam.eadbox.module

import br.com.trancosowiliam.eadbox.courselist.CourseListContract
import br.com.trancosowiliam.eadbox.courselist.CourseListPresenter
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    bean { CourseListPresenter(repository = get()) as CourseListContract.Presenter }

}