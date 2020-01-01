package info.eniuy.horoscope

import com.google.gson.JsonObject

data class HoroscopeData (
        val requestUrl: String,
        val horoscope: JsonObject
)

data class DailyContents (
        val content: String,
        val item: String,
        val money: Int,
        val total: Int,
        val job: Int,
        val color: String,
        val love: Int,
        val rank: Int,
        val sign: String
)