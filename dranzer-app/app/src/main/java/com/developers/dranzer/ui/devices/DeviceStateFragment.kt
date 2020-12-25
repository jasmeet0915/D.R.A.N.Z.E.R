package com.developers.dranzer.ui.devices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.dranzer.R
import com.developers.dranzer.data.DeviceState

class DeviceStateFragment : Fragment(), DeviceStateView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_state, container, false)
    }

    override fun setDeviceState(state: DeviceState) {
        TODO("Not yet implemented")
    }

}