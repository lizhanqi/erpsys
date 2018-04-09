package com.suxuantech.erpsys.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.suxuantech.erpsys.R

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val findViewById = findViewById<ImageView>(R.id.big_img)
        Glide.with(this).load( intent.getStringExtra("uri")) .into(findViewById)
    }
}
