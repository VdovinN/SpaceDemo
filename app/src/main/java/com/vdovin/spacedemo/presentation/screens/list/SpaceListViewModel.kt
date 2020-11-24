package com.vdovin.spacedemo.presentation.screens.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.presentation.util.click.SingleLiveEvent
import com.vdovin.spacedemo.presentation.base.BaseViewModel
import com.vdovin.spacedemo.presentation.screens.list.model.SpacesView
import com.vdovin.spacedemo.presentation.util.mapper.toSpacesView
import com.vdovin.spacedemo.usecase.GetSpacesUseCase
import com.vdovin.spacedemo.usecase.base.UseCase

class SpaceListViewModel
@ViewModelInject constructor(
        private val getSpacesUseCase: GetSpacesUseCase,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val navigateToDetail = SingleLiveEvent<Long>()

    private val _data: MutableLiveData<List<SpacesView>> = MutableLiveData()
    val data: LiveData<List<SpacesView>>
        get() = _data

    fun getSpaces() {
        if (data.value.isNullOrEmpty()) {
            getSpacesUseCase(UseCase.None()) { it.fold(::handleFailure, ::handleSpaces) }
        }
    }
    private fun handleSpaces(spaceList: List<Space>) {
        _data.value = spaceList.map {
            it.toSpacesView()
        }
    }

    fun openSpaceDetails(flightNumber: Long) {
        navigateToDetail.value = flightNumber
    }

    override fun onCleared() {
        super.onCleared()
        getSpacesUseCase.cancel()
    }
}