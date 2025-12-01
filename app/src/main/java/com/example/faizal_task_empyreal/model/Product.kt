package com.example.faizal_task_empyreal.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: List<String>,
    val count: Int = 0
)