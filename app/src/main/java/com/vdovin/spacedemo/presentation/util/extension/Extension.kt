package com.vdovin.spacedemo.presentation.util.extension

import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vdovin.spacedemo.R
import com.vdovin.spacedemo.presentation.util.image.GlideApp

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun TextView.setTextOrHide(text: String?) {
    return if (text.isNullOrEmpty() || text.isNullOrBlank()) {
        this.visibility = View.GONE
    } else {
        this.text = text
    }
}

fun TextView.setTextOrHide(resId: Int, vararg text: Any?) {
    if (text.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.text = context.getString(resId, *text)
    }
}

fun AppCompatActivity.setupStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.generalGray)
    }
}

fun ImageView.loadRoundImage(context: Context, url: String, cornerRadius: Int) {
    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.ic_rocket)
        .error(R.drawable.ic_rocket)
        .transform(CenterCrop(), RoundedCorners(cornerRadius))
        .into(this)
}

fun ImageView.loadImage(url: String) {
    GlideApp.with(context)
        .load(url)
        .placeholder(R.drawable.ic_rocket)
        .error(R.drawable.ic_rocket)
        .into(this)
}