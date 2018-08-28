package br.com.trancosowiliam.eadbox.data.repository.remote

import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.data.repository.remote.service.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CourseRemoteRepositoryImpl(private val retrofit: Retrofit) : CourseRemoteRepository {
    private val courseService by lazy { retrofit.create(CourseService::class.java) }

    override fun getAll(onSuccess: (List<Course>) -> Unit, onFailure: (String) -> Unit) {
        courseService.getAll().exec(onSuccess, onFailure, listOf())
    }

    override fun getCategories(onSuccess: (List<Category>) -> Unit, onFailure: (String) -> Unit) {
        courseService.getCategories().exec(onSuccess, onFailure, listOf())
    }

    override fun getCoursesWithCategory(categorySlug: String, onSuccess: (List<Course>) -> Unit, onFailure: (String) -> Unit) {
        courseService.getCoursesWithCategory(categorySlug).exec(onSuccess, onFailure, listOf())
    }
}

fun <T> Call<T>.exec(onSuccess: (T) -> Unit, onFailure: (String) -> Unit, default:T){
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if(response?.isSuccessful == true) {
                onSuccess(response?.body() ?: default)
            } else {
                onFailure("Erro inesperado!")
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            onFailure("Erro inesperado! Verifique sua conexão")
        }
    })
}