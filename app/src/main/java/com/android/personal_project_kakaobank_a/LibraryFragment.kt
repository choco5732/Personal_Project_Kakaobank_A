package com.android.personal_project_kakaobank_a

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_kakaobank_a.adapter.LibraryAdapter
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.LibraryFragmentBinding

class LibraryFragment : Fragment() {
    companion object {
        fun newInstance() = LibraryFragment()
    }


    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewAdapter by lazy {
        LibraryAdapter()
    }
    private val KakaoList = arrayListOf<KakaoData>()

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

    override fun onPause() {
        super.onPause()
        KakaoList.clear()
        recyclerViewAdapter.notifyDataSetChanged()
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

        /**
         *  라이브러리에 '좋아요' 연락처 추가
         */
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val itemList = bundle.getParcelableArrayList<KakaoData>("item")!!

            recyclerViewAdapter.addItems(itemList)

            itemList.clear()
        }

        /**
         *  클릭시 라이브러리에서 연락처 삭제
         */
        recyclerViewAdapter.itemClick = object : LibraryAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                recyclerViewAdapter.deleteItem(position)
            }
        }
    }
}