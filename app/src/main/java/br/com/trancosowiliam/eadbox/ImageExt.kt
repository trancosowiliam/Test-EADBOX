package br.com.trancosowiliam.eadbox

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation
import java.lang.Exception


fun RequestCreator.rounded(radius:Number) : RequestCreator {
    this.transform(object : Transformation {
        override fun key() = "$radius"

        override fun transform(source: Bitmap?): Bitmap {
            source?.let { source ->
                val paint = Paint()
                paint.isAntiAlias = true
                paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

                val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(output)
                canvas.drawRoundRect(RectF(0f, 0f, source.width.toFloat(), source.height.toFloat()), radius.toFloat(), radius.toFloat(), paint)

                if (source !== output) {
                    source.recycle()
                }

                return output
            }

            return Bitmap.createBitmap(source)
        }
    })

    return this
}

fun RequestCreator.into(img:ImageView, onLoaded:()->Unit) {
    this.into(img, object : Callback {
        override fun onSuccess() {
            onLoaded()
        }
        override fun onError(e: Exception?) {}
    })
}

fun View.setBgColor(@ColorInt color:Int) {
    (this.background as? GradientDrawable)?.let {
        it.setColor(color)
    }
}

@ColorInt
fun ImageView.getColorPixel(x:Int, y:Int): Int {
    return (this.drawable as? BitmapDrawable)?.let {
        it.bitmap.getPixel(x, y)
    } ?: Color.TRANSPARENT
}