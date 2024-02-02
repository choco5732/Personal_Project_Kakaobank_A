package com.android.personal_project_retrofit.presentation.search

import androidx.fragment.app.FragmentActivity

sealed interface SearchEvent {
    data class LoadData(
        val type: String,
        val name: String
    ) : SearchEvent
    data class SaveData(
        val type: String,
        val name: String
    ) : SearchEvent
}