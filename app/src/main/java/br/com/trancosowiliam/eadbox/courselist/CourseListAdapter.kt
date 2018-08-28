package br.com.trancosowiliam.eadbox.courselist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Course
import br.com.trancosowiliam.eadbox.dpToPx
import br.com.trancosowiliam.eadbox.rounded
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_course.view.*

class CourseListAdapter(private val courses:List<Course>) : RecyclerView.Adapter<CourseListAdapter.Holder>() {

    var onClickListener: ((Course) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_course, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(courses[position])
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(courses[position])
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun render(item: Course) {
            Picasso.get()
                    .load(item.logoUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .rounded(16.dpToPx())
                    .into(itemView.icImgLogo)
        }
    }
}