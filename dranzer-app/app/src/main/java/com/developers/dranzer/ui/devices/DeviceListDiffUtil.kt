package com.developers.dranzer.ui.devices

import androidx.recyclerview.widget.DiffUtil
import com.developers.dranzer.data.Device
import com.developers.dranzer.data.DranzerDevice

class DeviceListDiffUtil: DiffUtil.ItemCallback<DranzerDevice>() {

    override fun areItemsTheSame(oldItem: DranzerDevice, newItem: DranzerDevice): Boolean {
        return oldItem.deviceId == newItem.deviceId
    }

    override fun areContentsTheSame(oldItem: DranzerDevice, newItem: DranzerDevice): Boolean {
        oldItem.device as Device
        newItem.device as Device
        return oldItem.device.name == newItem.device.name
    }
}