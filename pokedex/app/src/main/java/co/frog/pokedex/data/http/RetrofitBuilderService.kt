package co.frog.pokedex.data.http

import co.frog.pokedex.BuildConfig
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilderService {

    fun getInstance(baseUrl: String): Retrofit {

        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(10, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(interceptor)
        }

        val client = builder.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .build()
    }
}