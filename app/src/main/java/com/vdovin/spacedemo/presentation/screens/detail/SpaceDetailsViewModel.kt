package com.vdovin.spacedemo.presentation.screens.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.vdovin.spacedemo.domain.Space
import com.vdovin.spacedemo.presentation.util.click.SingleLiveEvent
import com.vdovin.spacedemo.presentation.base.BaseViewModel
import com.vdovin.spacedemo.presentation.screens.detail.model.SpaceDetailsView
import com.vdovin.spacedemo.presentation.util.mapper.toSpaceDetailsView
import com.vdovin.spacedemo.usecase.GetSpaceDetailsUseCase

class SpaceDetailsViewModel
@ViewModelInject constructor(
        private val getSpaceDetailsUseCase: GetSpaceDetailsUseCase,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val navigateBack = SingleLiveEvent<Unit>()
    val openVideo = SingleLiveEvent<String>()
    val openWiki = SingleLiveEvent<String>()

    private val _data: MutableLiveData<SpaceDetailsView> = MutableLiveData()

    val data: LiveData<SpaceDetailsView>
        get() = _data

    fun getSpaceByFlightNumber(flightNumber: Long) =
        getSpaceDetailsUseCase(GetSpaceDetailsUseCase.Params(flightNumber)) {
            it.fold(::handleFailure, ::handleSpaceDetails)
        }

    fun openVideo(videoId: String?) {
        openVideo.value = videoId
    }

    fun openWiki(wikiUrl: String?) {
        openWiki.value = wikiUrl
    }

    fun backPressed() {
        navigateBack.executeEmpty()
    }

    private fun handleSpaceDetails(space: Space) {
        _data.value = space.toSpaceDetailsView()
    }

    override fun onCleared() {
        super.onCleared()
        getSpaceDetailsUseCase.cancel()
    }
}
