package com.koshake1.testusersphoto.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koshake1.testusersphoto.model.api.ApiService
import com.koshake1.testusersphoto.model.data.repository.Repository
import com.koshake1.testusersphoto.model.data.repository.RepositoryImpl
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource
import com.koshake1.testusersphoto.model.datasources.RemoteDataSourceImpl
import com.koshake1.testusersphoto.viewmodel.UsersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_URL = "https://jsonplaceholder.typicode.com/"

val retrofitModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
}
