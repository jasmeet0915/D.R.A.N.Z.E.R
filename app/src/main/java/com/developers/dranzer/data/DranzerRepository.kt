package com.developers.dranzer.data

import com.developers.dranzer.MqttEventsListener
import com.developers.dranzer.MqttManager
import com.developers.dranzer.data.DranzerRepository.StateEvent.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DranzerRepository(private val mqttManager: MqttManager) : MqttEventsListener {

    fun setDeviceState(device: Devices, state: DeviceState): Observable<StateEvent> {
        val connectMqttEvent = getMqttConnectionObservable()
        val setDeviceStateEvent = setDeviceStateObservable(state, device)

        return Observable.concat(connectMqttEvent, setDeviceStateEvent)
            .onErrorReturn { StateSetFailure(MqttConnectionException) }
    }

    private fun setDeviceStateObservable(
        state: DeviceState,
        device: Devices
    ): Observable<StateEvent> {
        return Single.fromCallable { mqttManager.sendMessage(state, device.getTopic()) }
            .map<StateEvent> { StateSetComplete }
            .onErrorReturn { StateSetFailure(it) }.toObservable()
    }

    private fun getMqttConnectionObservable(): Observable<StateEvent> {
        return Single.just(mqttManager.isConnected())
            .flatMap {
                return@flatMap when {
                    it -> {
                        Single.just(StateSetConnect)
                    }
                    else -> {
                        mqttManager.init()
                        mqttManager.connect(USERNAME, PASSWORD)
                            .map<StateEvent> { StateSetConnect }
                    }
                }
            }.toObservable()
    }

    companion object {
        internal const val USERNAME = ""
        internal const val PASSWORD = ""
    }

    override fun onConnectionLost(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    sealed class StateEvent {
        object StateSetConnect : StateEvent()
        object StateSetComplete : StateEvent()
        data class StateSetFailure(val exception: Throwable) : StateEvent()
    }

    object MqttConnectionException : Throwable()
}