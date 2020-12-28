package be.marche.www.news.ui.tests

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import be.marche.www.model.News
import be.marche.www.ui.NetworkImageComponentPicasso
import be.marche.www.R

@Composable
fun ListNewsScreen(newsListLiveData: LiveData<List<News>>, onItemClick: (Int) -> Unit) {
    val personList by newsListLiveData.observeAsState(initial = emptyList())
    if (personList.isEmpty()) {
        LiveDataLoadingComponent3()
    } else {
        LiveDataComponentList3(personList, onItemClick)
    }
}

@Composable
fun LiveDataComponentList3(newsList: List<News>, onItemClick: (Int) -> Unit) {
    // LazyColumn is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecyclerView tries to do as it's more optimized
    // than the VerticalScroller.
    LazyColumn {


        items(items = newsList, itemContent = { news ->
            // Card composable is a predefined composable that is meant to represent the
            // card surface as specified by the Material Design specification. We also
            // configure it to have rounded corners and apply a modifier.

            // You can think of Modifiers as implementations of the decorators pattern that are used to
            // modify the composable that its applied to. In this example, we assign a padding of
            // 16dp to the Card along with specifying it to occupy the entire available width.
            Card(
                shape = RoundedCornerShape(4.dp),
                backgroundColor = Color.White,
                modifier = Modifier.fillParentMaxWidth().padding(8.dp)
                    .clickable(onClick = {
                        onItemClick(news.id)
                    })

            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    news.image?.let { imageUrl ->
                        // Look at the implementation of this composable in ImageActivity to learn
                        // more about its implementation. It uses Picasso to load the imageUrl passed
                        // to it.
                        NetworkImageComponentPicasso(
                            url = imageUrl,
                            modifier = Modifier.fillMaxWidth()
                                .wrapContentHeight(Alignment.CenterVertically)
                        )
                    }
                    val typography = MaterialTheme.typography

                    Text(
                        text = news.intitule,
                        style = typography.h3
                    )
                }
            }
        })
    }
}

@Composable
fun LiveDataLoadingComponent3() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}