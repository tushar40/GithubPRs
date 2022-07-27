package com.martnear.githubclosedprs.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.martnear.githubclosedprs.R
import com.martnear.githubclosedprs.adapters.PullRequestAdapter
import com.martnear.githubclosedprs.databinding.ActivityPullRequestsBinding
import com.martnear.githubclosedprs.models.PullRequest
import com.martnear.githubclosedprs.models.UiState
import com.martnear.githubclosedprs.utils.Constants
import com.martnear.githubclosedprs.utils.getStringResource
import com.martnear.githubclosedprs.viewmodels.PullRequestViewModel


class PullRequestsActivity: AppCompatActivity(), PullRequestAdapter.OnItemClickListener {

    private val pullRequestViewModel: PullRequestViewModel by viewModels()
    private val pullRequestAdapter by lazy { PullRequestAdapter(this) }
    private lateinit var binding: ActivityPullRequestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "PR: ${Constants.owner}/${Constants.repo}"
        binding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI(binding)
        pullRequestViewModel.getUiStates().observe(this) { uiState ->
            showCurrentUiState(uiState)
        }

        fetchPullRequests()
    }

    override fun onAvatarClicked(url: String) {
        openLink(url)
    }

    override fun onItemClicked(url: String) {
        openLink(url)
    }

    private fun openLink(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun setUpUI(binding: ActivityPullRequestsBinding) {
        with(binding) {
            recyclerViewQuestions.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = pullRequestAdapter
            }

            swipeRefreshLayout.setOnRefreshListener {
                fetchPullRequests()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun fetchPullRequests() {
        pullRequestViewModel.getPullRequests()
    }

    private fun showPullRequests(questions: List<PullRequest>) {
        showList()
        pullRequestAdapter.submitList(questions)
    }

    private fun showCurrentUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Content<*> -> {
                if (uiState.data == null) {
                    Toast.makeText(applicationContext, getString(R.string.couldnt_fetch_details), Toast.LENGTH_SHORT).show()
                } else {
                    showPullRequests(uiState.data as List<PullRequest>)
                }
            }
            is UiState.Progress -> showLoading()
            is UiState.NetworkError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.no_internet_message))
            is UiState.ServerError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.server_error_message))
            is UiState.ServerDownError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.server_error_message))
        }
    }

    private fun showLoading() {
        with(binding) {
            loadingView.isVisible = true
            cardViewEmpty.isVisible = false
            swipeRefreshLayout.isVisible = false
        }
    }

    private fun showErrorView(clickListener: View.OnClickListener? = null, msg: String? = "") {
        with(binding) {
            loadingView.isVisible = false
            cardViewEmpty.isVisible = true
            swipeRefreshLayout.isVisible = false

            tvErrorMsg.text= msg
            btnReload.setOnClickListener {
                clickListener?.onClick(it)
            }
        }
    }

    private fun showList() {
        with(binding) {
            loadingView.isVisible = false
            cardViewEmpty.isVisible = false
            swipeRefreshLayout.isVisible = true
        }
    }
}