package br.com.trancosowiliam.eadbox.module

import android.arch.persistence.room.Room
import br.com.trancosowiliam.eadbox.categorieslist.CategoryListContract
import br.com.trancosowiliam.eadbox.categorieslist.CategoryListPresenter
import br.com.trancosowiliam.eadbox.course.CourseContract
import br.com.trancosowiliam.eadbox.course.CoursePresenter
import br.com.trancosowiliam.eadbox.courselist.CourseListContract
import br.com.trancosowiliam.eadbox.courselist.CourseListPresenter
import br.com.trancosowiliam.eadbox.data.dao.EadboxDatabase
import br.com.trancosowiliam.eadbox.data.repository.CourseRepository
import br.com.trancosowiliam.eadbox.data.repository.remote.CategoryRepository
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRemoteRepository
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRemoteRepositoryImpl
import org.koin.dsl.module.applicationContext

val applicationModule = applicationContext {
    factory { CourseListPresenter(get()) as CourseListContract.Presenter }
    factory { CategoryListPresenter(get()) as CategoryListContract.Presenter }
    factory { CoursePresenter() as CourseContract.Presenter }

    factory { CourseRepository(get(), get()) }
    factory { CategoryRepository(get(), get()) }

    factory { CourseRemoteRepositoryImpl(get()) as CourseRemoteRepository }
    factory { Room.databaseBuilder(get(), EadboxDatabase::class.java, "database3.db").build() }
}