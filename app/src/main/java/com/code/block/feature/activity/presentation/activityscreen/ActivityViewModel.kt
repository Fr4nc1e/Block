package com.code.block.feature.activity.presentation.activityscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.code.block.feature.activity.domain.usecase.GetActivitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    getActivitiesUseCase: GetActivitiesUseCase
) : ViewModel() {

    val activities = getActivitiesUseCase()
        .cachedIn(viewModelScope)

    private val _state = mutableStateOf(ActivityState())
    val state: State<ActivityState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun onEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.ClickedOnUser -> TODO()
            is ActivityEvent.ClickedOnParent -> TODO()
            ActivityEvent.Refresh -> {
                refresh()
            }
        }
    }

    fun refresh(
        onRefresh: () -> Unit = {}
    ) {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            delay(500)
            onRefresh()
            _isRefreshing.emit(false)
        }
    }
}
