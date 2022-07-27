package com.martnear.githubclosedprs.api

import com.martnear.githubclosedprs.models.PullRequest
import com.martnear.githubclosedprs.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @Headers("Accept: application/vnd.github+json")
    @GET(Constants.RETROFIT_REPO_ENDPOINT)
    suspend fun getClosedPRs(@Query("state") state: String): Response<List<PullRequest>>
}