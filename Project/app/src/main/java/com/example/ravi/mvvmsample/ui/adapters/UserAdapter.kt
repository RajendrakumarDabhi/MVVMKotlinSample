package com.example.ravi.mvvmsample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ravi.mvvmsample.R
import com.example.ravi.mvvmsample.helpers.LoadCircleImage
import com.example.ravi.mvvmsample.models.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(adapterClick: (pos: Int, data: User, view: View) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {

    val mList: MutableList<User> = mutableListOf()
    val mClickCallBack: (pos: Int, data: User, view: View) -> Unit = adapterClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserHolder(view,adapterClick = mClickCallBack)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bindData(mList.get(position),position)
    }

    fun setData(users: List<User>) {
        mList.clear()
        mList.addAll(users)
        notifyDataSetChanged()
    }


    class UserHolder(itemView: View,adapterClick: (pos: Int, data: User, view: View) -> Unit) : RecyclerView.ViewHolder(itemView) {

        var mPosition:Int = 0
        val mAdapterClick=adapterClick

        fun bindData(user: User,pos:Int) {
            mPosition=pos
            itemView.imageView.LoadCircleImage(itemView.imageView, user.avatar, R.drawable.failed)
            itemView.setOnClickListener {
                mAdapterClick(mPosition,user,it)
            }
            itemView.txt_name.text=user.first_name+" "+user.last_name
            itemView.txt_email.text=user.email
        }


    }
}