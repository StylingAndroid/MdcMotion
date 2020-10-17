package com.stylingandroid.mdcmotion.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialFadeThrough
import com.stylingandroid.mdcmotion.R
import com.stylingandroid.mdcmotion.databinding.FragmentContainerBinding
import com.stylingandroid.mdcmotion.databinding.ItemLayoutBinding
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
        binding.card.setOnClickListener { card ->
            navigateCard(
                card,
                binding.textView,
                getString(R.string.container_card),
                getString(R.string.shared_elements_text),
                ContainerFragmentDirections.actionContainerFragmentToContentFragment(
                    binding.textView.text.toString()
                )
            )
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(inflater.context, VERTICAL, false)
            adapter = ItemsAdapter(
                inflater.context.resources.getStringArray(R.array.items).toList()
            ) { card, textView, index ->
                navigateCard(
                    card,
                    textView,
                    getString(R.string.container_item_card, index),
                    getString(R.string.shared_elements_item_text, index),
                    ContainerFragmentDirections.actionContainerFragmentToContentItemFragment(
                        textView.text.toString(),
                        index
                    )
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun navigateCard(
        card: View,
        textView: TextView,
        cardTransitionName: String,
        textTransitionName: String,
        navDirections: NavDirections
    ) {
        val extras = FragmentNavigatorExtras(
            card to cardTransitionName,
            textView to textTransitionName
        )
        findNavController().navigate(navDirections, extras)
    }
}

private class ItemsAdapter(
    private val items: List<String>,
    private val selectionHandler: (View, TextView, Int) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false),
            selectionHandler
        )

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        viewHolder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}

private class ItemViewHolder(
    private val binding: ItemLayoutBinding,
    private val selectionHandler: (View, TextView, Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String, position: Int) {
        val context = itemView.context
        binding.card.transitionName = context.getString(R.string.container_item_card, position)
        binding.textView.transitionName =
            context.getString(R.string.shared_elements_item_text, position)
        binding.card.setOnClickListener { card ->
            selectionHandler(card, binding.textView, position)
        }
        binding.textView.text = text
    }
}
