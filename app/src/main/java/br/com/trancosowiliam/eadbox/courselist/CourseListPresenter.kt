package br.com.trancosowiliam.eadbox.courselist

import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.data.repository.CourseRepository

class CourseListPresenter(private val courseRepository: CourseRepository) : CourseListContract.Presenter {
    override lateinit var view: CourseListContract.View

    private val getCoursesOnSuccess: (List<Course>) -> Unit = { courses ->
        if(courses.isEmpty()) {
            view.showMessage("Nenhum item foi encontrado")
        } else {
            view.showCourses(courses)
            view.showLoading(false)
        }
    }

    override fun loadCourses(categorySlug:String) {
        view.showLoading(true)
        courseRepository.getCourses(categorySlug, getCoursesOnSuccess)
    }
}