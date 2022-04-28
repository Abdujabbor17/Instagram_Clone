package com.masterandroid.instagram_clone.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.masterandroid.instagram_clone.R
import com.masterandroid.instagram_clone.fragment.SearchFragment
import com.masterandroid.instagram_clone.model.User

class SearchAdapter(var fragment:SearchFragment,var items:ArrayList<User>):BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_search,parent,false)
        return UserViewHolder(view)
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_profile:ShapeableImageView = view.findViewById(R.id.iv_profile)
        var tv_fullname:TextView = view.findViewById(R.id.tv_fullname)
        var tv_email:TextView = view.findViewById(R.id.tv_email)
        var tv_follow:TextView = view.findViewById(R.id.tv_follow)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user: User = items[position]
        if (holder is UserViewHolder) {
            holder.tv_fullname.text = user.fullname
            holder.tv_email.text = user.email

            Glide.with(fragment).load(user.userImg)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(holder.iv_profile)

            val tv_follow = holder.tv_follow
            tv_follow.setOnClickListener {
                if(!user.isFollowed){
                    tv_follow.text = fragment.getString(R.string.str_following)
                    tv_follow.setTextColor(Color.BLACK)
                    tv_follow.setBackgroundResource(R.drawable.textview_rounded_corners)
                }else{
                    tv_follow.text = fragment.getString(R.string.str_follow)
                    tv_follow.setTextColor(Color.WHITE)
                    tv_follow.setBackgroundResource(R.drawable.textview_rounded_corners_blue)
                }
                fragment.followOrUnfollow(user)
            }

            if(!user.isFollowed){
                tv_follow.text = fragment.getString(R.string.str_follow)
                tv_follow.setTextColor(Color.WHITE)
                tv_follow.setBackgroundResource(R.drawable.textview_rounded_corners_blue)
            }else{
                tv_follow.text = fragment.getString(R.string.str_following)
                tv_follow.setTextColor(Color.BLACK)
                tv_follow.setBackgroundResource(R.drawable.textview_rounded_corners)
            }
            if(!user.isFollowed){
                tv_follow.text = fragment.getString(R.string.str_follow)
            }else{
                tv_follow.text = fragment.getString(R.string.str_following)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}