package com.android.personal_project_retrofit.presentation.search

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(private val spanCount: Int, private val space: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount + 1      // 1부터 시작

        // 첫 행 제외하고 상단 여백 추가
        if (position >= spanCount) {
            outRect.top = space
        }
        outRect.bottom = space

        // 열별 좌,우 여백 설정

        if (column == 1 ) {
            outRect.left = space
            outRect.right = space/2
        }

        if (column != 1) {
            outRect.left = space/2
            outRect.right = space
        }
    }
}

