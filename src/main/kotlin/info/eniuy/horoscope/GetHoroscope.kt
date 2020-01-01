package info.eniuy.horoscope

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import kotlin.system.exitProcess
import kotlin.text.Charsets.UTF_8
import com.google.gson.Gson
import com.google.gson.JsonObject

fun getHoroscope(formatDate: String): JsonObject {
    //create gson
    val gson = Gson()
    //set url horoscope of the day
    val requestUrl = "http://api.jugemkey.jp/api/horoscope/free/"
    try {
        //create url
        val url = URL(requestUrl)
        //create url connection
        val urlConnection = url.openConnection() as HttpURLConnection
        //set request method
        urlConnection.requestMethod = "GET"
        try {
            //connect url
            urlConnection.connect()
            println("connect to: $requestUrl")
        } catch (e: SocketTimeoutException) {
            println("connect timed out: $requestUrl")
            exitProcess(1)
        }
        println("responseCode: ${urlConnection.responseCode}")
        //set buffered reader encode utf-8
        val bufferedReader = BufferedReader(
                InputStreamReader(
                        urlConnection.inputStream,
                        UTF_8
                )
        )
        val results = StringBuilder()
        //create results string
        bufferedReader.forEachLine { results.append(it) }
        //set return object
        val resultsObject = gson.fromJson(results.toString(), JsonObject::class.java)
        //add request url property
        resultsObject.addProperty("requestUrl",requestUrl)
        // return json object
        return resultsObject
    } catch (e: IOException) {
        println("url request error : $requestUrl")
        exitProcess(1)
    }
}