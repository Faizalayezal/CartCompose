package com.example.faizal_task_empyreal.di

import com.example.faizal_task_empyreal.repository.ProductRepository
import com.example.faizal_task_empyreal.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { ProductRepository(androidContext()) }

    single { ProductViewModel(get()) }
 // viewModel { ProductViewModel(get()) }
}
