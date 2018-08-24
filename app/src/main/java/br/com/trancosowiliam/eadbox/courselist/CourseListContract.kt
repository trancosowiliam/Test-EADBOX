package br.com.trancosowiliam.eadbox.courselist

import br.com.trancosowiliam.eadbox.BasePresenter
import br.com.trancosowiliam.eadbox.BaseView
import br.com.trancosowiliam.eadbox.data.model.Course

interface CourseListContract{
    interface Presenter : BasePresenter<View> {
        fun loadCourses()
    }

    interface View : BaseView<Presenter> {
        fun showLoadDialog()
        fun showCourses(courses: List<Course>)
        fun dismissLoadDialog()
    }
}