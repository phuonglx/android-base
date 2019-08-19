package com.studiounknown.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.studiounknown.R
import com.studiounknown.model.WeatherModel
import com.studiounknown.ui.base.BaseListAdapter

class WeatherAdapter(
    private val clickListener: (WeatherModel) -> Unit
) : BaseListAdapter<WeatherModel, WeatherAdapter.WeatherViewHolder>(
    callBack = object : DiffUtil.ItemCallback<WeatherModel>() {
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean =
            oldItem.name == newItem.name
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_history, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    inner class WeatherViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weatherModel: WeatherModel, clickListener: (WeatherModel) -> Unit) {
            view.findViewById<TextView>(R.id.tvWeatherName).text = weatherModel.name
            view.setOnClickListener {
                clickListener.invoke(weatherModel)
            }
        }
    }
}
