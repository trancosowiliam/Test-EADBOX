package br.com.trancosowiliam.eadbox.courselist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import br.com.trancosowiliam.eadbox.GridDecoration
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.dpToPx
import kotlinx.android.synthetic.main.activity_course_list.*
import org.koin.android.ext.android.inject

class CourseListActivity : AppCompatActivity(), CourseListContract.View{
    override val presenter by inject<CourseListContract.Presenter>()

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
        clRecCourses.layoutManager = GridLayoutManager(this, 2)
        clRecCourses.addItemDecoration(GridDecoration(16.dpToPx(), 8.dpToPx()))
        val adapter = CourseListAdapter(courses)
        adapter.onClickListener = {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
        }
        clRecCourses.adapter = adapter
    }

    override fun dismissLoadDialog() {
        Log.i("EADBOX_LOG", "dismissLoadDialog")
    }
}
