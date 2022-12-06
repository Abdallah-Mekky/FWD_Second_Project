package com.task1.asteroidsappfwd.ui.mainAsteroidsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.task1.asteroidsappfwd.R
import com.task1.asteroidsappfwd.databinding.ItemAsteroidBinding
import com.task1.asteroidsappfwd.ui.models.Asteroid

class AsteroidesAdapter(var items: List<Asteroid>? = null) :
    Adapter<AsteroidesAdapter.ItemAsteroidViewHolder>() {

    class ItemAsteroidViewHolder(val itemAsteroidBinding: ItemAsteroidBinding) :
        ViewHolder(itemAsteroidBinding.root) {

        fun bind(item: Asteroid) {

            itemAsteroidBinding.item = item
            itemAsteroidBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAsteroidViewHolder {

        val itemAsteroidBinding: ItemAsteroidBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_asteroid, parent,
            false,
        )
        return ItemAsteroidViewHolder(itemAsteroidBinding)
    }

    override fun onBindViewHolder(holder: ItemAsteroidViewHolder, position: Int) {

        val item = items?.get(position)

        if (onItemClickListener != null) {

            holder.itemView.setOnClickListener {

                onItemClickListener!!.onClick(position, item!!)
            }
        }

        holder.bind(item!!)
    }

    override fun getItemCount(): Int {

        return items?.size ?: 0
    }

    fun refreashAdapter(items: List<Asteroid>) {

        this.items = items
        notifyDataSetChanged()
    }


    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onClick(position: Int, item: Asteroid)
    }


}