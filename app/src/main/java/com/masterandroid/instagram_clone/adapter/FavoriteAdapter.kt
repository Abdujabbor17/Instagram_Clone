package com.masterandroid.instagram_clone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.masterandroid.instagram_clone.R
import com.masterandroid.instagram_clone.fragment.FavoriteFragment
import com.masterandroid.instagram_clone.managers.AuthManager
import com.masterandroid.instagram_clone.model.Post

class FavoriteAdapter(var fragment: FavoriteFragment, var items:ArrayList<Post>):BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post_favorite,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = items[position]
        if (holder is FavoriteViewHolder){
            val tv_fullname = holder.tv_fullname
            val tv_caption = holder.tv_caption
            val iv_post = holder.iv_post
            val iv_profile = holder.iv_profile
            val tv_time = holder.tv_time
            val iv_like = holder.iv_like
            val iv_more = holder.iv_more

            tv_fullname.text = post.fullname
            if (post.caption == ""){
                tv_caption.visibility = View.GONE
            }else{
                tv_caption.text = post.caption

            }
            tv_time.text = post.currentDate
            Glide.with(fragment).load(post.postImg).into(iv_post)
            Glide.with(fragment).load(post.userImg).placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person).into(iv_profile)

            iv_like.setOnClickListener {
                if (post.isLiked){
                    post.isLiked = false
                    iv_like.setImageResource(R.drawable.ic_favorite)
                }else{
                    post.isLiked = true
                    iv_like.setImageResource(R.drawable.ic_like)
                }
                fragment.likeOrUnlikePost(post)
            }

            if (post.isLiked){
                iv_like.setImageResource(R.drawable.ic_like)
            }else{
                iv_like.setImageResource(R.drawable.ic_favorite)
            }

            val uid = AuthManager.currentUser()!!.uid
            if (uid == post.uid){
                iv_more.visibility = View.VISIBLE
            }else{
                iv_more.visibility = View.GONE
            }
            iv_more.setOnClickListener {
                  fragment.showDeleteDialog(post)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var iv_profile:ShapeableImageView = view.findViewById(R.id.iv_profile)
        var iv_post:ShapeableImageView = view.findViewById(R.id.iv_post)
        var tv_fullname:TextView = view.findViewById(R.id.tv_fullname)
        var tv_time:TextView = view.findViewById(R.id.tv_time)
        var tv_caption:TextView = view.findViewById(R.id.tv_caption)
        var iv_more:ImageView = view.findViewById(R.id.iv_more)
        var iv_like:ImageView = view.findViewById(R.id.iv_like)
        var iv_share:ImageView = view.findViewById(R.id.iv_share)

    }

}

