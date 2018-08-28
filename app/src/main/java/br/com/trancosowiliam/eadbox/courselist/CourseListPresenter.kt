package br.com.trancosowiliam.eadbox.courselist

import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CourseListPresenter(private val repository: CourseRepository) : CourseListContract.Presenter {
    override lateinit var view: CourseListContract.View

    private val getCoursesOnSuccess: (List<Course>) -> Unit = { courses ->
        if(courses.isEmpty()) {
            view.showMessage("Nenhum item foi encontrado")
        } else {
            view.showCourses(courses)
            view.showLoading(false)
        }
    }

    private val getCoursesOnFailure: (String) -> Unit = { _ ->
        view.showMessage("Nenhum item foi encontrado")
    }

    override fun loadCourses(categorySlug:String) {
        view.showLoading(true)

        if (categorySlug.isNullOrEmpty()) {
            repository.getAll(getCoursesOnSuccess, getCoursesOnFailure)
        } else {
            repository.getCoursesWithCategory(categorySlug, getCoursesOnSuccess, getCoursesOnFailure)
        }
    }

}