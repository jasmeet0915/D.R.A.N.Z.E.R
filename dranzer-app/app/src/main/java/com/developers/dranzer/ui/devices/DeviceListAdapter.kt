package com.developers.dranzer.ui.devices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developers.dranzer.R
import com.developers.dranzer.data.DranzerDevice
import com.developers.dranzer.ui.devices.DeviceListAdapter.DeviceListViewHolder
import kotlinx.android.synthetic.main.row_device.view.*

class DeviceListAdapter : ListAdapter<DranzerDevice, DeviceListViewHolder>(DeviceListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_device, parent, false)
        return DeviceListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DeviceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dranzerDevice: DranzerDevice) {
            val imageId = dranzerDevice.device.getImage()
            itemView.deviceImage.setImageResource(imageId)
        }
    }

}