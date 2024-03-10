package com.example.myprapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myprapp.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var posts = listOf(
        Post(
            id = 2,
            author = "Студент КМА",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
            published = "18 сентября в 10:12",
            likedByMe = false
        ),
        Post(
            id = 1,
            author = "Студент БТПИТ - подаван",
            content = "Шутка № 33 (Сталкер в аду)\\nКто рассказывает: одиночки и чистонебовцы\\n\\nПопал как-то сталкер на экскурсию в ад.\\nЕму показывает черт муки, которые грешники терпят: (с восхищением) Кипящая смола! ПЛЕТКИ! Раскаленные УГЛИ! Огонь кругоммм… Вопли. Серой воняет…\\nСталкер посмотрел на все это и говорит чертям:\\n— Ну что, чуваки (спокойно). (Вздыхает) Тепло у вас тут. СУХО. Эх… Да и развлечений у вас тут побольше, чем у нас, на Болотах (С тоном скепсиса). Так что я пожалуй… тут останусь (улыбается).",
            published = "21 мая в 18:36",
            likedByMe = false
        )
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        data.value = posts
    }
}