package cl.diegoacuna.reigntest.service

import cl.diegoacuna.reigntest.model.remote.DataApi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiService {

    @GET("api/v1/search_by_date")
    fun getAllNews(@Query("query") type :String): Observable<DataApi>


     object Factory{

         fun create(url: String):NewApiService{
             val logging = HttpLoggingInterceptor()
             logging.level = HttpLoggingInterceptor.Level.BODY
             val httpClient = OkHttpClient.Builder()
             httpClient.addInterceptor(logging)

             val retrofit = Retrofit.Builder()
                 .baseUrl(url)
                 .client(httpClient.build())
                 .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .build()
             return retrofit.create(NewApiService::class.java)
         }
    }
}