package com.applanticstudio.squarefencing.ui.simulation


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applanticstudio.squarefencing.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_simulation.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SimulationFragment : Fragment() {

    private lateinit var viewModel: SimulationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SimulationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simulation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_button.setOnClickListener {
            resetLayout(true)
            console_textview.text = ""
            viewModel.startSimulationProcess()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        console_textview.text =
                                "${console_textview.text}$it"
                    }, {
                        resetLayout(false)
                        showGenericErrorDialog()
                    }, {
                        resetLayout(false)
                    })
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun resetLayout(busy: Boolean) {
        start_button.isEnabled = !busy
        start_button.isClickable = !busy
        start_button.isActivated = !busy
    }

    private fun showGenericErrorDialog() {
        if (context != null) {
            val alertDialog = AlertDialog.Builder(context!!).create()
            alertDialog.setTitle(R.string.app_name)
            alertDialog.setMessage(getString(R.string.generic_error))

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_ok), { _, _ -> })

            alertDialog.show()
        }
    }
}
