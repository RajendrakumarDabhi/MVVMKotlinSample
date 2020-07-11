package com.example.ravi.mvvmsample.ui.views

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ravi.mvvmsample.R
import com.example.ravi.mvvmsample.api.Response.ListUsersResponse
import com.example.ravi.mvvmsample.api.Response.ResponseStatus
import com.example.ravi.mvvmsample.helpers.LoadCircleImage
import com.example.ravi.mvvmsample.helpers.setUp
import com.example.ravi.mvvmsample.helpers.showToast
import com.example.ravi.mvvmsample.models.User
import com.example.ravi.mvvmsample.ui.adapters.Kadapter
import com.example.ravi.mvvmsample.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var mViewModel: MainViewModel
    val mUserList: MutableList<User> = ArrayList()
    lateinit var mAdapter: Kadapter<User>
    val titleMenuItem = "Load via Room Db"
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
        mViewModel.getListUsersLiveData().observe(this, Observer {
            if (it.status == ResponseStatus.STATUS_LOADING) {

                if (it.data == true) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }

            } else if (it.status == ResponseStatus.STATUS_ERROR) {
                progressBar.visibility = View.GONE
                rv_users.setBackgroundResource(R.drawable.error)
            } else {
                progressBar.visibility = View.GONE
                val users = it.data as ListUsersResponse
                users.data?.let { it1 -> mAdapter.setData(it1) }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menu?.add(titleMenuItem)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title.equals(titleMenuItem)) {
            mUserList.clear()
            mAdapter.notifyDataSetChanged()
            mViewModel.getandSaveListUsers()
            mViewModel.getListUsers().observe(this, Observer {
                mUserList.clear()
                mUserList.addAll(it)
                rv_users.setBackgroundColor(Color.WHITE)
                mAdapter.notifyDataSetChanged()
            })
        }
        return super.onOptionsItemSelected(item)
    }


}
