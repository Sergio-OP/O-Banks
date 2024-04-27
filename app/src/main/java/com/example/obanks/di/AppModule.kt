package com.example.obanks.di

import android.content.Context
import androidx.room.Room
import com.example.obanks.data.datasources.local.BankEntityDao
import com.example.obanks.data.datasources.local.OBanksDatabase
import com.example.obanks.data.datasources.remote.OBanksService
import com.example.obanks.data.repositories.BanksRepositoryImpl
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.use_cases.GetBanksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesOBanksDatabase(@ApplicationContext appContext: Context): OBanksDatabase = Room.databaseBuilder(
        context = appContext,
        klass = OBanksDatabase::class.java,
        name = "o_banks_database"
    ).build()

    @Singleton
    @Provides
    fun providesBankEntityDao(oBanksDatabase: OBanksDatabase): BankEntityDao =
        oBanksDatabase.bankEntityDao()

    @Singleton
    @Provides
    fun providesBanksRepository(oBanksService: OBanksService, bankEntityDao: BankEntityDao): BanksRepository =
        BanksRepositoryImpl(
            banksService = oBanksService,
            bankEntityDao = bankEntityDao
        )

    @Singleton
    @Provides
    fun providesGetBanksUseCase(banksRepository: BanksRepository): GetBanksUseCase =
        GetBanksUseCase(banksRepository)

}