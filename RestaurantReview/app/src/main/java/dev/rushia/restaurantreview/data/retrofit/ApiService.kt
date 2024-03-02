package dev.rushia.restaurantreview.data.retrofit

import dev.rushia.restaurantreview.data.response.PostReviewResponse
import dev.rushia.restaurantreview.data.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("detail/{id}")
    fun getRestraurant(
        @Path("id") id: String
    ): Call<RestaurantResponse>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): Call<PostReviewResponse>
}
