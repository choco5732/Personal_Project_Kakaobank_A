package com.android.personal_project_retrofit.presentation.library

sealed interface LibraryEvent {
    data class RemoveToast(
        val text: String
    ) : LibraryEvent
}