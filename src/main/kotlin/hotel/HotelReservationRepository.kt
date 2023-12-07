package hotel

import java.util.concurrent.atomic.AtomicInteger

object HotelReservationRepository {
    var previousId: AtomicInteger = AtomicInteger()
    val hotelReservationList: MutableList<HotelReservationInput> = mutableListOf()
}