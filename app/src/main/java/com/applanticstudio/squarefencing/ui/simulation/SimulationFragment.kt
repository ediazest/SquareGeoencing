package com.applanticstudio.squarefencing.ui.simulation


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applanticstudio.squarefencing.R
import kotlinx.android.synthetic.main.fragment_simulation.*

/**
 * A simple [Fragment] subclass.
 *
 */
class SimulationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        start_button.setOnClickListener {
            console_textview.text = "${console_textview.text}Starting simulation\n"
        }
    }
}
