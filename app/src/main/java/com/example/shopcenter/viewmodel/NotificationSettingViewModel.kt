package com.example.shopcenter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.shopcenter.utillity.workmanager.NewProductNotifyWorker

class NotificationSettingViewModel(application: Application) : AndroidViewModel(application) {

    fun scheduleNotification(min : Long){
        NewProductNotifyWorker.scheduleNotification(getApplication(),min,true)
    }
}