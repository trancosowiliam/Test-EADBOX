package br.com.trancosowiliam.eadbox.course

import br.com.trancosowiliam.eadbox.BasePresenter
import br.com.trancosowiliam.eadbox.BaseView

interface CourseContract{
    interface Presenter : BasePresenter<View>
    interface View : BaseView<Presenter>
}