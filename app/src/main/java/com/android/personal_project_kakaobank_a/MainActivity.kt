package com.android.personal_project_kakaobank_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.android.personal_project_kakaobank_a.adapter.ViewPagerAdapter
import com.android.personal_project_kakaobank_a.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ViewPagerAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

        communicateNetWork(setUpKakaoParameter("google"))
    }

    private fun initView() = with(binding) {
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(adapter.getTitle(position))
        }.attach()

    }

    private fun setUpKakaoParameter(query: String): HashMap<String, String> {
        val Authorization =
            "KakaoAK 8a8304bda25a4c8d282da26ac2f06e54"

        return hashMapOf(
            "Authorization" to Authorization,
            "query" to query,
            "sort" to "accuracy",
            "page" to "1",
            "size" to "5"
        )
    }

    private fun communicateNetWork(param: HashMap<String, String>) = lifecycleScope.launch() {
        val responseData = NetworkClient.kakaoNetWork.getKakao(param)
        Log.d("Parsing kakao ::", responseData.toString())

//        val adapter = IconSpinnerAdapter(binding.spinnerViewGoo)
//        items = responseData.response.dustBody.dustItem!!
//
//        val goo = ArrayList<String>()
//        items.forEach {
//            Log.d("add Item :", it.stationName)
//            goo.add(it.stationName)
//        }
//
//        runOnUiThread {
//            binding.spinnerViewGoo.setItems(goo)
//        }

    }

}