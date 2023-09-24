package com.android.personal_project_kakaobank_a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.android.personal_project_kakaobank_a.adapter.ViewPagerAdapter
import com.android.personal_project_kakaobank_a.data.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.ActivityMainBinding
import com.android.personal_project_kakaobank_a.retrofit.NetworkClient
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

    }

    private fun initView() = with(binding) {
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(adapter.getTitle(position))
        }.attach()

    }

    fun addBookmarkItem(item: KakaoModel) {
        val fragment = adapter.getFragment(1) as? LibraryFragment
        fragment?.addItem(item)
    }
}