package br.com.trancosowiliam.eadbox.data.repository.remote

import br.com.trancosowiliam.eadbox.data.model.Categorie
import br.com.trancosowiliam.eadbox.data.model.Course

interface CourseRepository {
    fun getAll(onSuccess:(List<Course>) -> Unit, onFailure: (String) -> Unit)
    fun getCategories(onSuccess:(List<Categorie>) -> Unit, onFailure: (String) -> Unit)
}