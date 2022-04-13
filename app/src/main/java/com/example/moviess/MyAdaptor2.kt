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

class MyAdaptor2(val context: Context, val users:List<PopularMovies.Result>) :
    RecyclerView.Adapter<MyAdaptor2.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var date: TextView
        var tittle: TextView
        var linearLayout2:LinearLayout
       // var id : TextView
       // var vote: TextView
        var image:ImageView
        init {
            tittle =itemView.findViewById(R.id.Tittle)
           date =itemView.findViewById(R.id.date)
           // id =itemView.findViewById(R.id.id)
            //vote=itemView.findViewById(R.id.Vote)
            image =itemView.findViewById(R.id.imageView)
            linearLayout2 =itemView.findViewById(R.id.LinearLayout2)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items2,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tittle.text=users[position].original_title.toString()
        holder.date.text=users[position].release_date.toString()
       // holder.id.text=userlist[position].id.toString()
        //holder.vote.text =userlist[position].vote_count.toString()

        Glide.with(context).load("https://image.tmdb.org/t/p/w500${users[position].poster_path}")
            .into(holder.image)

        holder.linearLayout2.setOnClickListener{
            val intent =Intent(context,MainActivity::class.java)
            val abc=users[position]
            intent.putExtra("tittle",abc)
            println(abc)
            context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return users.size
    }
}

