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


class MyAdaptor(val context: Context, val userlist:List<PopularMovies.Result>) :
    RecyclerView.Adapter<MyAdaptor.ViewHolder>() {
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val date:TextView
        val ptittle:TextView
        val image:ImageView

        val linearLayout:LinearLayout
        init {
            ptittle =itemView.findViewById(R.id.Tittle)
            date =itemView.findViewById(R.id.date)
            image =itemView.findViewById(R.id.imageView)
            linearLayout =itemView.findViewById(R.id.LinearLayout)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ptittle.text=userlist[position].original_title.toString()
        holder.date.text=userlist[position].release_date.toString()
        //val id =userlist[position].id.toInt()




        Glide.with(context).load("https://image.tmdb.org/t/p/w500${userlist[position].poster_path}")
            .into(holder.image)

        holder.linearLayout.setOnClickListener{
            val intent =Intent(context,MainActivity::class.java)
            val abc=userlist[position]
            intent.putExtra("ptittle",abc)
            //intent.putExtra("id",userlist[position].id)
            println(abc)
           context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return userlist.size
    }
}