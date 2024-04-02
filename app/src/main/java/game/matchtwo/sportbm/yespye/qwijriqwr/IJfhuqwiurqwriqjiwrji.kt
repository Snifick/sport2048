package game.matchtwo.sportbm.yespye.qwijriqwr

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class IJfhuqwiurqwriqjiwrji(private val rhqwirwqrjqwijr: AppCompatActivity, private val qhwiruqwhurhuqwrhuo: WebView, private val ruhqwhrqirjiw: String?, val rhqwirjqir: LottieAnimationView) {
    val qwuruigyqwuriqr2 = IJuiqwjriqjriwq(rhqwirwqrjqwijr, qhwiruqwhurhuqwrhuo, rhqwirjqir)
    val fhqwruiuqwhuirq = object : WebChromeClient(){}

    fun qhuqwurhuqwrui() {
        qhwiruqwhurhuqwrhuo.webChromeClient = fhqwruiuqwhuirq
        qhwiruqwhurhuqwrhuo.webViewClient = qwuruigyqwuriqr2
        jiqwhuiqwrihuiquiwruqwr2(qhwiruqwhurhuqwrhuo)
    }
}
