package com.android.personal_project_kakaobank_a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_kakaobank_a.adapter.LibraryAdapter
import com.android.personal_project_kakaobank_a.adapter.SearchAdapter
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.LibraryFragmentBinding
import com.android.personal_project_kakaobank_a.databinding.SearchFragmentBinding
import com.bumptech.glide.Glide

class LibraryFragment : Fragment() {
    companion object {
        fun newInstance() = LibraryFragment()
        var sendList = arrayListOf<KakaoData>()
        val testList = arrayListOf<KakaoData>()
    }
    private val testSet = mutableSetOf<KakaoData>()

    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewAdapter by lazy{
        SearchAdapter(testList)
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

        binding.recyclerView.adapter = recyclerViewAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val itemList = bundle.getParcelableArrayList<KakaoData>("item")!!

//            sendList.clear()
            for (i in 0 until itemList.size) {
                if (itemList[i] !in testSet) {
                    testSet.add(itemList[i])
                    recyclerViewAdapter.addItem(itemList[i])
                }
            }
            itemList.clear()
        }


        recyclerViewAdapter.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                sendList.add(recyclerViewAdapter.getList(position))

                setFragmentResult("favorite", bundleOf("removedata" to sendList))
                testList.remove(recyclerViewAdapter.getList(position))
                Log.d("testList", testList.toString())
                recyclerViewAdapter.deleteItemPosition(position)
            }
        }

    }


//        initView()


//    private fun initView() = with(binding) {
//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            val itemList = bundle.getParcelableArrayList<KakaoData>("item")!!
//
//            for (i in 0 until itemList.size) {
//                if (itemList[i] !in testSet) {
//                    testSet.add(itemList[i])
//                    recyclerViewAdapter.addItem(itemList[i])
//                }
//            }
//            itemList.clear()
//        }
//
//        recyclerView.adapter = recyclerViewAdapter
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//    }

//            for (i in 0 until itemList.size) {
//                if (itemList[i] !in testSet) {
//                    testSet.add(itemList[i])
//                    recyclerViewAdapter.addItem(itemList[i])
//                }
//            }
//            itemList.clear()
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}