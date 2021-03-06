package com.ironclad.retrofitdemo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.modelClass.Post
import kotlinx.android.synthetic.main.cv_post.view.*
import java.util.*

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var langCodeTitle: String = ""
        private var langCodeBody: String = ""

        private val languageIdentifier = LanguageIdentification.getClient()

        fun bind(post: Post) {

            languageIdentifier.identifyLanguage(post.title)
                .addOnSuccessListener { languageCode ->
                    langCodeTitle = languageCode!!
                }
            languageIdentifier.identifyLanguage(post.body)
                .addOnSuccessListener { languageCode ->
                    langCodeBody = languageCode!!
                }

            Log.d("PUI", "${post.id} $langCodeBody $langCodeTitle")

            with(itemView) {
                tvTitle.text = post.title
                tvBody.text = post.body

                val colors = resources.getIntArray(R.array.cardColors)
                val randomColor = colors[Random().nextInt(colors.size)]
                cardPost.setBackgroundColor(randomColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PostAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cv_post, parent, false)
        )
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}