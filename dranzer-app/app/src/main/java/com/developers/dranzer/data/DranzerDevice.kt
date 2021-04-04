package com.developers.dranzer.data

data class DranzerDevice(
        val deviceId: Long,
        val device: DeviceInterface,
        val state: DeviceState
)
