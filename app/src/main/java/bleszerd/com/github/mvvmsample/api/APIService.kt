package bleszerd.com.github.mvvmsample.api

import bleszerd.com.github.mvvmsample.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): User
}