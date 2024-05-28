package com.example.logique_test.data;

import android.content.Context
import com.example.logique_test.data.services.local.AppDatabase
import com.example.logique_test.data.services.local.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun providesPostDao(
        database: AppDatabase
    ) : PostDao {
        return database.postDao()
    }
}