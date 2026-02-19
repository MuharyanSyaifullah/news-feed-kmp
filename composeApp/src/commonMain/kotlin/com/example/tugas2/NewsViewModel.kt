package com.example.tugas2

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsViewModel(
    private val repo: NewsRepository = NewsRepository()
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _selectedCategory = MutableStateFlow("Tech")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList.asStateFlow()

    private val _detail = MutableStateFlow<String?>(null)
    val detail: StateFlow<String?> = _detail.asStateFlow()

    init {
        startStreaming()
    }

    private fun startStreaming() {
        scope.launch {
            repo.newsStream()
                .filter { it.category == _selectedCategory.value }
                .map { news ->
                    news.copy(title = "[${news.category}] ${news.title}")
                }
                .catch { e ->
                    _detail.value = "Error stream: ${e.message}"
                }
                .collect { news ->
                    _newsList.value = listOf(news) + _newsList.value
                }
        }
    }

    fun setCategory(category: String) {
        _selectedCategory.value = category
        _newsList.value = emptyList()
    }

    fun markAsRead() {
        _readCount.value = _readCount.value + 1
    }

    // Fitur #5: async/await ambil detail
    fun openDetail(id: String) {
        scope.launch {
            val detailNews = async { repo.fetchNewsDetail(id) }.await()
            _detail.value = detailNews.content
        }
    }
}
