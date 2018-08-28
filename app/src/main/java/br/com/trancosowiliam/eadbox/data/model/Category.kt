package br.com.trancosowiliam.eadbox.data.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Category(
        @SerializedName("title")
        val title:String,

        @SerializedName("category_slug")
        val slug:String,

        @SerializedName("courses_count")
        val coursesCount:Int
)