package info.eniuy.horoscope

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject

fun buildHoroscopeHtml(formatDate: String,resultHoroscope: JsonObject): String{
    //create gson
    val gson = Gson()
    val resultHoroscopeObject = gson.fromJson(resultHoroscope, HoroscopeData::class.java)
    val horoscopeHtml = StringBuilder("<div id='horoscope'>")
    val horoscopeObject = resultHoroscopeObject.horoscope
    for (i in horoscopeObject.keySet().toSortedSet()){
        when (i) {
            formatDate -> horoscopeHtml.append("<p>** $i **</p>")
            else -> horoscopeHtml.append("<p>$i</p>")
        }
        val dailyHoroscope = horoscopeObject.get(i) as JsonArray
        for (j in dailyHoroscope.iterator()){
            val dailyContents = gson.fromJson(j, DailyContents::class.java)
            val content = dailyContents.content
            val item = dailyContents.item
            val money = dailyContents.money
            val total = dailyContents.total
            val job = dailyContents.job
            val color = dailyContents.color
            val love = dailyContents.love
            val rank = dailyContents.rank
            val sign = dailyContents.sign
            horoscopeHtml.append("<p>$sign ")
            when (rank){
                1 -> horoscopeHtml.append("$rank st place</p>")
                2 -> horoscopeHtml.append("$rank nd place</p>")
                3 -> horoscopeHtml.append("$rank rd place</p>")
                in 4..12 -> horoscopeHtml.append("$rank th place</p>")
                else -> horoscopeHtml.append("error </p>")
            }
            horoscopeHtml.append("<p>General fortune: ")
            when (total){
                1 -> horoscopeHtml.append("@</p>")
                2 -> horoscopeHtml.append("@@</p>")
                3 -> horoscopeHtml.append("@@@</p>")
                4 -> horoscopeHtml.append("@@@@</p>")
                5 -> horoscopeHtml.append("@@@@@</p>")
                else -> horoscopeHtml.append("error </p>")
            }
            horoscopeHtml.append("<p>Economic fortune: ")
            when (money){
                1 -> horoscopeHtml.append("$</p>")
                2 -> horoscopeHtml.append("$$</p>")
                3 -> horoscopeHtml.append("$$$</p>")
                4 -> horoscopeHtml.append("$$$$</p>")
                5 -> horoscopeHtml.append("$$$$$</p>")
                else -> horoscopeHtml.append("error </p>")
            }
            horoscopeHtml.append("<p>Work luck: ")
            when (job){
                1 -> horoscopeHtml.append("#</p>")
                2 -> horoscopeHtml.append("##</p>")
                3 -> horoscopeHtml.append("###</p>")
                4 -> horoscopeHtml.append("####</p>")
                5 -> horoscopeHtml.append("#####</p>")
                else -> horoscopeHtml.append("error </p>")
            }
            horoscopeHtml.append("<p>Love luck: ")
            when (love){
                1 -> horoscopeHtml.append("!</p>")
                2 -> horoscopeHtml.append("!!</p>")
                3 -> horoscopeHtml.append("!!!</p>")
                4 -> horoscopeHtml.append("!!!!</p>")
                5 -> horoscopeHtml.append("!!!!!</p>")
                else -> horoscopeHtml.append("error </p>")
            }
            horoscopeHtml.append("<p>Lucky color: $color</p>")
            horoscopeHtml.append("<p>Lucky item: $item</p>")
            horoscopeHtml.append("<p>$content</p>")
        }
    }
    horoscopeHtml.append("</div>")
    return horoscopeHtml.toString()
}
