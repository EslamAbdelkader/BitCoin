package com.eslam.blockchain.util

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Shows a SwipeRefreshLayout refreshing marker
 */
fun SwipeRefreshLayout.showLoading() {
    isRefreshing = true
}

/**
 * Hides a SwipeRefreshLayout refreshing marker
 */
fun SwipeRefreshLayout.hideLoading() {
    isRefreshing = false
}