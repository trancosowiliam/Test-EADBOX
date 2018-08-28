package br.com.trancosowiliam.eadbox.categorieslist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import br.com.trancosowiliam.eadbox.GridDecoration
import br.com.trancosowiliam.eadbox.R
import br.com.trancosowiliam.eadbox.courselist.CourseListActivity
import br.com.trancosowiliam.eadbox.data.model.Category
import br.com.trancosowiliam.eadbox.dpToPx
import br.com.trancosowiliam.eadbox.isVisible
import kotlinx.android.synthetic.main.activity_categorie_list.*
import org.koin.android.ext.android.inject

class CategorieListActivity : AppCompatActivity(), CategorieListContract.View{
    override val presenter by inject<CategorieListContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie_list)
        setupViews()

        presenter.view = this
        presenter.loadCategories()
    }

    override fun showCategories(categories: List<Category>) {
        val adapter = CategoriesListAdapter(categories)
        adapter.onClickListener = { category ->
            startActivity(CourseListActivity.newIntent(this, category.slug))
        }
        ctlRecCategories.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        ctlRecCategories.isVisible = !show
        ctlLoading.isVisible = show
    }

    private fun setupViews() {
        ctlRecCategories.layoutManager = GridLayoutManager(this, 2)
        ctlRecCategories.addItemDecoration(GridDecoration(16.dpToPx(), 8.dpToPx()))
    }
}
