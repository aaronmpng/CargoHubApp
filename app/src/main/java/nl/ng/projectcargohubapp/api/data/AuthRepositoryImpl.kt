package nl.ng.projectcargohubapp.api.data

import android.content.SharedPreferences
import android.util.Log
import com.auth0.android.jwt.JWT
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.api.domain.AuthRepository
import nl.ng.projectcargohubapp.api.domain.AuthRequest
import nl.ng.projectcargohubapp.api.domain.AuthResult
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.strings.Strings
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResult<String> {

        return try {
            val response = api.signIn(
                request = AuthRequest(
                    email = email,
                    password = password
                )
            )
            prefs.edit()
                .putString(Strings.jwt, response.accessToken)
                .apply()

            val token = prefs.getString(Strings.jwt, null) ?: return AuthResult.Unauthorized()
            AuthResult.Authorized(token)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun getTrips(date: String): List<Items> {
        var list: List<Items> = emptyList()

        try {
            val token: String = MainViewModel.data
            val jwt: JWT = JWT(token)
            val driverID: String? =
                jwt.getClaim(Strings.driverId).asString()

            if (driverID != null) {
                val response = api.getTrips(
                    accessToken = "${Strings.Bearer} $token",
                    DriverID = driverID,
                    date = date
                )
                list = response
            } else {
                Log.e(Strings.getTrips, Strings.driverIdNull)
            }
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.getTrips, "${Strings.unauthorized}: ${e.message()}")
            } else {
                Log.e(Strings.getTrips, "${Strings.httpException}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.getTrips, "${Strings.unexpectedException}: ${e.message}")
        }
        return list
    }

    override suspend fun getAirwayBills(orderID: String): List<AirwayBillsItems> {
        var list: List<AirwayBillsItems> = listOf()

        try {
            val token = prefs.getString(Strings.jwt, null)
            val response = api.getAirwayBills(
                orderID = orderID,
                accessToken = " ${Strings.Bearer} ".plus(token)
            )
            list = response

        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.getAirwayBills, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.getAirwayBills, "${Strings.unexpectedException}: ${e.message}")
        }
        return list
    }

    override suspend fun getOrders(): List<OrderItems> {
        var orderlist: List<OrderItems> = listOf()
        try {
            val token = prefs.getString(Strings.jwt, null)
            val response = api.getOrders(
                accessToken = "${Strings.Bearer} ".plus(token)
            )
            orderlist = response
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.getOrders, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.getOrders, "${Strings.unexpectedException}: ${e.message}")
        }
        return orderlist
    }

    override suspend fun updateOrderStatus(orderID: String, status: String) {
        try {
            val token = prefs.getString(Strings.jwt, null)
            val response = api.updateStatus(
                orderID = orderID,
                status = status,
                accessToken = "${Strings.Bearer} ".plus(token)
            )
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.updateOrderStatus, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.updateOrderStatus, "${Strings.unexpectedException}: ${e.message}")
        }
    }

    override suspend fun updateTripStatus(BookingID: String, Status: String) {
        try {
            val token = prefs.getString(Strings.jwt, null)
            val response = api.updateTripStatus(
                bookingID = BookingID,
                status = Status,
                accessToken = "${Strings.Bearer} ".plus(token)
            )
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.updateTripStatus, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.updateTripStatus, "${Strings.unexpectedException}: ${e.message}")
        }
    }

    override suspend fun postImage(jpegByteArray: ByteArray, type: String, id: String) {
        try {
            val token = prefs.getString(Strings.jwt, null)
            val requestBody = RequestBody.create(Strings.imageJpeg.toMediaTypeOrNull(), jpegByteArray)
            val response = api.postImage(
                requestBody = requestBody,
                type = type,
                id = id,
                accessToken = "${Strings.Bearer} ".plus(token)
            )
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.postImage, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.postImage, "${Strings.unexpectedException}: ${e.message}")
        }
    }

    override suspend fun updateLocation(bookingID: String, lat: String, long: String) {
        try {
            val token = prefs.getString(Strings.jwt, null)
            val response = api.updateLocation(
                bookingID = bookingID,
                lat = lat,
                long = long,
                accessToken = "${Strings.Bearer} ".plus(token)
            )
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Log.e(Strings.updateLocation, "${Strings.unauthorized}: ${e.message()}")
            }
        } catch (e: Exception) {
            Log.e(Strings.updateLocation, "${Strings.unexpectedException}: ${e.message}")
        }
    }
}
