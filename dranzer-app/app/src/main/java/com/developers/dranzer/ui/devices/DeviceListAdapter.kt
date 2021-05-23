package com.developers.dranzer.ui.devices

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developers.dranzer.R
import com.developers.dranzer.data.DranzerDevice
import com.developers.dranzer.ui.devices.DeviceListAdapter.DeviceListViewHolder
import kotlinx.android.synthetic.main.row_device.view.*

class DeviceListAdapter(private val onToggleChange: (isChecked: Boolean) -> Unit):
    ListAdapter<DranzerDevice, DeviceListViewHolder>(DeviceListDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_device, parent, false)
        (view.deviceSwitch as SwitchCompat).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceState(view.deviceState, isChecked)
            onToggleChange(isChecked)
        }
        return DeviceListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DeviceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dranzerDevice: DranzerDevice) {
            val imageId = dranzerDevice.device.getImage()
            itemView.deviceImage.setImageResource(imageId)
            itemView.deviceTitle.text = dranzerDevice.device.getDeviceName()
        }
    }

    private fun toggleDeviceState(deviceStateView: ImageView, isChecked: Boolean) {
        if (isChecked) {
            val deviceStateOn = ContextCompat.getDrawable(
                deviceStateView.context,
                R.drawable.device_state_drawable_on
            )
            deviceStateView.deviceState.setImageDrawable(deviceStateOn)
        } else {
            val deviceStateOn = ContextCompat.getDrawable(
                deviceStateView.context,
                R.drawable.device_state_drawable_off
            )
            deviceStateView.deviceState.setImageDrawable(deviceStateOn)
        }
    }
}