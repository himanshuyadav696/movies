package com.example.moviess
import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
const val BASE_URL ="https://api.themoviedb.org/3/movie/"
const val BASE_URL2="https://api.themoviedb.org/3/movie/"
const val BASE_URL3="https://api.themoviedb.org/3/tv/"
const val BASE_URL4=" https://api.themoviedb.org/"
const val BASE_URL5 ="https://api.themoviedb.org/"
class SecondActivity : AppCompatActivity() {

    lateinit var pmrecyclerview:RecyclerView
    lateinit var MyAdaptor:MyAdaptor

    lateinit var uprecyclerview:RecyclerView
    lateinit var MyAdaptor2:MyAdaptor2

    lateinit var tvrecyclerview:RecyclerView
    lateinit var MyAdaptor3:MyAdaptor3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        pmrecyclerview =findViewById(R.id.pmrecyclerview)
        pmrecyclerview.setHasFixedSize(true)
        pmrecyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        uprecyclerview =findViewById(R.id.uprecyclerview)
        uprecyclerview.setHasFixedSize(true)
        uprecyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        tvrecyclerview=findViewById(R.id.tvrecyclerview)
        tvrecyclerview.setHasFixedSize(true)
        tvrecyclerview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        getMyData()
        getMyData2()
        getMyData3()
        }
    private fun getMyData() {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(apiInterface::class.java)

        val retrofitData =retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<PopularMovies?> {
            override fun onResponse(
                call: Call<PopularMovies?>,
                response: Response<PopularMovies?>
            ) {
                val responseBody = response.body()!!

                MyAdaptor = MyAdaptor(this@SecondActivity,responseBody.results)
                MyAdaptor.notifyDataSetChanged()
                pmrecyclerview.adapter=MyAdaptor

            }

            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
                Log.d("mainActivity", "on failure" + t.message)
            }
        })

    }
    private fun getMyData2() {
        val retrofitBuilder1= Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL2)
            .build()
            .create(UpApi::class.java)

        val retrofitData2 =retrofitBuilder1.getData2()
        retrofitData2.enqueue(object : Callback<PopularMovies?> {
            override fun onResponse(
                call: Call<PopularMovies?>,
                response: Response<PopularMovies?>
            ) {
                val responseBody = response.body()!!
                MyAdaptor2 = MyAdaptor2(this@SecondActivity,responseBody.results)
                MyAdaptor2.notifyDataSetChanged()
                uprecyclerview.adapter=MyAdaptor2

            }

            override fun onFailure(call: Call<PopularMovies?>, t: Throwable) {
                Log.d("mainActivity", "on failure" + t.message)
            }
        })

    }

    private fun getMyData3(){
        val retrofitBuilder3=Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL3)
            .build()
            .create(TvApiInterface::class.java)

        val retrofitData3= retrofitBuilder3.getMyData3()
        retrofitData3.enqueue(object : Callback<TvShows?> {
            override fun onResponse(call: Call<TvShows?>, response: Response<TvShows?>) {
                val responseBody =response.body()!!
                MyAdaptor3 =MyAdaptor3(this@SecondActivity,responseBody.results)
                MyAdaptor3.notifyDataSetChanged()
                tvrecyclerview.adapter=MyAdaptor3

            }

            override fun onFailure(call: Call<TvShows?>, t: Throwable) {
                Log.d("mainActivity", "on failure" + t.message)
            }
        })
    }
}

