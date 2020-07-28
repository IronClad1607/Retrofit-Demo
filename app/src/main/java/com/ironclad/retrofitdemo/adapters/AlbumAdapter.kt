package com.ironclad.retrofitdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.modelClass.Album
import kotlinx.android.synthetic.main.cv_albums.view.*
import kotlinx.android.synthetic.main.cv_albums.view.tvTitle
import kotlinx.android.synthetic.main.cv_post.view.*
import java.util.*

class AlbumAdapter(private val albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mRandom = Random()
        fun bind(album: Album) {
            with(itemView) {
                tvTitle.text = album.title
                val colors = resources.getIntArray(R.array.cardColors)
                val randomColor = colors[Random().nextInt(colors.size)]
                cardAlbum.setBackgroundColor(randomColor)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_albums, parent, false)
        )
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }
}