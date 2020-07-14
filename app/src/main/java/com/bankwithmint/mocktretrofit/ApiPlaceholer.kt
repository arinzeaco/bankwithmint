package com.bankwithmint.mocktretrofit

import io.reactivex.Single
import retrofit2.http.GET

interface ApiPlaceholer {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}