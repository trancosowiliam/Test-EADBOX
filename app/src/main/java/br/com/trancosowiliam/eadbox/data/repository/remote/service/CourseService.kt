package br.com.trancosowiliam.eadbox.data.repository.remote.service

import br.com.trancosowiliam.eadbox.data.model.Categorie
import br.com.trancosowiliam.eadbox.data.model.Course
import retrofit2.Call
import retrofit2.http.GET

interface CourseService {
    @GET("courses")
    fun getAll(): Call<List<Course>>

    @GET("categories")
    fun getCategories(): Call<List<Categorie>>
}