package com.stylingandroid.mdcmotion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.stylingandroid.mdcmotion.databinding.FragmentContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerFragment : Fragment() {

    private lateinit var binding: FragmentContainerBinding

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
        binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}
