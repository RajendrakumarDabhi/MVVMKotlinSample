package com.example.ravi.mvvmsample.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ravi.mvvmsample.R
import com.example.ravi.mvvmsample.helpers.LoadCircleImage
import com.example.ravi.mvvmsample.helpers.setUp
import com.example.ravi.mvvmsample.helpers.showToast
import com.example.ravi.mvvmsample.models.User
import com.example.ravi.mvvmsample.ui.adapters.Kadapter
import com.example.ravi.mvvmsample.ui.adapters.UserAdapter
import com.example.ravi.mvvmsample.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    val mUserList: MutableList<User> = ArrayList()
    lateinit var mAdapter: Kadapter<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.getUsers()
        mAdapter = getAndSetAdapter()
        observeEventViewmodel()
    }

    private fun getAndSetAdapter(): Kadapter<User> {
        return rv_users.setUp(mUserList, R.layout.item_user,
            {
                //function bind view holder

                txt_name.text = it.first_name
                txt_email.text = it.email
                imageView.LoadCircleImage(imageView, it.avatar)
                val item = it
                imageView.setOnClickListener {
                    showToast(this@MainActivity, item.first_name)
                }
                txt_name.setOnClickListener {
                    showToast(this@MainActivity, item.last_name)
                }
            },
            {
                //function onclick

                showToast(applicationContext, this.first_name)
            })
    }

    fun observeEventViewmodel() {
        mViewModel.getListUsers().observe(this, Observer {
              mAdapter.setData(it)
        })

    }


}
