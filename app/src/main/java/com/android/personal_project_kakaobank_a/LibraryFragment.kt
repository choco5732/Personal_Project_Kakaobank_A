package com.android.personal_project_kakaobank_a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_kakaobank_a.adapter.LibraryAdapter
import com.android.personal_project_kakaobank_a.data.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.LibraryFragmentBinding

class LibraryFragment : Fragment() {
    companion object {
        fun newInstance() = LibraryFragment()
    }


    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewAdapter by lazy {
        LibraryAdapter { position, item ->
            viewModel.removeLibraryItem(position)
            sharedViewModel.updateSearchItem(item)
        }
    }

    private val viewModel: LibraryViewModel by viewModels()
    private val sharedViewModel: MainSharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() = with(binding) {

        /**
         *  리사이클러뷰 어댑터, 레이아웃매니저 설정
         */
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        with(viewModel) {
            list.observe(viewLifecycleOwner) {
                recyclerViewAdapter.submitList(it)
            }
        }

        with(sharedViewModel) {
            libraryEvent.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is MainSharedEventForLibrary.UpdateLibraryItems -> {
                        viewModel.updateLibraryItems(event.items)
                    }
                    else -> Unit
                }
            }
        }

    }

}