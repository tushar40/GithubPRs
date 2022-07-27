package com.martnear.githubclosedprs.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martnear.githubclosedprs.repos.Repository
import com.martnear.githubclosedprs.models.*
import kotlinx.coroutines.launch

class PullRequestViewModel: ViewModel() {
    private val repository= Repository()
    private val pullRequestState = PullRequestState.closed
    private val currentUiState: MutableLiveData<UiState> = MutableLiveData()
    fun getUiStates(): LiveData<UiState> = currentUiState

    fun getPullRequests() {
        currentUiState.value = UiState.Progress
        viewModelScope.launch {
            repository.getPullRequests(pullRequestState).collect { result ->
                result.onSuccess {
                    currentUiState.value = UiState.Content(it)
                }.onNetworkError {
                    currentUiState.value = UiState.NetworkError {
                        getPullRequests()
                    }
                }.onServerError { error, code, errorMessage ->
                    currentUiState.value = UiState.ServerError(errorMessage) {
                        getPullRequests()
                    }
                }.showServerDownScreen {
                    currentUiState.value = UiState.ServerDownError {
                        getPullRequests()
                    }
                }
            }
        }
    }
}