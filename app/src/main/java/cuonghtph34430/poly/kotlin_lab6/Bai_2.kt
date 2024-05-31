package cuonghtph34430.poly.kotlin_lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

class Bai_2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieScreen(Movie.getSampleMovies())
        }
    }

    data class Movie(
        val title: String,
        val year: String,
        val posterUrl: String,
        val duration: String,
        val releaseDate: String,
        val genre: String,
        val shortDescription: String
    ) {
        companion object {
            fun getSampleMovies(): List<Movie> = listOf(
                Movie(
                    "Kang Fu Panda 4", "2024",
                    "https://lh4.googleusercontent.com/proxy/0ANH87_RsjcPVN_BPzv_LHYVYwO44rC9-yozjnYZUF2iqF36uvUJgSStqQuTfmcYHgtToTu7vXdqirFhitZH_XP36KbyZ-_qgxI87CX_Md0sg0Lt",
                    "1h 44'", "12/01/2024", "Phim hoạt hình",
                    "\"Kung Fu Panda 4\" tái hợp chú gấu trúc Po và nhóm bạn trong cuộc phiêu lưu mới, đối mặt với thế lực đen tối đe dọa thế giới Kung Fu."
                ),
                Movie(
                    "Hành Tinh Cát - Phần 2", "2024",
                    "https://upload.wikimedia.org/wikipedia/vi/9/94/Dune_2_VN_poster.jpg",
                    "1h 50'", "14/03/2024", "Phim hành động",
                    "\"Hành Tinh Cát Phần 2\" tiếp tục cuộc hành trình kỳ diệu của nhóm phi hành gia trên hành tinh sa mạc, khám phá bí ẩn và đối mặt với nguy hiểm mới."
                ),
                Movie(
                    "Mai", "2023",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTBwxAkLxonf5ByHa2ZNf3Aij7WTAxIlLeCq1rCyX5qHg&s",
                    "1h 35'", "29/12/2023", "Phim tình cảm",
                    "\"Mai\" là câu chuyện về một cô bé Việt Nam trải qua những thách thức, khám phá văn hóa mới và tìm kiếm ước mơ."
                ),
                Movie(
                    "Đào, Phở, Piano", "2024",
                    "https://www.elle.vn/wp-content/uploads/2024/02/28/573479/poster-phim-dao-pho-va-piano.jpg",
                    "1h 45'", "21/01/2024", "Phim tài liệu",
                    "\"Đào, Phở, Piano\" kể về hành trình của một cậu bé Việt trong việc khám phá và chinh phục ước mơ âm nhạc."
                ),
                Movie(
                    "Lật Mặt 7", "2024",
                    "https://www.lofficielvietnam.com/_next/image?url=https%3A%2F%2Fwww.datocms-assets.com%2F56778%2F1712133247-posster-v2.jpg%3Fauto%3Dformat%252Ccompress%26cs%3Dsrgb&w=3840&q=75",
                    "1h 55'", "12/05/2024", "Phim gia đình",
                    "\"Lật Mặt 7\" là câu chuyện hành động căng thẳng với cuộc truy đuổi và âm mưu giữa các nhân vật trong thế giới ngầm."
                ),
                Movie(
                    "Harry Potter", "2022",
                    "https://rukminim2.flixcart.com/image/850/1000/xif0q/poster/z/y/d/small-spos6935-poster-harry-potter-the-deathly-hallows-sl-6935-original-imaghmm5cbcnuhgf.jpeg?q=90&crop=false",
                    "2h 05'", "11/02/2022", "Phim giả tưởng",
                    "\"Harry Potter\" là bộ phim kỳ ảo nổi tiếng kể về cuộc phiêu lưu ma thuật của một cậu bé tên Harry Potter."
                )
            )
        }
    }

    enum class ListType {
        ROW, COLUMN, GRID
    }


    @Composable
    fun MovieItem(movie: Movie, listType: ListType) {
        Card(
            colors = CardDefaults.cardColors(containerColor =
            Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation =
            6.dp),
        ) {
            Column(
                modifier = Modifier.then(getItemSizeModifier(listType))
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.wrapContentHeight().fillMaxWidth()
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,

                    overflow = TextOverflow.Ellipsis

                )
                    BoldValueText(label = "Thời lượng: ", value =
                    movie.duration)
                    BoldValueText(label = "Khởi chiếu: ", value =
                    movie.releaseDate)
                }
            }
        }
    }

    @Composable
    fun MovieScreen(movies: List<Movie>) {
        var listType by remember { mutableStateOf(ListType.ROW) }

        Column {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { listType = ListType.ROW }) {
                    Text("Row")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { listType = ListType.COLUMN }) {
                    Text("Column")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { listType = ListType.GRID }) {
                    Text("Grid")
                }
            }

            when (listType) {
                ListType.ROW -> MovieRow(movies)
                ListType.COLUMN -> MovieColumn(movies)
                ListType.GRID -> MovieGrid(movies)
            }
        }
    }

    @Composable
    fun MovieRow(movies: List<Movie>) {
        LazyRow(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                MovieItem(movie = movies[index], listType = ListType.ROW)
            }
        }
    }

    @Composable
    fun MovieColumn(movies: List<Movie>) {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                MovieColumnItem(movie = movies[index], listType = ListType.COLUMN)
            }
        }
    }

    @Composable
    fun MovieGrid(movies: List<Movie>) {
        val gridState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            state = gridState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(movies.size) { index ->
                MovieItem(movie = movies[index], listType = ListType.GRID)
            }
        }
    }

    @Composable
    fun MovieColumnItem(movie: Movie, listType: ListType) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Row {
                    AsyncImage(
                        model = movie.posterUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .then(getItemSizeModifier(listType))
                            .wrapContentHeight()
                    )
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = movie.title,
                            fontSize = 20.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        BoldValueText("Thời lượng: ", movie.duration)
                        BoldValueText("Khởi chiếu: ", movie.releaseDate)
                        BoldValueText("Thể loại: ", movie.genre)
                        Text(
                            text = "Tóm tắt nội dung",

                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                        )
                        Text(
                            text = movie.shortDescription,
                            fontSize = 14.sp,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun BoldValueText(
        label: String, value: String, style: androidx.compose.ui.text.TextStyle =
        MaterialTheme.typography.bodySmall
    ) {
        Text(buildAnnotatedString {
            append(label)
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(value)
            }
        }, style = style)
    }

    @Composable
    private fun getItemSizeModifier(listType: ListType): Modifier {
        return when (listType) {
            ListType.ROW -> Modifier.width(175.dp)
            ListType.COLUMN -> Modifier.width(130.dp)
            ListType.GRID -> Modifier.fillMaxWidth()
        }
    }
}

