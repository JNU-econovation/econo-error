package com.example.project.ui.filter

data class FilterUiState(
    val filterInfos: List<filterInfo> = listOf<filterInfo>()
)

data class filterInfo(
    val filterId: Int=0,
    val filterName: String="",
    var filterColor: String ="",
    val filterCategory: String=""
)

enum class FilterCategory {
    PUBLIC, GROUP, PRIVATE
}
