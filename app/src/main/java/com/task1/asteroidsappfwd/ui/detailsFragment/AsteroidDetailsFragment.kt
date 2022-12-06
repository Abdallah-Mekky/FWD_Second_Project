package com.task1.asteroidsappfwd.ui.detailsFragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.task1.asteroidsappfwd.R
import com.task1.asteroidsappfwd.databinding.FragmentAsteroidDetailsBinding


class AsteroidDetailsFragment : Fragment() {

    lateinit var asteroidDetailsBinding: FragmentAsteroidDetailsBinding
    private val viewModel: AsteroidDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        asteroidDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_asteroid_details, container, false)

        asteroidDetailsBinding.lifecycleOwner = this
        asteroidDetailsBinding.viewModel = viewModel
        return asteroidDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentAsteroid =
            AsteroidDetailsFragmentArgs.fromBundle(requireArguments()).asteroidDetails

        asteroidDetailsBinding.asteroid = currentAsteroid
        subscribeToLiveData()
    }

    private fun displayExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)

        builder.create().show()
    }


    private fun subscribeToLiveData() {

        viewModel.clicked.observe(viewLifecycleOwner) {

            if (it) {

                displayExplanationDialog()
                viewModel.isClicked()
            }
        }
    }

}