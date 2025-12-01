package com.example.faizal_task_empyreal.repository

import android.content.Context
import com.example.faizal_task_empyreal.R
import com.example.faizal_task_empyreal.model.Product
import com.google.gson.Gson

class ProductRepository(private val context: Context) {

    suspend fun getProducts(): List<Product> {
        val inputStream = context.resources.openRawResource(R.raw.products)
        val json = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}