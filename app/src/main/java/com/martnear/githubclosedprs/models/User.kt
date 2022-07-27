package com.martnear.githubclosedprs.models

data class User(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val html_url: String,
    val site_admin: Boolean
)