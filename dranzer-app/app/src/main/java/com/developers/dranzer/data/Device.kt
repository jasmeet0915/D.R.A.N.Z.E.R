package com.developers.dranzer.data

import com.developers.dranzer.R

interface DeviceInterface {
    fun getTopic(): String
    fun getImage(): Int
    fun getDeviceName(): String
}

enum class Device : DeviceInterface {
    ALEXA {
        override fun getTopic(): String {
            return "workroom-alexa"
        }

        override fun getImage(): Int {
            return R.drawable.baseline_mic_black_48dp
        }

        override fun getDeviceName(): String {
            return "Alexa"
        }
    },
    ROOM_LIGHT {
        override fun getTopic(): String {
            return "workroom-light"
        }

        override fun getImage(): Int {
            return R.drawable.baseline_highlight_yellow_600_48dp
        }

        override fun getDeviceName(): String {
            return "Light"
        }
    },
    ALL {
        override fun getTopic(): String {
            return "all-devices"
        }

        override fun getImage(): Int {
            TODO("Not yet implemented")
        }

        override fun getDeviceName(): String {
            return "all"
        }
    };

    enum class Extensions : DeviceInterface {
        EXTENSION_ONE {
            override fun getTopic(): String {
                return "extension-one"
            }

            override fun getImage(): Int {
                TODO("Not yet implemented")
            }

            override fun getDeviceName(): String {
                return "Extension I"
            }
        },
        EXTENSION_TWO {
            override fun getTopic(): String {
                return "extension-two"
            }

            override fun getImage(): Int {
                TODO("Not yet implemented")
            }

            override fun getDeviceName(): String {
                return "Extension II"
            }
        },
    }

    enum class Monitors : DeviceInterface {
        ONE {
            override fun getTopic(): String {
                return "monitor-one"
            }

            override fun getImage(): Int {
                return R.drawable.baseline_tv_white_48dp
            }

            override fun getDeviceName(): String {
                return "Monitor I"
            }
        },
        TWO {
            override fun getTopic(): String {
                return "monitor-two"
            }

            override fun getImage(): Int {
                return R.drawable.baseline_tv_white_48dp
            }

            override fun getDeviceName(): String {
                return "Monitor II"
            }
        };
    }
}

enum class DeviceState {
    ON,
    OFF
}