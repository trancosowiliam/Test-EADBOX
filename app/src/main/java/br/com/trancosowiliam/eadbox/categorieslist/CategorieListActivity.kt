package br.com.trancosowiliam.eadbox.categorieslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import br.com.trancosowiliam.eadbox.GridDecoration
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.data.model.Categorie
import br.com.trancosowiliam.eadbox.dpToPx
import kotlinx.android.synthetic.main.activity_categorie_list.*
import org.koin.android.ext.android.inject

class CategorieListActivity : AppCompatActivity(), CategorieListContract.View{
    override val presenter by inject<CategorieListContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie_list)

        presenter.view = this

        presenter.loadCategories()
    }

    override fun showLoadDialog() {
    }

    override fun showCategories(categories: List<Categorie>) {
        ctlRecCategories.layoutManager = GridLayoutManager(this, 2)
        ctlRecCategories.addItemDecoration(GridDecoration(16.dpToPx(), 8.dpToPx()))
        val adapter = CategoriesListAdapter(categories)
        adapter.onClickListener = {
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show()
        }
        ctlRecCategories.adapter = adapter
    }

    override fun dismissLoadDialog() {
    }
}
