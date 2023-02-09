package com.sepiainnovations.petsapp.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.sepiainnovations.petsapp.model.network.ApiInterface
import com.sepiainnovations.petsapp.model.network.ResultCallAdapterFactory
import com.sepiainnovations.petsapp.model.repo.PetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAsset(@ApplicationContext context: Context, apiInterface: ApiInterface): PetsRepository{
        return PetsRepository(context.assets, apiInterface)
    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/w/api.php/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


}