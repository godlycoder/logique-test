package com.example.logique_test.domain

import com.example.logique_test.data.repository.PostRepositoryImpl
import com.example.logique_test.data.repository.ProfileRepositoryImpl
import com.example.logique_test.data.repository.UserRepositoryImpl
import com.example.logique_test.domain.repository.PostRepository
import com.example.logique_test.domain.repository.ProfileRepository
import com.example.logique_test.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl) : UserRepository

    @Binds
    abstract fun bindPostRepository(repositoryImpl: PostRepositoryImpl) : PostRepository

    @Binds
    abstract fun bindProfileRepository(repositoryImpl: ProfileRepositoryImpl) : ProfileRepository
}