package br.com.trancosowiliam.eadbox.data.repository.remote

import br.com.trancosowiliam.eadbox.data.model.Categorie
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.data.repository.remote.service.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CourseRepositoryImpl(val retrofit: Retrofit) : CourseRepository {


    private val courseService by lazy { retrofit.create(CourseService::class.java) }

    override fun getAll(onSuccess: (List<Course>) -> Unit, onFailure: (String) -> Unit) {
        courseService.getAll().enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>?) {
                if(response?.isSuccessful == true) {
                    onSuccess(response?.body() ?: listOf())
                } else {
                    onFailure("Erro inesperado!")
                }
            }

            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {
                onFailure("Erro inesperado! Verifique sua conexão")
            }
        })
    }

    override fun getCategories(onSuccess: (List<Categorie>) -> Unit, onFailure: (String) -> Unit) {
        courseService.getCategories().enqueue(object : Callback<List<Categorie>> {

            override fun onResponse(call: Call<List<Categorie>>?, response: Response<List<Categorie>>?) {
                if(response?.isSuccessful == true) {
                    onSuccess(response?.body() ?: listOf())
                } else {
                    onFailure("Erro inesperado!")
                }
            }

            override fun onFailure(call: Call<List<Categorie>>?, t: Throwable?) {
                onFailure("Erro inesperado! Verifique sua conexão")
            }

        })
    }

}