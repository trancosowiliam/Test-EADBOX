package br.com.trancosowiliam.eadbox.data.repository

import br.com.trancosowiliam.eadbox.asyncExec
import br.com.trancosowiliam.eadbox.data.dao.EadboxDatabase
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRemoteRepository

class CourseRepository(private val remoteRepository: CourseRemoteRepository, private val database:EadboxDatabase) {

    fun getCourses(slut:String, callback: (List<Course>) -> Unit) {
        if(slut.isNullOrEmpty()) {
            getAll(callback)
        } else {
            getCoursesWithCategory(slut, callback)
        }
    }

    private fun getAll(callback: (List<Course>) -> Unit) {
        remoteRepository.getAll(onSuccess = { courses ->
            callback(courses)
            saveCourses(courses)
        }, onFailure = {
            asyncExec(exec = {
                database.getCourseDao().getAll()
            }, callback = {courses ->
                callback(courses)
            })
        })
    }

    private fun getCoursesWithCategory(slut:String, callback: (List<Course>) -> Unit) {
        remoteRepository.getCoursesWithCategory(slut, onSuccess = { courses ->
            callback(courses)
            saveCourses(courses)
        }, onFailure = {
            asyncExec(exec = {
                database.getCourseDao().findCoursesForCategory(slut)
            }, callback = {courses ->
                callback(courses)
            })
        })
    }

    private fun saveCourses(categories: List<Course>) {
        asyncExec (exec = {
            database.getCourseDao().insert(*categories.toTypedArray())
        }){}
    }
}