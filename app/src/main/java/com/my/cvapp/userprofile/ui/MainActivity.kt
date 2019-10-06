package com.my.cvapp.userprofile.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.cvapp.R
import com.my.cvapp.di.injectViewModel
import com.my.cvapp.di.observe
import com.my.cvapp.userprofile.model.ProfileDetail
import com.my.cvapp.userprofile.model.UserInfo
import com.my.cvapp.userprofile.ui.adapters.ProfileDetailAdapter
import com.my.cvapp.userprofile.ui.data.MainViewModel
import kotlinx.android.synthetic.main.section_experience.*
import kotlinx.android.synthetic.main.section_header.*

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = injectViewModel(this@MainActivity)
        observe(viewModel.userInfo, ::updateUI)
        observe(viewModel.profilelist, ::updateProfileData)
    }


    private fun updateUI(userInfo: UserInfo?) {

        userInfo?.let {
            tvName.text = it.Name
            tvSummary.text = it.Description
            tvTechnology.text = it.Technology
            tvPost.text = it.post
            tvAddress.text = it.address
            viewModel.makeProfileList()
        }

    }

    private fun updateProfileData(list: List<ProfileDetail>?) {

        my_recycler_view.apply {
           layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ProfileDetailAdapter(this@MainActivity,list)
        }
    }




}
































