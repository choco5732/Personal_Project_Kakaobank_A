package com.android.personal_project_kakaobank_a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.personal_project_kakaobank_a.adapter.ImageAdapter
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.SearchFragmentBinding
import com.android.personal_project_kakaobank_a.retrofit.NetworkClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!


    private val recyclerViewAdpater by lazy {
        ImageAdapter(test)
    }
    companion object {
        fun newInstance() = SearchFragment()
        val test = ArrayList<KakaoData>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSearch.setOnClickListener {
            test.clear()
            val query = binding.etSearchKeyword.text.toString()
            communicateNetWork(setUpKakaoParameter("$query"))
        }

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setUpKakaoParameter(query: String): HashMap<String, String> {

        return hashMapOf(
            "query" to query
        )
    }

    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
        val responseData = NetworkClient.kakaoNetWork.getKakao(param = param)
        Log.e("MainActivity", "#choco5732 받은 데이터 : $responseData")
//        val items = responseData.documents[0].thumbnailUrl

        val item = responseData.documents

        item.forEach {
            test.add(KakaoData(it.thumbnailUrl, it.displaySitename, it.datetime))
        }

        Log.d("MainActivity", "#choco5732 List : $test")

        binding.recyclerView.adapter = recyclerViewAdpater

//        val goo = ArrayList<String>()
//        items.forEach {
//            Log.d("add Item :", it.stationName)
//            goo.add(it.stationName)
//        }
//        binding.tv1.text = "$items"
    }
}