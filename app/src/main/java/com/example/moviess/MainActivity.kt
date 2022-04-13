package com.example.moviess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.Unit.toString

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var tv:TextView
    lateinit var date:TextView
    lateinit var overview:TextView
    lateinit var imageview:ImageView
    lateinit var cast:TextView
    lateinit var logo:ImageView
    lateinit var average_count:TextView
    //lateinit var imagelogo:ImageView
    //lateinit var show:TextView


    //lateinit var imageview5:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.wbutton)

        tv =findViewById(R.id.tv_mtitle)
        date = findViewById(R.id.rdate)

        overview =findViewById(R.id.overview)
        imageview =findViewById(R.id.imageview1)

        cast = findViewById(R.id.cast)
        logo =findViewById(R.id.logo)
        average_count=findViewById(R.id.averagecount)
        //imagelogo=findViewById(R.id.imagelogo)
      //  imageview5 =findViewById(R.id.imageview5)


        val tv1:PopularMovies.Result?=intent.getParcelableExtra("ptittle")
        val tv2:PopularMovies.Result?=intent.getParcelableExtra("tittle")
        val tv3:TvShows.Result?=intent.getParcelableExtra("title")
        //val tv2=intent.getStringExtra("tittle").toString()
       // val date=intent.getStringExtra("date").toString()
       if(tv1!=null) {
            tv.text = tv1.original_title
            date.text = tv1.release_date
            overview.text = tv1.overview
           average_count.text =tv1.vote_average.toString()

            Glide.with(imageview).load("https://image.tmdb.org/t/p/w500${tv1.poster_path}")
                .into(imageview)
                        }

        if(tv2 !=null) {
            tv.text = tv2.original_title
            date.text = tv2.release_date
            overview.text = tv2.overview
            Glide.with(imageview).load("https://image.tmdb.org/t/p/w500${tv2.poster_path}")
                .into(imageview)
        }

        if(tv3!=null)
        {
            tv.text = tv3.original_name
            date.text = tv3.first_air_date
            overview.text = tv3.overview
            Glide.with(imageview).load("https://image.tmdb.org/t/p/w500${tv3.poster_path}")
                .into(imageview)
        }
        //tv.text=tv2
        //preleseadate.text=date

        button.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
            getMyData4(tv1)
        getMyData4(tv2)
        getMyData5(tv3)
    }
    private fun getMyData4(tv1:PopularMovies.Result?) {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL4)
            .build()
            .create(MovieDetailInterface::class.java)
        val retrofitData =retrofitBuilder.getMyData4(tv1?.id)
        retrofitData.enqueue(object : Callback<MovieDetails?> {
            override fun onResponse(
                call: Call<MovieDetails?>,
                response: Response<MovieDetails?>
            ) {
                val responseBody = response.body()
                responseBody?.production_companies?.let {
                    cast.text = it[0].name

                    Glide.with(logo).load("https://image.tmdb.org/t/p/w500${it[0].logo_path}")
                        .into(logo)
                }
            }

            override fun onFailure(call: Call<MovieDetails?>, t: Throwable) {
                Log.d("mainActivity", "on failure" + t.message)
            }
        })

    }

   /* private fun getMyData5(tv2:PopularMovies.Result?) {
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL4)
            .build()
            .create(MovieDetailInterface::class.java)

        val retrofitData =retrofitBuilder.getMyData5(tv2?.id)
        retrofitData.enqueue(object : Callback<MovieDetails?> {
            override fun onResponse(
                call: Call<MovieDetails?>,
                response: Response<MovieDetails?>
            ) {
                val responseBody = response.body()
                // if(responseBody!=null) {

                if(responseBody!=null) {
                    tv.text = tv2?.original_title
                    date.text = tv2?.release_date
                    overview.text = tv2?.overview

                    Glide.with(imageview).load("https://image.tmdb.org/t/p/w500${tv2?.poster_path}")
                        .into(imageview)
                }
                cast.text=responseBody?.production_companies.toString()

                // }

            }

            override fun onFailure(call: Call<MovieDetails?>, t: Throwable) {
                Log.d("mainActivity", "on failure" + t.message)
            }
        })

    }*/
   private fun getMyData5(tv3:TvShows.Result?) {
       val retrofitBuilder= Retrofit.Builder()
           .addConverterFactory(MoshiConverterFactory.create())
           .baseUrl(BASE_URL5)
           .build()
           .create(TvshowdetailInterface::class.java)

       val retrofitData =retrofitBuilder.getMyData5(tv3?.id)
       retrofitData.enqueue(object : Callback<Tvshowsdetail?> {
           override fun onResponse(
               call: Call<Tvshowsdetail?>,
               response: Response<Tvshowsdetail?>
           ) {
               val responseBody = response.body()
               if(responseBody!=null) {
                   responseBody.production_companies.let {
                       if(it.size==0){
                           cast.text =cast.toString()
                       }
                       else {
                           cast.text = it[0].name
                           Glide.with(logo)
                               .load("https://image.tmdb.org/t/p/w500${it[0].logo_path}")
                               .into(logo)
                       }

                   }
               }

           }

           override fun onFailure(call: Call<Tvshowsdetail?>, t: Throwable) {
               Log.d("mainActivity", "on failure" + t.message)
           }
       })

   }
}