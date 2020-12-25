package com.developers.dranzer.ui.devices

import com.developers.dranzer.data.DeviceState
import com.developers.dranzer.data.Device
import com.developers.dranzer.data.DranzerRepository.StateEvent.*
import com.developers.dranzer.domain.SetDeviceStateUseCase

class DeviceStatePresenter(
    private val deviceStateView: DeviceStateView,
    private val setDeviceStateUseCase: SetDeviceStateUseCase
) {

    fun setDeviceState(device: Device, deviceState: DeviceState) {
        setDeviceStateUseCase.invoke(device = device, state = deviceState)
            .subscribe {
                when (it) {
                    StateSetConnect -> TODO()
                    StateSetComplete -> deviceStateView.setDeviceState(deviceState)
                    is StateSetFailure -> TODO()
                }
            }
    }
}