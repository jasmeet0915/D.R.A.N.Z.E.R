package com.developers.dranzer.ui.devices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.developers.dranzer.R
import com.developers.dranzer.data.Device
import com.developers.dranzer.data.DeviceState
import com.developers.dranzer.data.DranzerDevice
import kotlinx.android.synthetic.main.fragment_device_state.*

class DeviceStateFragment : Fragment(), DeviceStateView {

    private val deviceListAdapter = DeviceListAdapter()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_state, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deviceList = listOf(
            DranzerDevice(1, Device.ALEXA, DeviceState.OFF),
            DranzerDevice(2, Device.ROOM_LIGHT, DeviceState.OFF),
            DranzerDevice(3, Device.Monitors.ONE, DeviceState.OFF)
        )
        deviceListAdapter.submitList(deviceList)
        deviceRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        deviceRecyclerView.adapter = deviceListAdapter
    }

    override fun setDeviceState(state: DeviceState) {
        TODO("Not yet implemented")
    }

}