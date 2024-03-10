package com.example.myprapp.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myprapp.R
import com.example.myprapp.databinding.ActivityMainBinding
import com.example.myprapp.dto.Post
import com.example.myprapp.databinding.CardPostBinding
import com.example.myprapp.viewmodel.PostViewModel

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Курско-магнитный студент ГБПОУ ВО БТПИТ и выпускник Курской магнитной аномалии 2 степени.",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → https://yandex.ru/",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { posts ->
            binding.container?.removeAllViews()
            posts.map { post ->
                CardPostBinding.inflate(layoutInflater, binding.container, true).apply {
                    textView.text = post.author
                    textView2.text = post.published
                    textView3.text = post.content
                    if (post.likedByMe) {
                        imageButton.setImageResource(R.drawable.like)
                    }

                    imageButton.setOnClickListener {
                        post.likedByMe = !post.likedByMe
                        imageButton.setImageResource(
                            if (post.likedByMe) R.drawable.like else R.drawable.like_no_activity
                        )
                        if (post.likedByMe) post.likes+=1 else post.likes--
                        textView4.text = AmogusAmi(post.likes) //post.likes.toString()
                    }

                    imageButton2.setOnClickListener {
                        var k : Int = (textView5.text.toString()).toInt()
                        textView5.text  = (k + 1).toString()
                    }
                }.root
            }
        }
    }
    fun AmogusAmi(like : Int) : String {

        var tempString = "EROR"
        if (like<1000)
            tempString = like.toString()
        if (like >= 1000)
            tempString = (like / 1000).toString() + "." + ((like % 1000) / 100).toString() + "K"
        if(like >= 10_000)
            tempString = (like / 1000).toString() + "K"
        if(like >= 1_000_000)
            tempString = (like / 1_000_000).toString() + "." + ((like % 1_000_000) / 100_000).toString() + "M"

        return tempString
    }
}