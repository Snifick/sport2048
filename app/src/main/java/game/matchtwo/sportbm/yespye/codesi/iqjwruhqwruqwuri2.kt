package game.matchtwo.sportbm.yespye.codesi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import com.airbnb.lottie.LottieAnimationView
import com.onesignal.OneSignal
import game.matchtwo.sportbm.R
import game.matchtwo.sportbm.yespye.qwijriqwr.IJfhuqwiurqwriqjiwrji
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class iqjwruhqwruqwuri2 : AppCompatActivity() {
    private lateinit var joqwirqwirqwr2fjiaw: WebView
    private lateinit var iqwrui2r2i: IJfhuqwiurqwriqjiwrji
    private lateinit var iqwurqwru: LottieAnimationView

    override fun onCreate(iqwjrhqwr: Bundle?) {
        super.onCreate(iqwjrhqwr)
        setContentView(R.layout.iqwjroqwrji2)
        joqwirqwirqwr2fjiaw = findViewById(R.id.ijwqrhuqwuf)
        val oqwieiui2 = intent.getStringExtra("LLL_EXTRA")
        iqwrui2r2i = IJfhuqwiurqwriqjiwrji(this@iqjwruhqwruqwuri2, joqwirqwirqwr2fjiaw, oqwieiui2, iqwurqwru)
        joqwirqwirqwr2fjiaw.webChromeClient = iqwrui2r2i.fhqwruiuqwhuirq
        joqwirqwirqwr2fjiaw.webViewClient = iqwrui2r2i.qwuruigyqwuriqr2
        iqwrui2r2i.qhuqwurhuqwrui()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            OneSignal.Notifications.requestPermission(true)
        }
        joqwirqwirqwr2fjiaw.loadUrl(iqwrui2r2i.qwuruigyqwuriqr2.getLastOpenedUrl(oqwieiui2.toString()).toString())
        joqwirqwirqwr2fjiaw.visibility = View.VISIBLE
    }
    override fun onBackPressed() {
        if (joqwirqwirqwr2fjiaw.canGoBack()) {
            joqwirqwirqwr2fjiaw.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
