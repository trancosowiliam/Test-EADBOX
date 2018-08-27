package br.com.trancosowiliam.eadbox.categorieslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Categorie
import kotlinx.android.synthetic.main.item_categorie.view.*

class CategoriesListAdapter (private val categories:List<Categorie>) : RecyclerView.Adapter<CategoriesListAdapter.Holder>() {

    var onClickListener: ((Categorie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_categorie, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(categories[position])
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(categories[position])
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(item: Categorie) {
            itemView.ictTxtShadowIcon.text = item.title.elementAt(0).toString()
            itemView.ictTxtIcon.text = item.title.elementAt(0).toString()
            itemView.ictTxtTitle.text = item.title
        }
    }
}