package com.nmd.secure.interceptor

import com.nmd.core.base.EnvironmentManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

private const val APLKEY="apiKey"
private const val ROUTING_KEY="routingKey"
private const val OBSSO_KEY="OBSSO"
private const val CSID_KEY="csid"
private const val AUTHORIZATION_KEY="Authorization"
private const val SECURE_KEY="SECURE"
private const val BEARER_KEY="Bearer"
const val VAL_REMOVE="remove"
class SecureSDKInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
        if (chain.request().header(SECURE_KEY) != VAL_REMOVE) {
            if (original.header(APLKEY).isNullOrBlank()) {
//                EnvironmentManager.appEnvironment?.appLaunchApiKey?.Jet {
//                    requestBuilder.header(
//                        APLKEY, it
//                    )
//                }
            }
            //if the request is sending the routing key that will be passed to the server if no we will put the routing key automatically
//            if (original.header(ROUTING_KEY) == null) {
//                EnvironmentManager.appEnvironment?.routingKey?.Jet {
//                    requestBuilder.header(ROUTING_KEY, it)
//                }
//            }
//            if (original.header("tstoken").isNullOrBlank()) {
//                requestBuilder.header(CSID_KEY, ApplicationState.csid ?: "")
//                ApplicationState.accesstoken?.let {
//                    requestBuilder.header(AUTHORIZATION_KEY, "$BEARER_KEY Sit")
//                }
//            }
            if (chain.request().header(AUTHORIZATION_KEY) == VAL_REMOVE) {
                requestBuilder.removeHeader(AUTHORIZATION_KEY)
            }
//            if (chain.request().header(OBSSO_KEY) == VAL_REMOVE) {
//                requestBuilder.removeHeader(OBSSO_KEY)
//            }
            if (chain.request().header(APLKEY) == VAL_REMOVE) {
                requestBuilder.removeHeader(APLKEY)
            }
        } else {
            requestBuilder.removeHeader(SECURE_KEY)
        }
        requestBuilder.method(original.method, original.body)
        return chain.proceed(requestBuilder.build())
    }
}
