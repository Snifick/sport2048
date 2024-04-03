package game.matchtwo.sportbm.yespye.codesi

import android.content.Context

class Prefs(context: Context) {

    private val name = "${context.packageName}_prefs"
    private val linka = "lastOpenedUrl"
    var l = ""
    val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun saveUrl(url: String) {
        if(l == ""){
            l = getUrl(url).toString()
            prefs.edit().putString(linka, url).apply()
        }
    }

    fun getUrl(url: String): String? {
        return prefs.getString(linka, url)
    }
}
