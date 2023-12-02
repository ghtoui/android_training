package jp.co.yumemi.droidtraining.model

import jp.co.yumemi.droidtraining.OpenWeatherAPIKey
import okhttp3.Interceptor
import okhttp3.Response

// queryを付与するインターセプター
class CustomInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("appid", OpenWeatherAPIKey.getApiKey())
            .addQueryParameter("lang", "ja")
            .addQueryParameter("units", "metric")
            .build()

        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}

