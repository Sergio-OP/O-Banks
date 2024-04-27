package com.example.obanks.di

import com.example.obanks.data.datasources.remote.OBanksService
import com.example.obanks.data.repositories.BanksRepositoryImpl
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.use_cases.GetBanksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://dev.obtenmas.com/catom/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun providesOBanksService(retrofit: Retrofit): OBanksService =
        retrofit.create(OBanksService::class.java)


    @Singleton
    @Provides
    fun providesBanksRepository(oBanksService: OBanksService): BanksRepository =
        BanksRepositoryImpl(oBanksService)

    @Singleton
    @Provides
    fun providesGetBanksUseCase(banksRepository: BanksRepository): GetBanksUseCase =
        GetBanksUseCase(banksRepository)

}