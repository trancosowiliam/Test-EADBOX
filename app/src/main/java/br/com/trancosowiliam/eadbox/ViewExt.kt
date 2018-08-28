package br.com.trancosowiliam.eadbox

import android.content.res.Resources
import android.view.View

fun Int.dpToPx(): Int {
    val metrics = Resources.getSystem().displayMetrics
    val px = this * (metrics.densityDpi / 160f)
    return Math.round(px)
}


var View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE
    set(value) {
        this.visibility = if(value)  View.VISIBLE else View.GONE
    }

