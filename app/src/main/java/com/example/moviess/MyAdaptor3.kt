package com.example.moviess

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdaptor3(val context: Context, val userlist:List<TvShows.Result>) :
    RecyclerView.Adapter<MyAdaptor3.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       // var language: TextView
        var tittle: TextView
        var date : TextView
       // var vote: TextView
        var image:ImageView

        val linearLayout3:LinearLayout
        init {
            tittle =itemView.findViewById(R.id.Tittle)
            date =itemView.findViewById(R.id.date)
            //id =itemView.findViewById(R.id.id)
            //vote=itemView.findViewById(R.id.Vote)
            image=itemView.findViewById(R.id.imageView)

            linearLayout3 =itemView.findViewById(R.id.LinearLayout3)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.rows_item3,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tittle.text=userlist[position].original_name.toString()
        holder.date.text=userlist[position].first_air_date.toString()
        //holder.id.text=userlist[position].id.toString()
        //holder.vote.text =userlist[position].vote_count.toString()

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/${userlist[position].poster_path}")
        .into(holder.image)

        holder.linearLayout3.setOnClickListener{
            val intent = Intent(context,MainActivity::class.java)
            val abc=userlist[position]
            intent.putExtra("title",abc)
            println(abc)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return userlist.size
    }
}