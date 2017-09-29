package com.vm.talk2me.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.vm.talk2me.Constants
import com.vm.talk2me.R
import com.vm.talk2me.model.Message
import kotlinx.android.synthetic.main.mess_in.view.*
import kotlinx.android.synthetic.main.mess_out.view.*

/**
 * Created by VanManh on 01-Sep-17.
 */

class RCAdapter(val messages: MutableList<Message>,val typeface: Typeface) : RecyclerView.Adapter<RCAdapter.MHolder>() {

    override fun getItemViewType(position: Int): Int {
        return messages[position].tag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MHolder {
        var view: View? = null
        when (viewType) {
            Constants.MESS_IN_TAG -> view = LayoutInflater.from(parent.context).inflate(R.layout.mess_in, parent, false)
            Constants.MESS_OUT_TAG -> view = LayoutInflater.from(parent.context).inflate(R.layout.mess_out, parent, false)
        }
        return MHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: MHolder, position: Int) {
        holder.tvMess!!.text = messages[position].mess
        holder.tvMessTime!!.text = messages[position].timeFomated
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class MHolder(itemView: View?, viewType: Int) : ViewHolder(itemView) {

        var tvMess: TextView? = null
        var tvMessTime: TextView? = null

        init {
            when (viewType) {
                Constants.MESS_IN_TAG -> {
                    tvMess = itemView!!.tv_mess_in
                    tvMessTime = itemView!!.tv_mess_time_in
                }
                Constants.MESS_OUT_TAG -> {
                    tvMess = itemView!!.tv_mess_out
                    tvMessTime = itemView!!.tv_mess_time_out
                }
            }

            tvMess!!.typeface = typeface
            tvMessTime!!.typeface = typeface
        }
    }
}
