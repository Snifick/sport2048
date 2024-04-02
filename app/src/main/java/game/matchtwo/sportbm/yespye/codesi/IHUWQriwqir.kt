package game.matchtwo.sportbm.yespye.codesi

import android.content.Context

class IHUWQriwqir(context: Context) {

    private val hfjqwihfuqjwrjiqw = "${context.packageName}_prefs"
    private val qiwjriqwrqjwirji = "lastOpenedUrl"
    var l = ""
    val huqwirjiqwrjiqjr = context.getSharedPreferences(hfjqwihfuqjwrjiqw, Context.MODE_PRIVATE)

    fun uqwhijrqwrqwrqwij(url: String) {
        if(l == ""){
            l = qhuwriqwrihiqrjji2(url).toString()
            huqwirjiqwrjiqjr.edit().putString(qiwjriqwrqjwirji, url).apply()
        }
    }

    fun qhuwriqwrihiqrjji2(url: String): String? {
        return huqwirjiqwrjiqjr.getString(qiwjriqwrqjwirji, url)
    }
}
