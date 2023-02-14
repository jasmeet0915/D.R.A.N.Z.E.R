package com.developers.dranzer.ui.devices

import androidx.lifecycle.ViewModel
import com.developers.dranzer.domain.SetDeviceStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(deviceStateUseCase: SetDeviceStateUseCase) : ViewModel() {

}