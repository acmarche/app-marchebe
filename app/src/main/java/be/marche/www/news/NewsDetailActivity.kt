package be.marche.www.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import be.marche.www.model.News
import be.marche.www.news.ui.NewsShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val news = News(
            10,
            "Iron Man",
            "",
            "",
            "",
            "",
            "",
            ""
        )
        setContent {
            NewsShow(news)
        }
    }
}
