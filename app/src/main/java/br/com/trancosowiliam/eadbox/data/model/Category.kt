package br.com.trancosowiliam.eadbox.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Category(
        @SerializedName("title")
        var title: String,

        @PrimaryKey
        @SerializedName("category_slug")
        var slug: String,

        @SerializedName("courses_count")
        var coursesCount: Int)
{
    constructor() : this("", "", 0)
}