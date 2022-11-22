package dev.kawaiidevs.mvvmcleantemplate.modules.search.view

import android.content.Context
import android.view.ViewGroup
import dev.kawaiidevs.mvvmcleantemplate.R
import dev.kawaiidevs.mvvmcleantemplate.adapter.ItemView
import dev.kawaiidevs.mvvmcleantemplate.databinding.ItemItunesBinding
import dev.kawaiidevs.mvvmcleantemplate.modules.search.entities.ItunesItemModelUi
import dev.kawaiidevs.mvvmcleantemplate.utils.itemViewBinding
import dev.kawaiidevs.mvvmcleantemplate.utils.setSingleClickListener

class SearchItemView(
    override val context: Context,
    onClickListener: ((ItunesItemModelUi) -> Unit)
) : ItemView<ItunesItemModelUi> {

    private val binding by  itemViewBinding<ItemItunesBinding>(R.layout.item_itunes)

    override val view = binding.root

    override lateinit var data: ItunesItemModelUi

    init {
        binding.root.apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            binding.cardViewTokenHome.setSingleClickListener {
                onClickListener(data)
            }
        }
    }

    override fun bind(item: ItunesItemModelUi) {
        data = item
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}