package com.nannaapp.kotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.nannaapp.kotlin.Model.ListItem
import com.nannaapp.kotlin.R
import kotlinx.android.synthetic.main.list_item.view.*


class ListItemAdapter(val context : Context, val data: ArrayList<ListItem>): RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val tvImageDesc = itemView.image_desc
        val tvImageName = itemView.image_text
        val imgThumbnail = itemView.thumbnail
        val imgFull = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ListItemAdapter.ViewHolder, position: Int) {
       val listItem : ListItem = data.get(position)
//        holder.imgFull.setImageResource()
//        holder.imgThumbnail.setImageResource()
        val url = GlideUrl(
            listItem.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        val urlThumbnail = GlideUrl(
            listItem.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide
            .with(holder.imgFull.getContext())
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .dontAnimate().
            into(holder.imgFull);
        Glide
            .with(holder.imgThumbnail.getContext())
            .load(urlThumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .dontAnimate()
            .into(holder.imgThumbnail)
        holder.tvImageDesc.text = listItem.title
        holder.tvImageName.text = "Album" + listItem.id


    }

}