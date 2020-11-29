package com.developers.dranzer.domain

import com.developers.dranzer.SchedulersProvider
import com.developers.dranzer.data.DeviceState
import com.developers.dranzer.data.Devices
import com.developers.dranzer.data.DranzerRepository
import com.developers.dranzer.data.DranzerRepository.StateEvent
import io.reactivex.rxjava3.core.Observable

class SetDeviceStateUseCase(
    private val dranzerRepository: DranzerRepository,
    private val schedulersProvider: SchedulersProvider
) {

    fun invoke(devices: Devices, state: DeviceState): Observable<StateEvent> {
        return dranzerRepository.setDeviceState(device = devices, state = state)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.ui())
    }
}