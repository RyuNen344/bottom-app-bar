package com.ryunen344.bottomappbar.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ryunen344.bottomappbar.home.databinding.FragmentHomeBinding
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = homeViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setupWithNavController(findNavController())

        binding.fab.setOnClickListener {
            val directions = HomeFragmentDirections.actionHomeToDetail()
            findNavController().navigate(directions)
        }

        binding.list.setOnApplyWindowInsetsListener { v, insets ->
            binding.list.updatePadding(
                bottom = insets.systemWindowInsetBottom
            )

            return@setOnApplyWindowInsetsListener insets
        }
    }
}
