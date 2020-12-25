package com.developers.dranzer.data

interface DeviceInterface {
    fun getTopic(): String
}

enum class Device : DeviceInterface {
    ALEXA {
        override fun getTopic(): String {
            return "workroom-alexa"
        }
    },
    ROOM_LIGHT {
        override fun getTopic(): String {
            return "workroom-light"
        }
    },
    ALL {
        override fun getTopic(): String {
            return "all-devices"
        }
    };

    enum class Extensions : DeviceInterface {
        EXTENSION_ONE {
            override fun getTopic(): String {
                return "extension-one"
            }
        },
        EXTENSION_TWO {
            override fun getTopic(): String {
                return "extension-two"
            }
        },
    }

    enum class Monitors : DeviceInterface {
        ONE {
            override fun getTopic(): String {
                return "monitor-one"
            }
        },
        TWO {
            override fun getTopic(): String {
                return "monitor-two"
            }
        };
    }
}

enum class DeviceState {
    ON,
    OFF
}