package com.applanticstudio.squarefencing.ui.historical


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applanticstudio.squarefencing.R
import kotlinx.android.synthetic.main.fragment_historical_data.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HistoricalDataFragment : Fragment() {

    private lateinit var viewModel: HistoricalDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HistoricalDataViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historical_data, container, false)
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchHistoricalData().observe(this, Observer {
            if (it != null) {
                (events_recyclerview.adapter as HistoricalDataAdapter).updateList(it)
                events_recyclerview.smoothScrollToPosition(it.size)
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context,
                layoutManager.orientation)
        events_recyclerview.layoutManager = layoutManager
        events_recyclerview.addItemDecoration(dividerItemDecoration)

        events_recyclerview.adapter = HistoricalDataAdapter(mutableListOf())
    }
}
