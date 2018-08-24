package br.com.trancosowiliam.eadbox.data.model

import com.google.gson.annotations.SerializedName

data class Course(
        @SerializedName("course_id")
        val courseId:String
)