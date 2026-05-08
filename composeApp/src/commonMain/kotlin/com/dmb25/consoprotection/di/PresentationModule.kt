package com.dmb25.consoprotection.di

import com.dmb25.consoprotection.presentation.ui.product_list.ProductListViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val presentationModule = module {
    viewModel { ProductListViewModel(get()) }
}