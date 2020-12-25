package com.developers.dranzer.data

import com.developers.dranzer.MqttEventsListener
import com.developers.dranzer.MqttManager
import com.developers.dranzer.data.DranzerRepository.MqttEvents.ConnectionLost
import com.developers.dranzer.data.DranzerRepository.StateEvent.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DranzerRepository(private val mqttManager: MqttManager) : MqttEventsListener {

    private val mqttEventSubject = BehaviorSubject.create<MqttEvents>()

    fun setDeviceState(device: Device, state: DeviceState): Observable<StateEvent> {
        val connectMqttEvent = getMqttConnectionObservable()
        val setDeviceStateEvent = setDeviceStateObservable(state, device)

        return Observable.concat(connectMqttEvent, setDeviceStateEvent)
            .onErrorReturn { StateSetFailure(MqttConnectionException) }
    }

    private fun setDeviceStateObservable(
        state: DeviceState,
        device: Device
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
        mqttEventSubject.onNext(ConnectionLost)
    }

    fun getMqttEvents(): BehaviorSubject<MqttEvents> {
        return mqttEventSubject
    }

    sealed class StateEvent {
        object StateSetConnect : StateEvent()
        object StateSetComplete : StateEvent()
        data class StateSetFailure(val exception: Throwable) : StateEvent()
    }

    sealed class MqttEvents {
        object ConnectionLost: MqttEvents()
    }

    object MqttConnectionException : Throwable()
}