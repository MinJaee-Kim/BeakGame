package com.example.beakgame

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS,
    RESET
}

class MyViewModel : ViewModel() {
    private val _currentValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        Log.d("TAG", "MyviewModel 호출")
        _currentValue.value = 0
    }

    fun updateValue (actionType: ActionType) {
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(1)
            ActionType.RESET ->
                _currentValue.value = 0
        }
    }
}