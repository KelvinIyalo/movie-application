package com.kelviniyalo.moviesapp.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.palette.graphics.Palette
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.kelviniyalo.moviesapp.R
import java.io.ByteArrayOutputStream

object Helper {

    private fun getProgressDrawable(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }
    }

    fun ImageView.loadImage(uri: String, context: Context,getImageResult:(Bitmap) -> Unit = {}) {
        val option = RequestOptions()
            .placeholder(Helper.getProgressDrawable(context))
            .error(R.drawable.warning_24)
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .asBitmap()
            .load(uri)
            .fitCenter()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {
                    this@loadImage.setImageBitmap(resource)
                    getImageResult.invoke(resource)
                }


                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle placeholder
                }
            })
    }


     fun byteArrayToBitmap(data: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data.size)
    }

     fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}