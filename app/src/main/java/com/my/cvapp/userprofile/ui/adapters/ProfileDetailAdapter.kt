package com.my.cvapp.userprofile.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.my.cvapp.R
import com.my.cvapp.di.gone
import com.my.cvapp.di.visible
import com.my.cvapp.userprofile.model.ProfileDetail
import kotlinx.android.synthetic.main.adapter_profiledetail.view.*




class ProfileDetailAdapter(var context : Context, private val profilelist: List<ProfileDetail>?) :
    RecyclerView.Adapter<ProfileDetailAdapter.ViewHolder>() {

    var myImageList = intArrayOf(R.drawable.im_logo, R.drawable.cbse_logo, R.drawable.cbse_logo, R.drawable.mit_logo, R.drawable.ust_logo)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_profiledetail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.heading_textview.text = profilelist?.get(position)?.heading ?: "---"
        holder.subheading_textview.text = profilelist?.get(position)?.sub_heading ?: ""
        holder.date_textview.text = profilelist?.get(position)?.date ?: ""

        setProfileType(holder, position)
    }

    private fun setProfileType(holder: ViewHolder, position: Int) {
        if (position > 0 && !profilelist?.get(position)?.type.equals(profilelist?.get(position - 1)?.type)) {
            holder.profiletype_textview.visible()
            holder.profiletype_textview.text = profilelist?.get(position)?.type ?: "---"
        } else if (position == 0) {
            holder.profiletype_textview.visible()
            holder.profiletype_textview.text = profilelist?.get(position)?.type ?: "---"
        } else {
            holder.profiletype_textview.gone()

        }

        Glide.with(context)
            .load(myImageList[position])
            .apply(RequestOptions.circleCropTransform())
            .into(holder.logo_imageview)
    }

    override fun getItemCount(): Int = profilelist?.size ?: 0


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val heading_textview = view.heading_textview
        val subheading_textview = view.subheading_textview
        val profiletype_textview = view.profiletype_textview
        val date_textview = view.date_textview
       val logo_imageview = view.logo_imageview
    }

}