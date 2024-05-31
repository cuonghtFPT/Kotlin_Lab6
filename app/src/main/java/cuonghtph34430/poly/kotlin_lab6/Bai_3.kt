package cuonghtph34430.poly.kotlin_lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.text.font.FontStyle
import kotlin.random.Random

class Bai_3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaSeatBookingScreen(
                createTheaterSeating(
                    totalRows = 12,
                    totalSeatsPerRow = 9,
                    aislePositionInRow = 4,
                    aislePositionInColumn = 5
                ), totalSeatsPerRow = 9
            )
        }
    }

    @Composable
    fun SeatComposable(seat: Seat, clickable: Boolean = true) {
        var status by remember { mutableStateOf(seat.status) }
        val backgroundColor = when (status) {
            SeatStatus.EMPTY -> Color.LightGray.copy(alpha = 0.5f)
            SeatStatus.SELECTED -> Color(0xFFFFA500)
            SeatStatus.BOOKED -> Color.Gray
            SeatStatus.AISLE -> Color.Transparent
        }
        val borderModifier = if (status != SeatStatus.AISLE) {
            Modifier.border(
                BorderStroke(1.dp, Color.DarkGray.copy(alpha = 0.8f)),
                shape = RoundedCornerShape(8.dp)
            )
        } else Modifier

        Box(
            modifier = Modifier
                .padding(2.dp)
                .size(width = 35.dp, height = 30.dp)
                .then(borderModifier)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .padding(if (seat.status != SeatStatus.AISLE) 3.dp else 0.dp)
                .clickable(enabled = clickable && (status == SeatStatus.EMPTY || status == SeatStatus.SELECTED)) {
                    status = if (status == SeatStatus.EMPTY) SeatStatus.SELECTED else SeatStatus.EMPTY
                },
            contentAlignment = Alignment.Center
        ) {
            if (seat.status != SeatStatus.AISLE) {
                Text(
                    text = "${seat.row}${seat.number}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewSeat() {
        Row {
            SeatComposable(Seat('X', 1, SeatStatus.EMPTY))
            SeatComposable(Seat('Y', 1, SeatStatus.SELECTED))
            SeatComposable(Seat('Z', 1, SeatStatus.BOOKED))
        }
    }

    @Composable
    fun CinemaSeatBookingScreen(seats: List<Seat>, totalSeatsPerRow: Int) {
        val textModifier = Modifier.padding(end = 16.dp, start = 4.dp)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(12.dp)) {
            Text(
                "Screen",
                modifier = Modifier.padding(16.dp),
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(totalSeatsPerRow)) {
                items(seats.size) { index ->
                    SeatComposable(seat = seats[index])
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                val exampleEmptySeat = remember { Seat('X', 1, SeatStatus.EMPTY) }
                val exampleSelectedSeat = remember { Seat('Y', 1, SeatStatus.SELECTED) }
                val exampleBookedSeat = remember { Seat('Z', 1, SeatStatus.BOOKED) }
                SeatComposable(exampleEmptySeat, false)
                Text(
                    text = "Available",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = textModifier
                )
                SeatComposable(exampleSelectedSeat, false)
                Text(
                    text = "Selected",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = textModifier
                )
                SeatComposable(exampleBookedSeat, false)
                Text(
                    text = "Booked",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = textModifier
                )
            }
        }
    }

    @Preview
    @Composable
    fun PreviewCinemaSeatBooking() {
        CinemaSeatBookingScreen(
            createTheaterSeating(
                totalRows = 12,
                totalSeatsPerRow = 9,
                aislePositionInRow = 4,
                aislePositionInColumn = 5
            ), totalSeatsPerRow = 9
        )
    }

    fun createTheaterSeating(
        totalRows: Int,
        totalSeatsPerRow: Int,
        aislePositionInRow: Int,
        aislePositionInColumn: Int
    ): List<Seat> {
        val seats = mutableListOf<Seat>()
        for (rowIndex in 0 until totalRows) {
            for (seatIndex in 1..totalSeatsPerRow) {
                val adjustedRowIndex = if (rowIndex >= aislePositionInRow) rowIndex - 1 else rowIndex
                val adjustedSeatIndex = if (seatIndex >= aislePositionInColumn) seatIndex - 1 else seatIndex
                val isAisleRow = rowIndex == aislePositionInRow
                val isAisleColumn = seatIndex == aislePositionInColumn
                val status = when {
                    isAisleRow || isAisleColumn -> SeatStatus.AISLE
                    else -> if (Random.nextInt(0, 99) % 2 == 0) SeatStatus.BOOKED else SeatStatus.EMPTY
                }
                seats.add(
                    Seat(
                        row = 'A' + adjustedRowIndex,
                        number = adjustedSeatIndex,
                        status = status
                    )
                )
            }
        }
        return seats
    }
}

