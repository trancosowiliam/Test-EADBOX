package br.com.trancosowiliam.eadbox.data.repository.remote

import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.data.model.Course

interface CourseRemoteRepository {
    fun getAll(onSuccess:(List<Course>) -> Unit, onFailure: (String) -> Unit)
    fun getCategories(onSuccess:(List<Category>) -> Unit, onFailure: (String) -> Unit)
    fun getCoursesWithCategory(categorySlug:String, onSuccess:(List<Course>) -> Unit, onFailure: (String) -> Unit)
}