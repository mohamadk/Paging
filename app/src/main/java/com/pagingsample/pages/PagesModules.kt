package com.pagingsample.pages

import com.pagingsample.pages.home.HomeViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModules: Module = module {
    viewModel {
        HomeViewModel(homeRepository = get())
    }
}
