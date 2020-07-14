package com.bankwithmint.mocktretrofit


import io.reactivex.Single


class JsonRepository(private val api: ApiPlaceholer) {

   fun observePosts(): Single<List<Post>> = api.getPosts()

}