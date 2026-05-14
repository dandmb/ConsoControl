package com.dmb25.consoprotection.di

import com.dmb25.consoprotection.presentation.ui.product_detail.ProductDetailsViewModel
import com.dmb25.consoprotection.presentation.ui.product_list.ProductListViewModel
import com.dmb25.consoprotection.presentation.ui.codescan.ScannerViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val presentationModule = module {
    viewModel { ProductListViewModel(get()) }
    viewModel { ProductDetailsViewModel(get()) }
    viewModel { ScannerViewModel(get(), get()) }
}