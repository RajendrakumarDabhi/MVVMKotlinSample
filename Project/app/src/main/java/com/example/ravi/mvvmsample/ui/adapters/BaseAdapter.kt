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

abstract class AbstractAdapter<ITEM> constructor(
    protected var itemList: MutableList<ITEM>,
    private val layoutResId: Int)
    : RecyclerView.Adapter<AbstractAdapter.Holder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =  LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        val viewHolder = Holder(view)
        val itemView = viewHolder.itemView
        /*itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }*/
        return viewHolder
    }
    protected open fun onItemClick(itemView: View, position: Int) {
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = itemList[position]
        holder.itemView.bind(item)
    }

    protected open fun View.bind(item: ITEM) {

    }

    fun setData(listData:List<ITEM>)
    {
        itemList.clear()
        itemList.addAll(listData)
        notifyDataSetChanged()
    }
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)


}


class Kadapter<ITEM>(items: MutableList<ITEM>,
                     layoutResId: Int,
                     private val bindHolder: View.(ITEM) -> Unit)
    : AbstractAdapter<ITEM>(items, layoutResId) {

    private var itemClick: ITEM.() -> Unit = {}

    constructor(items: MutableList<ITEM>,
                layoutResId: Int,
                bindHolder: View.(ITEM) -> Unit,
                itemClick: ITEM.() -> Unit = {}) : this(items, layoutResId, bindHolder) {
        this.itemClick = itemClick
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bindHolder(itemList[position])
    }

    override fun onItemClick(itemView: View, position: Int) {
        itemList[position].itemClick()
    }


}

