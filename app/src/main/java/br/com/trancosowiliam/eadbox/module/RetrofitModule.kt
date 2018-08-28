package br.com.trancosowiliam.eadbox.module

import br.com.trancosowiliam.eadbox.BuildConfig
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.TypeAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "BASE_URL"
private const val READ_TIME_OUT = "READ_TIME_OUT"
private const val CONNECT_TIME_OUT = "CONNECT_TIME_OUT"
private const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"
private const val LOGGER_INTERCEPTOR = "LOGGER_INTERCEPTOR"
private const val COURSE_DESERIALIZER = "COURSE_DESERIALIZER"

val retrofitClientModule = applicationContext {

    bean(BASE_URL) { "https://academia.eadbox.com/api/" }
    bean(READ_TIME_OUT) { 180 }
    bean(CONNECT_TIME_OUT) { 180 }

    factory {
        Retrofit.Builder()
                .baseUrl(get<String>(BASE_URL))
                .addConverterFactory(/*Converter.Factory*/ get())
                .client(/* OkHttpClient */ get())
                .build()
    }

    bean { GsonBuilder()
            .registerTypeAdapter(Course::class.java, get(COURSE_DESERIALIZER))
            .create() as Gson }

    bean {
        OkHttpClient.Builder()
                .readTimeout(get(READ_TIME_OUT), TimeUnit.SECONDS)
                .connectTimeout(get(CONNECT_TIME_OUT), TimeUnit.SECONDS)
                .addInterceptor(get(HEADER_INTERCEPTOR))
                .addInterceptor(get(LOGGER_INTERCEPTOR))
                .build() as OkHttpClient
    }

    bean { GsonConverterFactory.create(/* Gson */ get()) as Converter.Factory }

    bean(HEADER_INTERCEPTOR) {
        Interceptor { chain ->
            val requestBuilder = chain?.request()?.newBuilder()
            requestBuilder?.header("Content-Type", "application/json")
            chain!!.proceed(requestBuilder?.build())
        }
    }

    bean(LOGGER_INTERCEPTOR) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    bean(COURSE_DESERIALIZER) {
        JsonDeserializer<Course> { json, _, _ ->
            val gson = Gson()
            gson.fromJson<Course>(json, Course::class.java).copy(
                    categoryId = gson.fromJson<Category>(json?.asJsonObject?.get("category"), Category::class.java).slug
            )
        }
    }
}