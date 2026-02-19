package com.example.tugas2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {

    private val categories = listOf("Tech", "Sports", "Politics")

    fun newsStream(): Flow<News> = flow {
        var count = 1

        while (true) {
            delay(2000)

            val category = categories[count % categories.size]

            val now = System.currentTimeMillis()
            println("EMIT at: $now | count=$count | category=$category")

            emit(
                News(
                    id = count.toString(),
                    title = "Breaking News $count",
                    category = category,
                    content = "Ini adalah konten berita ke-$count pada kategori $category.",
                    timestamp = now
                )
            )

            count++
        }
    }

    suspend fun fetchNewsDetail(id: String): News {
        delay(1000)
        val now = System.currentTimeMillis()
        return News(
            id = id,
            title = "Detail News $id",
            category = "Tech",
            content = "Ini adalah detail lengkap untuk berita id=$id (simulasi async).",
            timestamp = now
        )
    }
}
