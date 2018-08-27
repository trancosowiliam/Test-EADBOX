package br.com.trancosowiliam.eadbox

import android.graphics.*
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Transformation


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