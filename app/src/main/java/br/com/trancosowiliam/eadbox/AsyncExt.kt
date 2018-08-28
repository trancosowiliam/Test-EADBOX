package br.com.trancosowiliam.eadbox

import android.os.AsyncTask

fun <T> asyncExec(exec:() -> T, callback:(T) -> Unit) {
    object : AsyncTask<Unit, Unit, T>() {
        override fun doInBackground(vararg params: Unit?): T {
            return exec()
        }

        override fun onPostExecute(result: T) {
            callback(result)
        }
    }.execute()
}