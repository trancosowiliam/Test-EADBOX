package br.com.trancosowiliam.eadbox.categorieslist

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.random
import br.com.trancosowiliam.eadbox.setBgColor
import kotlinx.android.synthetic.main.item_categorie.view.*

class CategoriesListAdapter (private val categories:List<Category>) : RecyclerView.Adapter<CategoriesListAdapter.Holder>() {

    var onClickListener: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_categorie, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = categories.size + 1

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val category = categories.getOrElse(position) { Category("Todos", "", 1) }

        holder.render(category)
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(category)
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(item: Category) {
            itemView.ictTxtShadowIcon.text = item.title.elementAt(0).toString()
            itemView.ictTxtIcon.text = item.title.elementAt(0).toString()
            itemView.ictTxtTitle.text = item.title

            itemView.ictContent.setBgColor(Color.rgb((10..170).random(), (10..170).random(),(10..170).random()))
        }
    }
}