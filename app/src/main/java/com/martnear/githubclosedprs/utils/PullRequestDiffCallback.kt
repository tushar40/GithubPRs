package com.martnear.githubclosedprs.utils

import androidx.recyclerview.widget.DiffUtil
import com.martnear.githubclosedprs.models.PullRequest

class PullRequestDiffCallback: DiffUtil.ItemCallback<PullRequest>() {
    override fun areItemsTheSame(
        oldItem: PullRequest,
        newItem: PullRequest
    ): Boolean {
        val samePullRequest = oldItem.id == newItem.id
        val sameOwner = oldItem.user == newItem.user

        return sameOwner && samePullRequest
    }

    override fun areContentsTheSame(
        oldItem: PullRequest,
        newItem: PullRequest
    ): Boolean {
        val samePullRequest = oldItem.id == newItem.id
        val notEdited = oldItem.closed_at == newItem.closed_at

        return samePullRequest && notEdited
    }
}