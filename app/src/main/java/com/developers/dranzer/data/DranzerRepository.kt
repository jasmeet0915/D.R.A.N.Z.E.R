package com.developers.dranzer.data

import com.developers.dranzer.MqttEventsListener
import com.developers.dranzer.MqttManager
import com.developers.dranzer.MqttManagerImpl.ConnectionStatus
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class DranzerRepository(private val mqttManager: MqttManager) : MqttEventsListener {

    fun setState(device: Devices, state: DeviceState): Single<StateEvent> {
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
        return Completable.fromCallable { mqttManager.sendMessage(state, device.getTopic()) }
            .toSingle { Unit }
            .map<StateEvent> { StateEvent.StateSetComplete }
            .onErrorReturn { StateEvent.StateSetFailure(it) }
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

    sealed class StateEvent {
        object StateSetComplete : StateEvent()
        data class StateSetFailure(val exception: Throwable) : StateEvent()
    }
}