package com.android.personal_project_retrofit.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_retrofit.presentation.main.MainSharedEventForSearch
import com.android.personal_project_retrofit.presentation.main.MainSharedViewModel
import com.android.personal_project_retrofit.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val searchAdapter by lazy {
        SearchAdapter(
            onItemClick = { _, item ->
                viewModel.modifyKakaoItem(
                    item = item
                )
            }
        )
    }

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = searchAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        loadData() // SharedPreferences -> 마지막 검색어 호출

        btnSearch.setOnClickListener {
            viewModel.removeKakaoItems()

            val query = etSearchKeyword.text.toString()

            viewModel.search(query)
            saveData() // SharedPreferences -> 검색어 저장
        }
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
            sharedViewModel.updateLibraryItems(it)
        }

        sharedViewModel.searchEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is MainSharedEventForSearch.UpdateSearchItem -> {
                    viewModel.modifyKakaoItem(event.item)
                    searchAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    /**
     * SharedPreferences -> 검색어 불러오기
     */
    private fun loadData() {
        val preference = this.activity?.getSharedPreferences("preference", 0)
        binding.etSearchKeyword.setText(preference?.getString("searchKeyword", ""))
    }

    /**
     * SharedPreferences -> 검색어 저장
     */
    private fun saveData() {
        val preference = this.activity?.getSharedPreferences("preference", 0)
        val edit = preference?.edit()
        edit?.putString("searchKeyword", binding.etSearchKeyword.text.toString())
        edit?.apply()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}