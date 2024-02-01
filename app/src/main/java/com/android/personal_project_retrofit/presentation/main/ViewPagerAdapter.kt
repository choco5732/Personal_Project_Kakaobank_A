package com.android.personal_project_retrofit.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.personal_project_retrofit.presentation.library.LibraryFragment
import com.android.personal_project_retrofit.presentation.search.SearchFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init {
        fragments.add(
            MainTabs(SearchFragment.newInstance(),"Search")
        )
        fragments.add(
            MainTabs(LibraryFragment.newInstance(),"Library")
        )
    }
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): String {
        return fragments[position].title
    }

}