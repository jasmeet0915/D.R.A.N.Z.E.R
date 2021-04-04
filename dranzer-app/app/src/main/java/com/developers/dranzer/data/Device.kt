package com.developers.dranzer.data

import com.developers.dranzer.R

interface DeviceInterface {
    fun getTopic(): String
    fun getImage(): Int
}

enum class Device : DeviceInterface {
    ALEXA {
        override fun getTopic(): String {
            return "workroom-alexa"
        }

        override fun getImage(): Int {
            return R.drawable.baseline_mic_black_48dp
        }
    },
    ROOM_LIGHT {
        override fun getTopic(): String {
            return "workroom-light"
        }

        override fun getImage(): Int {
            return R.drawable.baseline_highlight_yellow_600_48dp
        }
    },
    ALL {
        override fun getTopic(): String {
            return "all-devices"
        }

        override fun getImage(): Int {
            TODO("Not yet implemented")
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
        },
        EXTENSION_TWO {
            override fun getTopic(): String {
                return "extension-two"
            }

            override fun getImage(): Int {
                TODO("Not yet implemented")
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
        },
        TWO {
            override fun getTopic(): String {
                return "monitor-two"
            }

            override fun getImage(): Int {
                return R.drawable.baseline_tv_white_48dp
            }
        };
    }
}

enum class DeviceState {
    ON,
    OFF
}