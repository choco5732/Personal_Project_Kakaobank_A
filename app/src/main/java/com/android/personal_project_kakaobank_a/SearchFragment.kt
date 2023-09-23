package com.android.personal_project_kakaobank_a

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_kakaobank_a.adapter.SearchAdapter
import com.android.personal_project_kakaobank_a.data.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.SearchFragmentBinding
import com.android.personal_project_kakaobank_a.retrofit.NetworkClient
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
        val test = arrayListOf<KakaoModel>()
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!


    val testList = arrayListOf<KakaoModel>()

    private val recyclerViewAdpater by lazy {
        SearchAdapter()
        }

    private val viewModel: SearchViewModel by viewModels()


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

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() = with(binding){

        /**
         * 리사이클러뷰 어댑터, 레이아웃매니저 설정
         */
        recyclerView.adapter = recyclerViewAdpater
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        // SharedPreferences -> 마지막 검색어 호출
        loadData()

        with(viewModel) {
            list.observe(viewLifecycleOwner) {
                recyclerViewAdpater.submitList(it)
            }
        }


        /**
         *  '검색 버튼' 클릭 시 EditText에 입력한 값으로 쿼리를 보내
         *  서버로부터 데이터를 받아와 리사이클러뷰에 적용해 화면에 보여주는 코드
         */
        btnSearch.setOnClickListener {
            recyclerViewAdpater.notifyDataSetChanged()
            val query = etSearchKeyword.text.toString()
            Log.d("SearchFragment","#choco5732 query : $query")
            communicateNetWork(setUpKakaoParameter(query))

            // SharedPreferences -> 검색어 저장
            saveData()
        }

        /**
         * 연락처 클릭 시 라이브러리에 추가하는 로직
         */
        recyclerViewAdpater.itemClick = object : SearchAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                test[position].isAdd = !test[position].isAdd
                recyclerViewAdpater.notifyDataSetChanged()
                val choicedItem = test[position]
                testList.add(choicedItem)
                setFragmentResult("requestKey", bundleOf("item" to testList))
            }
        }
    }

    /**
     * SharedPreferences -> 검색어 불러오기
     */
    private fun loadData() {
        val preference = this.activity?.getSharedPreferences("preference",0)
        binding.etSearchKeyword.setText(preference?.getString("searchKeyword",""))
    }

    /**
     * SharedPreferences -> 검색어 저장
     */
    private fun saveData() {
        val preference = this.activity?.getSharedPreferences("preference", 0)
        val edit = preference?.edit()
        edit?.putString("searchKeyword", binding.etSearchKeyword.text.toString())
        edit?.apply()
        Log.d("SearchFragment", "#choco5732 searchKeyword: ${binding.etSearchKeyword.text.toString()}")
    }

    /**
     * GET요청에 쓰일 파라메터
     */
    private fun setUpKakaoParameter(query: String): HashMap<String, String> {
        return hashMapOf(
            "query" to query,
            "size" to "80"
        )
    }

    /**
     * 서버로부터 데이터를 받아오는 로직
     */
    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
        val responseData = NetworkClient.kakaoNetWork.getKakao(param = param)

        val item = responseData.documents

        item.forEach {
            viewModel.addSearchItem(KakaoModel(thumbnail_url = it.thumbnailUrl, displaySiteName = it.displaySitename, dateTime = it.datetime, isAdd = false))
        }


    }
}