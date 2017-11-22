package com.prediction.tobe.di.app

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.prediction.tobe.di.DependencyManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class FirebaseDBModule {
    companion object {
        private const val NODE_NAME_USERS = "users"
    }

    @Provides
    @Named(DependencyManager.DB_USERS)
    @Singleton
    fun provideUserDB(): DatabaseReference = FirebaseDatabase.getInstance().getReference(NODE_NAME_USERS);

}