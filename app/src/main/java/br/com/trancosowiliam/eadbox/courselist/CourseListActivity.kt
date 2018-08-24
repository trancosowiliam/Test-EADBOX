package br.com.trancosowiliam.eadbox.courselist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Course
import kotlinx.android.synthetic.main.activity_course_list.*
import org.koin.android.ext.android.inject

class CourseListActivity : AppCompatActivity(), CourseListContract.View{
    override val presenter by inject<CourseListContract.Presenter>()

    val controller by lazy { CourseController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        presenter.view = this
        presenter.loadCourses()
    }

    override fun showLoadDialog() {
        Log.i("EADBOX_LOG", "showLoadDialog")
    }

    override fun showCourses(courses: List<Course>) {
        clRecCourses.setController(controller)
        controller.setData(courses)
        controller.listener = {course ->
            Log.i("EADBOX_LOG", course.courseId)
        }
    }

    override fun dismissLoadDialog() {
        Log.i("EADBOX_LOG", "dismissLoadDialog")
    }
}
