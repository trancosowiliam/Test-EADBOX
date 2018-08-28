package br.com.trancosowiliam.eadbox.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [(ForeignKey(entity = Category::class, parentColumns = ["slug"], childColumns = ["categoryId"], onDelete = ForeignKey.CASCADE, deferred = true))])
data class Course(
        @PrimaryKey
        @SerializedName("course_id")
        var courseId: String,

        @SerializedName("logo_url")
        var logoUrl: String,

        @SerializedName("description")
        var description: String,

        var categoryId: String) : Parcelable
{
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

        constructor() : this("", "", "", "")

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
                writeString(courseId)
                writeString(logoUrl)
                writeString(description)
                writeString(categoryId)
        }

        companion object {
                @JvmField
                val CREATOR: Parcelable.Creator<Course> = object : Parcelable.Creator<Course> {
                        override fun createFromParcel(source: Parcel): Course = Course(source)
                        override fun newArray(size: Int): Array<Course?> = arrayOfNulls(size)
                }
        }
}