package com.example.ravi.mvvmsample.helpers

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ravi.mvvmsample.R
import com.example.ravi.mvvmsample.api.Response.BaseResponse
import com.example.ravi.mvvmsample.api.Response.ResponseStatus
import com.example.ravi.mvvmsample.ui.adapters.Kadapter


fun ImageView.LoadCircleImage(
    img: ImageView,
    filePath: String,
    placeHolder: Int = R.drawable.failed
) {
    val reqOptions = RequestOptions()
    reqOptions.placeholder(placeHolder)
    reqOptions.circleCrop()
    Glide.with(img)
        .load(filePath)
        .apply(reqOptions)
        .into(img)

}

fun <ITEM> RecyclerView.setUp(
    items: MutableList<ITEM>,
    layoutResId: Int,
    bindHolder: View.(ITEM) -> Unit,
    itemClick: ITEM.() -> Unit = {},
    manager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
): Kadapter<ITEM> {
    return Kadapter(items, layoutResId,
        {
            bindHolder(it)
        },
        {
            itemClick()
        })
        .apply {
            layoutManager = manager
            adapter = this
        }
}

fun Activity.showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

