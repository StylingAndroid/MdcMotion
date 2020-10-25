package com.stylingandroid.mdcmotion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.stylingandroid.mdcmotion.databinding.FragmentAxisBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AxisFragment : Fragment() {

    private lateinit var binding: FragmentAxisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAxisBinding.inflate(inflater, container, false)
        binding.xAxisButtonButton.setOnClickListener {
            setupTransitions(MaterialSharedAxis.X)
            navigate(binding.xAxisButtonButton.text.toString(), MaterialSharedAxis.X)
        }
        binding.yAxisButtonButton.setOnClickListener {
            setupTransitions(MaterialSharedAxis.Y)
            navigate(binding.yAxisButtonButton.text.toString(), MaterialSharedAxis.Y)
        }
        binding.zAxisButtonButton.setOnClickListener {
            setupTransitions(MaterialSharedAxis.Z)
            navigate(binding.zAxisButtonButton.text.toString(), MaterialSharedAxis.Z)
        }
        return binding.root
    }

    private fun setupTransitions(axis: Int) {
        exitTransition = MaterialSharedAxis(axis, true)
        reenterTransition = MaterialSharedAxis(axis, false)
    }

    private fun navigate(text: String, axis: Int) {
        findNavController().navigate(
            AxisFragmentDirections.actionAxisFragmentToAxisDestinationFragment(
                text,
                axis
            )
        )
    }
}
