package br.com.trancosowiliam.eadbox.courselist

import br.com.trancosowiliam.eadbox.data.repository.remote.CourseRepository

class CourseListPresenter(private val repository: CourseRepository) : CourseListContract.Presenter {
    override lateinit var view: CourseListContract.View

    override fun loadCourses() {
        view.showLoadDialog()

        repository.getAll({courses ->
            view.showCourses(courses)
            view.dismissLoadDialog()
        }, {
            view.dismissLoadDialog()
        })
    }

}