package com.applanticstudio.squarefencing.ui.historic


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applanticstudio.squarefencing.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_historical_data.*


/**
 * A simple [Fragment] subclass.
 *
 */
class HistoricalDataFragment : Fragment() {

    private lateinit var viewModel: HistoricalDataViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HistoricalDataViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historical_data, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchHistoricalData().observe(this, Observer {
            if (it != null) {
                events_recyclerview.adapter = HistoricalDataAdapter(it)
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

        events_recyclerview.adapter = HistoricalDataAdapter(listOf())
    }
}
