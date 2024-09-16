package com.rishabh.kotlinexample.RxJavaExampple

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface RxJavaApiService {
    @FormUrlEncoded
    @POST("api/user/winning_history")
    fun getWinningHistory(
        @Header("Authorization") authorization: String,
        @Field("user_id") user_id: String,
        @Field("type") type: String
    ): Observable<WinningHistoryResponse>
}