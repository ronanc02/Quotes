package com.example.quotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.quotes.data.Quotes
import com.example.quotes.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity() : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotegroup = findViewById<TextView>(R.id.quotegroup)
        val quoteauthor = findViewById<TextView>(R.id.quoteauthor)
        val quotetext = findViewById<TextView>(R.id.quotetext)
        val next = findViewById<FloatingActionButton>(R.id.fab)

        val text = FileHelper.getTextFromResources(this, R.raw.quotes)
        //Log.i("data",text)

        val listQuotesType = Types.newParameterizedType(
            List::class.java, Quotes::class.java
        )

        val quotes: List<Quotes> = Gson().fromJson(text, listQuotesType)
        //Log.i("data", quotes.toString())
        val random = quotes.random()
        quotegroup.text = random.quotegroup
        quoteauthor.text = random.quoteauthor
        quotetext.text = random.quotetext

        next.setOnClickListener {
            val random2 = quotes.random()
            val brackets =" '' "
            quotegroup.text = random2.quotegroup
            quotetext.text = brackets+random2.quotetext+brackets
            quoteauthor.text = brackets+random2.quoteauthor+brackets
        }
    }
}
