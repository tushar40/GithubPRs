package com.martnear.githubclosedprs.repos

import com.martnear.githubclosedprs.R
import com.martnear.githubclosedprs.api.ApiClient
import com.martnear.githubclosedprs.models.PullRequestState
import com.martnear.githubclosedprs.models.ResultWrapper
import com.martnear.githubclosedprs.utils.getStringResource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.HttpURLConnection

class Repository {
    private val mApiService = ApiClient.getApi()

    suspend fun getPullRequests(pullRequestState: PullRequestState) = makeApiCall {
        mApiService.getClosedPRs(pullRequestState.name)
    }

    private suspend fun <T>makeApiCall(call: suspend () -> Response<T>) = flow {
        try {
            val response = call()
            val statusCode = response.code()

            if (statusCode == HttpURLConnection.HTTP_OK) {
                emit(ResultWrapper.OnSuccess(response.body()!!))
            } else {
                if (statusCode == HttpURLConnection.HTTP_UNAVAILABLE) {
                    emit(ResultWrapper.ShowServerDownScreen)
                } else {
                    emit(
                        ResultWrapper.OnServerError(
                            HttpException(response),
                            statusCode,
                            getStringResource(R.string.server_error_message)
                        ))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultWrapper.OnNetworkError(e))
        }
    }
}