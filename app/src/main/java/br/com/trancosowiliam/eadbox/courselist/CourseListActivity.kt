package br.com.trancosowiliam.eadbox.courselist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast
import br.com.trancosowiliam.eadbox.GridDecoration
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.dpToPx
import br.com.trancosowiliam.eadbox.isVisible
import kotlinx.android.synthetic.main.activity_course_list.*
import org.koin.android.ext.android.inject

class CourseListActivity : AppCompatActivity(), CourseListContract.View{
    override val presenter by inject<CourseListContract.Presenter>()
    private val categorySlut by lazy { intent.getStringExtra(EXTRA_CATEGORIE_SLUG) ?: "" }

    companion object {
        private const val EXTRA_CATEGORIE_SLUG = "categorie_slug"

        fun newIntent(context:Context, slug:String) =
                Intent(context, CourseListActivity::class.java).apply {
                    this.putExtra(EXTRA_CATEGORIE_SLUG, slug)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)
        setupViews()

        presenter.view = this
        presenter.loadCourses(categorySlut)
    }

    private fun setupViews() {
        clRecCourses.layoutManager = GridLayoutManager(this, 2)
        clRecCourses.addItemDecoration(GridDecoration(16.dpToPx(), 8.dpToPx()))
    }

    override fun showCourses(courses: List<Course>) {
        val adapter = CourseListAdapter(courses)
        adapter.onClickListener = {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
        }
        clRecCourses.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        clRecCourses.isVisible = !show
        clLoading.isVisible = show
    }
}
