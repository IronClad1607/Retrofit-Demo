package com.ironclad.retrofitdemo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ironclad.retrofitdemo.R
import com.ironclad.retrofitdemo.activities.HomeTabbedActivity
import com.ironclad.retrofitdemo.modelClass.User
import kotlinx.android.synthetic.main.cv_users.view.*
import java.util.*

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView) {
                tvName.text = user.name
                tvUserName.text = user.username
                tvEmail.text = user.email
                tvPhoneNumber.text = user.phone
                tvWebsite.text = user.website
                tvStreet.text = user.address.street
                tvSuite.text = user.address.suite
                tvCity.text = user.address.city
                tvZipCode.text = user.address.zipcode
                tvLat.text = user.address.geo.lat
                tvLong.text = user.address.geo.lng
                tvCompanyName.text = user.company.name
                tvCP.text = user.company.catchPhrase
                tvBS.text = user.company.bs

                val colors = resources.getIntArray(R.array.cardColors)
                val randomColor = colors[Random().nextInt(colors.size)]
                viewColor.setBackgroundColor(randomColor)

                setOnClickListener {
                    val intent = Intent(context, HomeTabbedActivity::class.java)
                    startActivity(context, intent, null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            from(parent.context).inflate(R.layout.cv_users, parent, false)
        )
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }
}