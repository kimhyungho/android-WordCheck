package com.appdev.wordcheck.di

import com.appdev.wordcheck.data.remote.api.UserService
import com.appdev.wordcheck.data.remote.datasource.UserRemoteDataSource
import com.appdev.wordcheck.data.remote.datasource.UserRemoteDataSourceImpl
import com.appdev.wordcheck.data.repository.UserRepo
import com.appdev.wordcheck.data.repository.UserRepoImpl
import com.appdev.wordcheck.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
//    single {
//        Room.databaseBuilder(get(), ProfileDatabase::class.java, "profile.db")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//    single { get<ProfileDatabase>().profileDao() }
}

val networkModule = module {
    single<UserService> {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://sulrae.com:9900/api/")
            .build()
            .create(UserService::class.java)
    }
}

val localDataSourceModule = module {
}

val remoteDataSourceModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepo> { UserRepoImpl(get())}
}

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
}

val DiModule = listOf(
    databaseModule,
    networkModule,
    localDataSourceModule,
    remoteDataSourceModule,
    repositoryModule,
    viewModelModule
)