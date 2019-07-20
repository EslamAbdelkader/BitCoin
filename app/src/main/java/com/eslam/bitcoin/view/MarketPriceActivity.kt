package com.eslam.bitcoin.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eslam.bitcoin.R
import com.eslam.bitcoin.model.MarketPriceUIModel
import com.eslam.bitcoin.model.State
import com.eslam.bitcoin.util.hideLoading
import com.eslam.bitcoin.util.showLoading
import com.eslam.bitcoin.viewmodel.MarketPriceViewModel
import com.eslam.bitcoin.viewmodel.MarketPriceViewModelFactory
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_market_price.*


/**
 * The Home activity, showing the MarketPrice graph
 */
class MarketPriceActivity : AppCompatActivity() {

    private val marketPriceViewModel: MarketPriceViewModel by lazy {
        ViewModelProviders.of(
            this,
            MarketPriceViewModelFactory()
        ).get(MarketPriceViewModel::class.java)
    }

    /**
     * Initializing views, Observing LiveData components, and setting refresh listener
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_price)

        marketPriceViewModel.getState().observe(this, Observer { handleState(it) })

        swipeLayout.setOnRefreshListener { reload() }

        initChart()
    }

    /**
     * Sets initial attributes of chart
     */
    private fun initChart() {
        chartView.setNoDataTextTypeface(Typeface.MONOSPACE)
        chartView.setNoDataTextColor(Color.BLACK)
        chartView.description.text = ""
        chartView.axisRight.isEnabled = false
        chartView.xAxis.isEnabled = false
        chartView.invalidate()
    }

    /**
     * Assigns [model]'s data to the screen views
     */
    private fun populateData(model: MarketPriceUIModel) {
        titleTextView.text = model.title
        subtitleTextView.text = model.subtitle
        redrawChart(model.graphPoints)
    }

    /**
     * Handles the state of the data, either shows the data, the error, or the loading indicator
     */
    private fun handleState(state: State) {
        when (state) {
            is State.SUCCESS<*> -> {
                swipeLayout.hideLoading()
                populateData(state.data as MarketPriceUIModel)
            }
            is State.ERROR -> {
                swipeLayout.hideLoading()
                showError(state.message)
            }
            is State.LOADING -> {
                swipeLayout.showLoading()
            }
        }
    }

    /**
     * Init SnackBar View
     */
    private fun showError(message: String) {
        Snackbar.make(resultLayout, message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(ANIMATION_MODE_SLIDE)
            .setAction(R.string.retry_button_title) { reload() }
            .show()
    }

    /**
     * Reload data
     */
    private fun reload() {
        marketPriceViewModel.loadData()
    }

    /**
     * Assigns new data to chart and redraw
     */
    private fun redrawChart(list: List<Entry>) {
        val set = LineDataSet(list, getString(R.string.set_title))
        set.lineWidth = 3f
        set.color = getColor(R.color.colorPrimary)
        set.setDrawCircles(false)
        set.isHighlightEnabled = false

        val data = LineData(set)
        chartView.data = data

        chartView.invalidate()
        chartView.animateX(1000)
    }
}