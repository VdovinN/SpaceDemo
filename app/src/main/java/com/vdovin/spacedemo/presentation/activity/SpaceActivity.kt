package com.vdovin.spacedemo.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdovin.spacedemo.R
import com.vdovin.spacedemo.presentation.util.extension.setupStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceActivity : AppCompatActivity(R.layout.activity_space) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupStatusBarColor()
    }

}