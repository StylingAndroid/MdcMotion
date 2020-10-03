package com.stylingandroid.mdcmotion.ui.axis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.stylingandroid.mdcmotion.databinding.FragmentAxisBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AxisFragment : Fragment() {

    private val viewModel: AxisViewModel by viewModels()

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
        return binding.root
    }
}
