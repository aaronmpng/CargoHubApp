package nl.ng.projectcargohubapp.api.data

import nl.ng.projectcargohubapp.api.domain.AuthRequest
import nl.ng.projectcargohubapp.api.domain.TokenResponse
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthApi {

    @GET("Trips/DriverTrips")
    suspend fun getTrips(
        @Query("DriverID") DriverID: String,
        @Query("date") date: String,
        @Header("Authorization") accessToken: String
    ): List<Items>

    @GET("Orders")
    suspend fun getOrders(
        @Header("Authorization") accessToken: String
    ): List<OrderItems>

    @GET("AirwayBills/order")
    suspend fun getAirwayBills(
        @Query("orderID") orderID: String,
        @Header("Authorization") accessToken: String,
    ): List<AirwayBillsItems>

    @PUT("Orders/{orderID}/{status}")
    suspend fun updateStatus(
        @Path("orderID") orderID: String,
        @Path("status") status: String,
        @Header("Authorization") accessToken: String
    )

    @PUT("Trips/Status")
    suspend fun updateTripStatus(
        @Query("BookingID") bookingID: String,
        @Query("Status") status: String,
        @Header("Authorization") accessToken: String
    )

    @POST("Users/Login")
    suspend fun signIn(
        @Body request: AuthRequest
    ): TokenResponse

    @POST("Image")
    suspend fun postImage(
        @Body requestBody: RequestBody,
        @Query("type") type: String,
        @Query("id") id: String,
        @Header("Authorization") accessToken: String
    )

    @PUT("Trips/GPS")
    suspend fun updateLocation(
        @Query("BookingID") bookingID: String,
        @Query("lat") lat: String,
        @Query("long") long: String,
        @Header("Authorization") accessToken: String
    )

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    )
}