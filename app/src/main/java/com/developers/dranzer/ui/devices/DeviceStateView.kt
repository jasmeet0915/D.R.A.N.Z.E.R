package com.developers.dranzer.ui.devices

import com.developers.dranzer.data.DeviceState

interface DeviceStateView {
    fun setDeviceState(state: DeviceState)
}