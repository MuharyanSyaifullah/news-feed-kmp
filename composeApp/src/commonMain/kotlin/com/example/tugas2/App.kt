package com.example.tugas2

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    val vm = remember { NewsViewModel() }

    val newsList by vm.newsList.collectAsState()
    val readCount by vm.readCount.collectAsState()
    val detail by vm.detail.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text("News Feed Simulator", style = MaterialTheme.typography.titleLarge)
        Text("Read Count: $readCount")

        Spacer(Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Tech", "Sports", "Politics").forEach { cat ->
                Button(onClick = { vm.setCategory(cat) }) {
                    Text(cat)
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(Modifier.weight(1f)) {
            items(newsList) { news ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            vm.markAsRead()
                            vm.openDetail(news.id)
                        }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(news.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        Text(news.content)
                    }
                }
            }
        }

        detail?.let { d ->
            Spacer(Modifier.height(10.dp))
            Divider()
            Spacer(Modifier.height(10.dp))
            Text("Detail:", style = MaterialTheme.typography.titleMedium)
            Text(d)
        }
    }
}
