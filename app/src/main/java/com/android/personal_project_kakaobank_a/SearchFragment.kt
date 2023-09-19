package com.android.personal_project_kakaobank_a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.android.personal_project_kakaobank_a.databinding.SearchFragmentBinding
import com.android.personal_project_kakaobank_a.retrofit.NetworkClient
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SearchFragment()
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

        communicateNetWork(setUpKakaoParameter("google"))

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
        Log.e("MainActivity", "#choco5732 받은데이터 : $responseData")

        val items = responseData.documents[0].width

//        val goo = ArrayList<String>()
//        items.forEach {
//            Log.d("add Item :", it.stationName)
//            goo.add(it.stationName)
//        }

//        runOnUiThread {
            binding.tv1.text = "$items"
//        }

    }
}