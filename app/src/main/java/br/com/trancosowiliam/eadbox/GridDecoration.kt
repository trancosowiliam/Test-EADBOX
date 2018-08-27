package br.com.trancosowiliam.eadbox

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class GridDecoration(private val margin: Int, private val internalMargin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildLayoutPosition(view) < 2) {
            outRect.top = 0
        } else {
            outRect.top = internalMargin
        }

        if(parent.getChildLayoutPosition(view).rem(2) == 0) {
            outRect.left = margin
            outRect.right = internalMargin/2
        } else {
            outRect.right = margin
            outRect.left = internalMargin/2
        }
    }
}