package com.android.personal_project_kakaobank_a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.personal_project_kakaobank_a.databinding.LibraryFragmentBinding
import com.android.personal_project_kakaobank_a.databinding.SearchFragmentBinding

class LibraryFragment : Fragment() {

    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = LibraryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}