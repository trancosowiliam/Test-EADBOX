package br.com.trancosowiliam.eadbox.data.repository.remote.service

import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseService {
    @GET("courses")
    fun getAll(): Call<List<Course>>

    @GET("categories")
    fun getCategories(): Call<List<Category>>

    @GET("categories/{category_slug}")
    fun getCoursesWithCategory(@Path("category_slug") categorySlug:String): Call<List<Course>>
}