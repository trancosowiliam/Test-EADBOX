package br.com.trancosowiliam.eadbox.courselist

import android.support.v7.widget.AppCompatTextView
import android.view.View
import br.com.trancosowiliam.eadbox.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_course)
abstract class CourseModel : EpoxyModelWithHolder<CourseModel.Holder>() {

    @EpoxyAttribute
    var txtId: String? = null

    @EpoxyAttribute(hash = false)
    var listener: OnClickListener? = null

    override fun bind(holder: Holder) {
        holder.txtId?.text = txtId
        holder.itemView?.setOnClickListener {
            listener?.onClick()
        }
    }

    class Holder : EpoxyHolder() {
        var itemView: View? = null
        var txtId: AppCompatTextView? = null

        override fun bindView(itemView: View?) {
            this.itemView = itemView
            txtId = itemView?.findViewById(R.id.icTxtId)
        }
    }

    interface OnClickListener {
        fun onClick()
    }
}