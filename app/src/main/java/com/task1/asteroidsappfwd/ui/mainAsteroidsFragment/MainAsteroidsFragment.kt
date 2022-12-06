package com.task1.asteroidsappfwd.ui.mainAsteroidsFragment

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task1.asteroidsappfwd.R
import com.task1.asteroidsappfwd.databinding.FragmentMainAsteroidsBinding
import com.task1.asteroidsappfwd.ui.models.Asteroid
import com.task1.asteroidsappfwd.ui.getEndDate
import com.task1.asteroidsappfwd.ui.getStartDate


class MainAsteroidsFragment : Fragment() {

    lateinit var mainAsteroidsBinding: FragmentMainAsteroidsBinding
    var asteroidAdapter = AsteroidesAdapter(null)
    val viewModel: MainAsteroidsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)

        mainAsteroidsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_asteroids, container, false)
        mainAsteroidsBinding.lifecycleOwner = this

        mainAsteroidsBinding.mainAsteroidsViewModel = viewModel
        return mainAsteroidsBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun subscribeToLiveData() {

        viewModel.asteroidsList.observe(viewLifecycleOwner) {

            initAsteroidRecyclerView(it)
        }

        viewModel.progressAsteroids.observe(viewLifecycleOwner){

                mainAsteroidsBinding.progressLoadingAsteroids.isVisible = it

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.getAll -> {
                viewModel.getAllAsteroids()
            }
            R.id.getDate -> {
                viewModel.getAsteroidByDate(getStartDate(), getEndDate())
            }
            else -> {
                viewModel.getAsteroidsByDay(getStartDate())
            }
        }

        return true
    }

    private fun initAsteroidRecyclerView(asteroidsList:List<Asteroid>){

        mainAsteroidsBinding.asteroidRV.adapter = asteroidAdapter

        asteroidAdapter.refreashAdapter(asteroidsList)

        asteroidAdapter.onItemClickListener = object : AsteroidesAdapter.OnItemClickListener {

            override fun onClick(position: Int, item: Asteroid) {

                val action =
                    MainAsteroidsFragmentDirections.actionMainAsteroidsFragmentToAsteroidDetailsFragment(
                        item
                    )
                findNavController().navigate(action)
            }

        }
    }

}