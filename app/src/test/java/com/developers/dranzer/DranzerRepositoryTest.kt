package com.developers.dranzer

import com.developers.dranzer.MqttManagerImpl.*
import com.developers.dranzer.data.DeviceState
import com.developers.dranzer.data.Devices
import com.developers.dranzer.data.DranzerRepository
import com.developers.dranzer.data.DranzerRepository.Companion.PASSWORD
import com.developers.dranzer.data.DranzerRepository.Companion.USERNAME
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class DranzerRepositoryTest {

    private val mqttManager = mock<MqttManager>()
    private val dranzerRepository = DranzerRepository(mqttManager)

    @Test
    fun `when device state are being set, manager is initialized and connection is established if not done`() {
        // given
        val device = Devices.ALEXA
        val state = DeviceState.ON
        val onConnectAction: (status: ConnectionStatus) -> Unit = {}
        whenever(mqttManager.isConnected()).thenReturn(false)
        whenever(mqttManager.connect(USERNAME, PASSWORD, onConnectAction)).then {
            dranzerRepository.onConnectionSuccess(onConnectAction)
        }

        // when
        dranzerRepository.setState(device, state)

        // then
        verify(mqttManager).isConnected()
        verify(mqttManager).init()
        verify(mqttManager).connect(eq(USERNAME), eq(PASSWORD), any())
        verify(mqttManager).sendMessage(state, device.getTopic())
    }
}