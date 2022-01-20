package com.koshake1.testusersphoto.di

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.LruCache
import android.widget.ImageView
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.koshake1.testusersphoto.APP_PREFERENCES
import com.koshake1.testusersphoto.model.api.ApiService
import com.koshake1.testusersphoto.model.api.ApiServiceImage
import com.koshake1.testusersphoto.model.data.repository.PhotosRepository
import com.koshake1.testusersphoto.model.data.repository.PhotosRepositoryImpl
import com.koshake1.testusersphoto.model.data.repository.UserRepository
import com.koshake1.testusersphoto.model.data.repository.UserRepositoryImpl
import com.koshake1.testusersphoto.model.datasources.ImageDataSource
import com.koshake1.testusersphoto.model.datasources.ImageDataSourceImpl
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource
import com.koshake1.testusersphoto.model.datasources.RemoteDataSourceImpl
import com.koshake1.testusersphoto.model.image.ImageLoader
import com.koshake1.testusersphoto.model.image.ImageLoaderImpl
import com.koshake1.testusersphoto.model.image.cache.ImageCache
import com.koshake1.testusersphoto.model.image.cache.MemoryCache
import com.koshake1.testusersphoto.model.interactor.PhotosInteractor
import com.koshake1.testusersphoto.model.interactor.PhotosInteractorImpl
import com.koshake1.testusersphoto.viewmodel.PhotosViewModel
import com.koshake1.testusersphoto.viewmodel.UsersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

const val BASE_API_URL = "https://jsonplaceholder.typicode.com/"
const val BASE_API_URL_IMAGE = "https://via.placeholder.com/600/"

val retrofitModule = module {
    single<ApiService>(named("default")) {
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

    single<ApiServiceImage>(named("image")) {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_API_URL_IMAGE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiServiceImage::class.java)
    }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get<ApiService>(named("default"))) }
    single<ImageDataSource> { ImageDataSourceImpl(get<ApiServiceImage>(named("image"))) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<PhotosRepository> { PhotosRepositoryImpl(get()) }
}

val interactorModule = module {
    single<PhotosInteractor> { PhotosInteractorImpl(get()) }
}

val viewModelModule = module {
    viewModel { UsersViewModel(get()) }
    viewModel { PhotosViewModel(get()) }
}

val cacheModule = module {
    single<ImageCache> { MemoryCache() }
}

val imageLoaderModule = module {
    single<ImageLoader<ImageView>> { ImageLoaderImpl(get(), get()) }
}