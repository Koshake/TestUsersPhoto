package com.koshake1.testusersphoto.retrofit

import com.koshake1.testusersphoto.URL_KEY
import com.koshake1.testusersphoto.di.BASE_API_URL
import com.koshake1.testusersphoto.di.BASE_API_URL_IMAGE
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.URISyntaxException

class HostSelectionInterceptor(private val preferenceHelper: PreferenceHelper) : Interceptor {

    private var host = HttpUrl.parse(BASE_API_URL)

    fun setHostBaseUrl() {
        host = if (preferenceHelper.isImage(URL_KEY)) {
            HttpUrl.parse(BASE_API_URL_IMAGE)
        } else {
            HttpUrl.parse(BASE_API_URL)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (host != null) {
            var newUrl: HttpUrl? = null
            try {
                newUrl = request.url().newBuilder()
                    .scheme(host!!.scheme())
                    .host(host!!.url().toURI().host)
                    .build()
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            assert(newUrl != null)
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}