package br.com.trancosowiliam.eadbox.module


import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepositoryImpl
import org.koin.dsl.module.applicationContext

val servicesModule = applicationContext {
    factory { CourseRepositoryImpl(retrofit = get()) as CourseRepository }
}