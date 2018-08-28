package br.com.trancosowiliam.eadbox.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Course(
        @SerializedName("course_id")
        val courseId: String,

        @SerializedName("logo_url")
        val logoUrl: String,

        @SerializedName("description")
        val description: String) : Parcelable {
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString()
        )

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(courseId)
                writeString(logoUrl)
                writeString(description)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Course> = object : Parcelable.Creator<Course> {
                        override fun createFromParcel(source: Parcel): Course = Course(source)
                        override fun newArray(size: Int): Array<Course?> = arrayOfNulls(size)
                }
        }
}