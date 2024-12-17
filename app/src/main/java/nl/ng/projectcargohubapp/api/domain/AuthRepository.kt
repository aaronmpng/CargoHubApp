package nl.ng.projectcargohubapp.api.domain

import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems

interface AuthRepository {
    suspend fun signIn(email: String, password: String): AuthResult<String>
    suspend fun getTrips(date: String): List<Items>
    suspend fun getOrders(): List<OrderItems>
    suspend fun getAirwayBills(orderID: String): List<AirwayBillsItems>
    suspend fun updateOrderStatus(orderID: String, status: String)
    suspend fun updateTripStatus(BookingID: String, Status: String)
    suspend fun postImage(jpegByteArray: ByteArray, type: String, bookingID: String)
    suspend fun updateLocation(bookingID: String, lat: String, long: String)
}