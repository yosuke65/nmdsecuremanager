package com.nmd.secure.interceptor

import android.os.Parcel
import android.os.Parcelable
import com.nmd.core.base.EnvironmentManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

private const val ROUTING_KEY="routingKey"
private const val AUTHORIZATION_KEY= "Authorization"
private const val BEARER_KEY= "Bearer"

class SecureGraphQLSDKInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        //if the request is sending the routing key that will be passed to the server if no we will put the routing key automatically.
//        if (original.header(ROUTING_KEY) == null) {
//            EnvironmentManager.appEnvironment?.routingKey?.let {
//                requestBuilder.header(ROUTING_KEY, it)
//            }
//        }
//        if (original.header(AUTHORIZATION_KEY) == null) {
//            ApplicationState.accesstoken?.let {
//                requestBuilder.header(AUTHORIZATION_KEY, "$BEARER_KEY $it")
//            }
//        }
        requestBuilder.method(original.method, original.body)
        return chain.proceed(requestBuilder.build())
    }
}
