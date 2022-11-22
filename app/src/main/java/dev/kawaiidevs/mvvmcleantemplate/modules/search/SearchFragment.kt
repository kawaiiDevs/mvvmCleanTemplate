package dev.kawaiidevs.mvvmcleantemplate.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.kawaiidevs.mvvmcleantemplate.R
import dev.kawaiidevs.mvvmcleantemplate.adapter.GenericAdapter
import dev.kawaiidevs.mvvmcleantemplate.adapter.ItemDataAbstract
import dev.kawaiidevs.mvvmcleantemplate.components.CustomDialogFragment
import dev.kawaiidevs.mvvmcleantemplate.data.network.NetworkStatus
import dev.kawaiidevs.mvvmcleantemplate.data.network.NetworkStatusHelper
import dev.kawaiidevs.mvvmcleantemplate.databinding.FragmentSearchBinding
import dev.kawaiidevs.mvvmcleantemplate.modules.search.entities.ItunesItemModelUi
import dev.kawaiidevs.mvvmcleantemplate.modules.search.view.SearchItemView
import dev.kawaiidevs.mvvmcleantemplate.modules.search.view.SearchUiState
import dev.kawaiidevs.mvvmcleantemplate.utils.OnSingleClickListener
import dev.kawaiidevs.mvvmcleantemplate.utils.hideKeyboard
import dev.kawaiidevs.mvvmcleantemplate.utils.toggleVisibility
import dev.kawaiidevs.mvvmcleantemplate.utils.viewBinding

const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val binding by viewBinding<FragmentSearchBinding>()
    private val viewModel by viewModels<SearchViewModel>()
    private val itunesAdapter by lazy {
        GenericAdapter { parent, _ ->
            SearchItemView(
                parent.context,
                ::onItemSelected
            )
        }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@SearchFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        initViews()
    }

    private fun initViews() {
        binding.rvItunesList.adapter = itunesAdapter
        addSearchListener()
        hideAndClear()
        observeUiStates()
        networkStatusVisibility()
    }

    private fun observeUiStates() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.searchUiState.collect { searchUiState ->
                when (searchUiState) {
                    SearchUiState.Loading -> {
                        binding.progress.toggleVisibility(show = true)
                    }
                    is SearchUiState.Error, SearchUiState.ShowNoConnectivityError -> {
                        binding.progress.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                        showDialog(
                            resources.getString(R.string.title_generic_exception_error),
                            resources.getString(R.string.body_generic_exception_error),
                            R.drawable.ic_error
                        )
                    }
                    SearchUiState.Default -> {
                        binding.progress.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                    }
                    SearchUiState.NoDataFound -> {
                        showDialog(
                            resources.getString(R.string.title_generic_exception_error),
                            resources.getString(R.string.empty_query_label),
                            R.drawable.ic_error
                        )
                        binding.rvItunesList.toggleVisibility(false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = true)
                        binding.progress.toggleVisibility(show = false)
                        binding.includeEmptyErrorView.toggleVisibility(show = false)
                    }
                    is SearchUiState.Success -> {
                        itunesAdapter.submitList(
                            searchUiState.data?.map { itunesItemUiModel ->
                                ItemDataAbstract(
                                    itunesItemUiModel
                                )
                            }
                        )
                        binding.progress.toggleVisibility(show = false)
                        binding.includeEmptyErrorView.toggleVisibility(show = false)
                        binding.includeGenericErrorView.root.toggleVisibility(show = false)
                        binding.rvItunesList.toggleVisibility(true)
                    }
                }
            }
        }
    }

    private fun addSearchListener() {
        binding.itunesSearchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getItunesList(query)
                hideAndClear()
                binding.progress.toggleVisibility(show = true)
                return true
            }
        })
    }

    private fun networkStatusVisibility() {
        context?.let { context ->
            NetworkStatusHelper(context).observe(this) { networkStatus ->
                when (networkStatus) {
                    NetworkStatus.Unavailable -> {
                        binding.txvOffline.toggleVisibility(show = true)
                    }
                    else -> {
                        binding.txvOffline.toggleVisibility(show = false)
                    }
                }
            }
        }
    }

    private fun showDialog(title: String, body: String, icon: Int) {
        activity?.let {
            var dialog: CustomDialogFragment? = null
            dialog = CustomDialogFragment.Builder(requireContext())
                .setIcon(icon, R.color.white)
                .setTitle(title)
                .setMessage(body)
                .setPositiveButton(R.string.accept_label, OnSingleClickListener {
                    dialog?.dismiss()
                })
                .setCancelable(false)
                .create()
            dialog.show(it.supportFragmentManager, TAG)
        }
        hideAndClear()
    }

    private fun hideAndClear() {
        binding.mainViewSearch.hideKeyboard()
        binding.mainViewSearch.clearFocus()
    }

    private fun onItemSelected(item: ItunesItemModelUi) {

        resources.getString(
            R.string.description_artist_itunes,
            item.primaryGenreName,
            item.collectionPrice,
            item.currency,
            item.copyright
        )

        showDialog(
            resources.getString(R.string.title_artist_itunes),
            resources.getString(
                R.string.description_artist_itunes,
                item.primaryGenreName,
                item.collectionPrice,
                item.currency,
                item.copyright
            ),
            R.drawable.ic_music
        )
    }

}