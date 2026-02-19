# News Feed Simulator (Kotlin Multiplatform)

Project ini dibuat untuk memenuhi tugas P2 – Advanced Kotlin, Coroutines, dan Flow.

Aplikasi ini mensimulasikan sistem News Feed menggunakan Kotlin Multiplatform dan memanfaatkan:
- Coroutines
- Flow
- StateFlow
- Async/Await
- Jetpack Compose

---

## 🚀 Fitur

- Flow yang emit berita baru setiap 2 detik
- Filter berita berdasarkan kategori (Tech, Sports, Politics)
- Transformasi tampilan judul menggunakan operator map
- StateFlow untuk menghitung jumlah berita yang telah dibaca
- Pengambilan detail berita secara asynchronous menggunakan async/await

---

## 🧠 Konsep yang Digunakan

### 1️⃣ Kotlin Flow
Digunakan untuk mensimulasikan streaming data berita secara real-time.

```kotlin
repo.newsStream()
    .filter { it.category == selectedCategory }
    .map { it.copy(title = "[${it.category}] ${it.title}") }
```

### 2️⃣ StateFlow
Digunakan untuk menyimpan state jumlah berita yang telah dibaca dan list berita.

### 3️⃣ Coroutines
Digunakan untuk:
- Menjalankan streaming berita
- Mengambil detail berita secara asynchronous

---

## 🛠️ Cara Menjalankan Project

Clone repository:
git clone https://github.com/MuharyanSyaifullah/news-feed-kmp.git

Buka project di Android Studio  
Sync Gradle  
Jalankan target androidApp  

---

## 📂 Struktur Project

commonMain
 ├── App.kt
 ├── News.kt
 ├── NewsRepository.kt
 └── NewsViewModel.kt

---

---

# Dokumentasi
![Screenshot](screenshot.png)

## 👨‍💻 Author

Nama: Muharyan Syaifullah (123140045)
Mata Kuliah: Pemrograman Aplikasi Mobile
Tahun: 2026
