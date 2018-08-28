package br.com.trancosowiliam.eadbox.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [(ForeignKey(entity = Category::class, parentColumns = ["slug"], childColumns = ["categoryId"], onDelete = ForeignKey.CASCADE))])
data class Course(
        @PrimaryKey
        @SerializedName("course_id")
        val courseId: String,

        @SerializedName("logo_url")
        val logoUrl: String,

        @SerializedName("description")
        val description: String,

        @Transient
        val categoryId: String) : Parcelable
{
        constructor(source: Parcel) : this(
                source.readString(),
                source.readString(),
                source.readString(),
                source.readString()
        )

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