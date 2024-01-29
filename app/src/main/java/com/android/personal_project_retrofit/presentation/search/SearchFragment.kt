package com.android.personal_project_retrofit.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.personal_project_retrofit.presentation.main.MainSharedEventForSearch
import com.android.personal_project_retrofit.presentation.main.MainSharedViewModel
import com.android.personal_project_retrofit.databinding.SearchFragmentBinding
import com.android.personal_project_retrofit.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
        val test = arrayListOf<Kakao>()
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!


    val testList = arrayListOf<Kakao>()

    private val recyclerViewAdpater by lazy {
        SearchAdapter(
            onItemClick = { position, item ->
                viewModel.modifyKakaoItem(
                    item = item
                )
            }
        )
    }


    private val viewModel: SearchViewModel by viewModels{
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

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() = with(binding) {

        /**
         * 리사이클러뷰 어댑터, 레이아웃매니저 설정
         */
        recyclerView.adapter = recyclerViewAdpater
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)


        // SharedPreferences -> 마지막 검색어 호출
        loadData()

        /**
         *  '검색 버튼' 클릭 시 EditText에 입력한 값으로 쿼리를 보내
         *  서버로부터 데이터를 받아와 리사이클러뷰에 적용해 화면에 보여주는 코드
         */
        btnSearch.setOnClickListener {
            //recyclerViewAdpater.notifyDataSetChanged()
            viewModel.removeKakaoItems()
            val query = etSearchKeyword.text.toString()
            Log.d("SearchFragment", "#choco5732 query : $query")
            communicateNetWork(setUpKakaoParameter(query))

            // SharedPreferences -> 검색어 저장
            saveData()
        }

        with(viewModel) {
            list.observe(viewLifecycleOwner) {
                recyclerViewAdpater.submitList(it)
                sharedViewModel.updateLibraryItems(it)
            }
        }

        with(sharedViewModel) {
            searchEvent.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is MainSharedEventForSearch.UpdateSearchItem -> {
                        viewModel.modifyKakaoItem(event.item)
                        Log.d("SearchFragment", "#choco5732 event아이템의 정체는 : ${event.item}")
                        recyclerViewAdpater.notifyDataSetChanged()
                    }
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
        Log.d(
            "SearchFragment",
            "#choco5732 searchKeyword: ${binding.etSearchKeyword.text.toString()}"
        )
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
        val responseData = RetrofitClient.search.getKakao(param = param)

        val item = responseData.documents

        item?.forEach {
            viewModel.addSearchItem(
                Kakao(
                    thumbnail_url = it.thumbnailUrl,
                    displaySiteName = it.displaySitename,
                    dateTime = it.datetime,
                    isAdd = false
                )
            )
        }
    }
}