package com.developers.dranzer.data

import com.developers.dranzer.MqttEventsListener
import com.developers.dranzer.MqttManager
import com.developers.dranzer.MqttManagerImpl.ConnectionStatus

class DranzerRepository(private val mqttManager: MqttManager) : MqttEventsListener {

    fun setState(device: Devices, state: DeviceState) {
        val isConnected = mqttManager.isConnected()
        if (!isConnected) {
            mqttManager.init()
            mqttManager.connect(USERNAME, PASSWORD) {
                when (it) {
                    ConnectionStatus.CONNECTED -> {
                        setState(device, state)
                    }
                    ConnectionStatus.DISCONNECTED -> TODO()
                }
            }
        }
        mqttManager.sendMessage(state, device.getTopic())
    }

    companion object {
        internal const val USERNAME = ""
        internal const val PASSWORD = ""
    }

    override fun onConnectionLost(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuccess(onConnectAction: (status: ConnectionStatus) -> Unit) {
        onConnectAction.invoke(ConnectionStatus.CONNECTED)
    }

    override fun onConnectionFailure(exception: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onSendMessageFailure(exception: Throwable) {
        TODO("Not yet implemented")
    }
}