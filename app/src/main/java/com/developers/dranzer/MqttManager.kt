package com.developers.dranzer

import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

interface MqttEventsListener {
    fun onConnectionLost(throwable: Throwable)
    fun onConnectionSuccess()
    fun onConnectionFailure(exception: Throwable)
    fun onSendMessageFailure(exception: Throwable)
}

interface MqttManager {
    fun init()
    fun connect(username: String, password: String)
    fun sendMessage(message: Any, topic: String)
}

class MqttManagerImpl(
    private val context: Context,
    private val mqttEventListener: MqttEventsListener
) : MqttManager {

    private val mqttAndroidClient by lazy {
        MqttAndroidClient(context, SERVER_URI, DRANZER_CLIENT_ID)
    }

    override fun init() {
        mqttAndroidClient.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {}

            override fun connectionLost(cause: Throwable) {
                mqttEventListener.onConnectionLost(cause)
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
        })
    }

    override fun connect(username: String, password: String) {
        val mqttConnectOptions = createMqttConnectOptions()
        mqttAndroidClient.connect(mqttConnectOptions, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                val disconnectedBufferOptions = DisconnectedBufferOptions().apply {
                    isBufferEnabled = true
                    bufferSize = 100
                    isPersistBuffer = false
                    isDeleteOldestMessages = false
                }
                mqttAndroidClient.setBufferOpts(disconnectedBufferOptions)
                mqttEventListener.onConnectionSuccess()
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable) {
                mqttEventListener.onConnectionFailure(exception)
            }
        })
    }

    override fun sendMessage(message: Any, topic: String) {
        try {
            val messageByteArray = getMessageBytes(message)
            val mqttMessage = MqttMessage().apply {
                payload = messageByteArray
            }
            mqttAndroidClient.publish(topic, mqttMessage)
        } catch (exception: Exception) {
            mqttEventListener.onSendMessageFailure(exception)
        }
    }

    private fun getMessageBytes(message: Any): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(message)
        objectOutputStream.flush()
        return byteArrayOutputStream.toByteArray()
    }

    private fun createMqttConnectOptions(): MqttConnectOptions {
        return MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = false
        }
    }

    companion object {
        private const val SERVER_URI = "SERVER_URI"
        private const val DRANZER_CLIENT_ID = "DRANZER_ANDROID_CLIENT"
    }

}