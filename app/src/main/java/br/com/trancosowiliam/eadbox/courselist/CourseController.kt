package br.com.trancosowiliam.eadbox.courselist

import br.com.trancosowiliam.eadbox.data.model.Course
import com.airbnb.epoxy.TypedEpoxyController

class CourseController : TypedEpoxyController<List<Course>>(){

    var listener:((Course) -> Unit)? = null

    override fun buildModels(data: List<Course>?) {
        data?.forEach {course ->
            CourseModel_()
                    .id(course.courseId)
                    .txtId(course.courseId)
                    .listener(object : CourseModel.OnClickListener {
                        override fun onClick() {
                            listener?.invoke(course)
                        }

                    })
                    .addTo(this)
        }


    }
}