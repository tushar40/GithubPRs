package com.martnear.githubclosedprs.models

data class PullRequest(
    val id: Int,
    val html_url: String,
    val number: Int,
    val state: PullRequestState,
    val locked: Boolean,
    val title: String,
    val user: User,
    val created_at: String,//"2022-07-25T11:27:16Z",
    val updated_at: String,
    val closed_at: String
)