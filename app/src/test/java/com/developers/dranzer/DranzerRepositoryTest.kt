package com.developers.dranzer

import com.developers.dranzer.data.DeviceState
import com.developers.dranzer.data.Devices
import com.developers.dranzer.data.DranzerRepository
import com.developers.dranzer.data.DranzerRepository.Companion.PASSWORD
import com.developers.dranzer.data.DranzerRepository.Companion.USERNAME
import com.developers.dranzer.data.DranzerRepository.StateEvent.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.eclipse.paho.client.mqttv3.MqttException
import org.junit.Test

class DranzerRepositoryTest {

    private val mqttManager = mock<MqttManager>()
    private val dranzerRepository = DranzerRepository(mqttManager)

    @Test
    fun `when device state is set, manager is initialized and connection is established if not done`() {
        // given
        val device = Devices.ALEXA
        val state = DeviceState.ON
        whenever(mqttManager.isConnected()).thenReturn(false)
        whenever(mqttManager.connect(USERNAME, PASSWORD)).thenReturn(Single.just(true))

        // when
        val stateObserver = dranzerRepository.setDeviceState(device, state)
            .test()

        // then
        stateObserver.assertValues(StateSetConnect, StateSetComplete)
        verify(mqttManager).isConnected()
        verify(mqttManager).init()
        verify(mqttManager).connect(USERNAME, PASSWORD)
        verify(mqttManager).sendMessage(state, device.getTopic())
    }

    @Test
    fun `when manager is already connected never call init and connect and send message directly`() {
        // given
        val device = Devices.ALEXA
        val deviceState = DeviceState.ON
        whenever(mqttManager.isConnected()).thenReturn(true)

        // when
        val stateObserver = dranzerRepository.setDeviceState(device, deviceState)
            .test()

        // then
        stateObserver.assertValues(StateSetConnect, StateSetComplete)
        verify(mqttManager).isConnected()
        verify(mqttManager, never()).init()
        verify(mqttManager, never()).connect(USERNAME, PASSWORD)
        verify(mqttManager).sendMessage(deviceState, device.getTopic())
    }

    @Test
    fun `when device state is set and message is sent, emit state failure if exception is raised`() {
        // given
        val device = Devices.ALEXA
        val state = DeviceState.ON
        val mqttException = MqttException(Throwable("Connect First !!"))
        whenever(mqttManager.isConnected()).thenReturn(true)
        whenever(mqttManager.sendMessage(state, device.getTopic())).then { throw mqttException }

        // when
        val stateObserver = dranzerRepository.setDeviceState(device, state)
            .test()

        // then
        stateObserver.assertValues(StateSetConnect, StateSetFailure(mqttException))
        verify(mqttManager).isConnected()
        verify(mqttManager, never()).init()
        verify(mqttManager, never()).connect(USERNAME, PASSWORD)
        verify(mqttManager).sendMessage(state, device.getTopic())
    }
}