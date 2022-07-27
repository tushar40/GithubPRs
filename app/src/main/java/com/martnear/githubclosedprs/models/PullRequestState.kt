package com.martnear.githubclosedprs.models

import com.google.gson.annotations.SerializedName

enum class PullRequestState {
    @SerializedName("open")
    open,
    @SerializedName("closed")
    closed,
    @SerializedName("all")
    all
}