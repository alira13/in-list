package com.example.inlist.di

import android.app.Application
import com.example.inlist.currentList.presentation.CurrentListFragment
import com.example.inlist.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(currentListFragment: CurrentListFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }
}