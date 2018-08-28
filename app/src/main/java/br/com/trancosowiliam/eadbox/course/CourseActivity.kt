package br.com.trancosowiliam.eadbox.course

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import br.com.trancosowiliam.eadbox.*
import br.com.trancosowiliam.eadbox.data.model.Course
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_course.*
import org.koin.android.ext.android.inject

class CourseActivity : AppCompatActivity(), CourseContract.View {
    override val presenter by inject<CourseContract.Presenter>()
    private val course by lazy { intent.getParcelableExtra<Course>(EXTRA_COURSE)}

    companion object {
        private const val EXTRA_COURSE = "course"

        fun newIntent(context: Context, course:Course) =
                Intent(context, CourseActivity::class.java).apply {
                    this.putExtra(EXTRA_COURSE, course)
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        presenter.view = this

        setupView()
    }

    private fun setupView() {
        Picasso.get()
                .load(course.logoUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .rounded(16.dpToPx())
                .into(coImgLogo){
                    coContainer.setBackgroundColor(coImgLogo.getColorPixel(16.dpToPx(),16.dpToPx()))
                }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            coTxtDescription.text = Html.fromHtml(course.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            coTxtDescription.text = Html.fromHtml(course.description)
        }
    }
}
