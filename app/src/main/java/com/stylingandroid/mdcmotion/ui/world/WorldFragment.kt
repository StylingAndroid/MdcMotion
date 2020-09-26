package com.stylingandroid.mdcmotion.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import com.stylingandroid.bottomnavbarshape.databinding.FragmentWorldBinding
import com.stylingandroid.mdcmotion.ui.home.WorldViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorldFragment : Fragment() {

    private val viewModel: WorldViewModel by viewModels()

    private lateinit var binding: FragmentWorldBinding

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
        binding = FragmentWorldBinding.inflate(inflater, container, false)
        return binding.root
    }
}
