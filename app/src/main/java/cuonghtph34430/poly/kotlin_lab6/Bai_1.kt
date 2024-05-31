package cuonghtph34430.poly.kotlin_lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class Bai_1 : ComponentActivity() {
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

    @Composable
    fun MovieItem(movie: Movie) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .width(175.dp)
                .height(330.dp)
        ) {
            Column {
                AsyncImage(
                    model = movie.posterUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .height(255.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Năm phát hành: ${movie.year}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

    @Composable
    fun MovieScreen(movies: List<Movie>) {
        LazyRow(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movies.size) { index ->
                MovieItem(movie = movies[index])
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewMovieScreen() {
        MovieScreen(movies = Movie.getSampleMovies())
    }
}
