package com.martnear.githubclosedprs.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.martnear.githubclosedprs.R
import com.martnear.githubclosedprs.databinding.ItemPullRequestBinding
import com.martnear.githubclosedprs.models.PullRequest
import com.martnear.githubclosedprs.utils.DateTimeManager
import com.martnear.githubclosedprs.utils.PullRequestDiffCallback

class PullRequestAdapter(private val itemClickListener: OnItemClickListener): ListAdapter<PullRequest, PullRequestAdapter.ViewHolder>(
    PullRequestDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPullRequestBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemPullRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        infix fun bind(pullRequest: PullRequest) {
            with(binding) {
                tvName.text = pullRequest.user.login
                tvDateCreated.text = DateTimeManager.getFormattedTime(pullRequest.created_at)
                tvDateClosed.text = root.context.getString(R.string.closed_at, DateTimeManager.getFormattedTime(pullRequest.closed_at))
                tvTitle.text = pullRequest.title

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(pullRequest.user.avatar_url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivProfileImage)

                ivProfileImage.setOnClickListener {
                    itemClickListener.onAvatarClicked(pullRequest.user.html_url)
                }

                itemView.setOnClickListener {
                    itemClickListener.onItemClicked(pullRequest.html_url)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onAvatarClicked(url: String)
        fun onItemClicked(url: String)
    }
}