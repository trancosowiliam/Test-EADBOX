package br.com.trancosowiliam.eadbox.data.model

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("title")
        val title:String,

        @SerializedName("category_slug")
        val slug:String
)