package com.martnear.githubclosedprs.utils

object Constants {
    const val BASE_URL = "https://api.github.com/repos/"
    const val owner = "square"
    const val repo = "retrofit"
    const val PULLS_ENDPOINT = "pulls"
    const val RETROFIT_REPO_ENDPOINT = "$owner/$repo/$PULLS_ENDPOINT"
}