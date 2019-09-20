package com.vipin.nasaapod.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vipin.nasaapod.R
import com.vipin.nasaapod.model.Apod
import com.vipin.nasaapod.util.loadImage
import kotlinx.android.synthetic.main.grid_item_view_layout.view.*

/**
 * Created by vipin.c on 17/09/2019
 */
class GridViewAdapter(val apodLists: MutableList<Apod> = mutableListOf()): RecyclerView.Adapter<GridViewAdapter.ViewHolder>() {


    fun apodList(apodList: List<Apod>){
        apodLists.addAll(apodList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item_view_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return apodLists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(apodLists[position])
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(apod: Apod){
            apod.url?.let { itemView.image_view.loadImage(it) }
        }

    }
}