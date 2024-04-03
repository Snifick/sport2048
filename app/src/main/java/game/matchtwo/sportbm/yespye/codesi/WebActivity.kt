package game.matchtwo.sportbm.yespye.codesi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import com.airbnb.lottie.LottieAnimationView
import com.onesignal.OneSignal
import game.matchtwo.sportbm.R
import game.matchtwo.sportbm.yespye.qwijriqwr.WebSettingsSetup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var webSettingsSetup: WebSettingsSetup

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.iqwjroqwrji2)
        webView = findViewById(R.id.ijwqrhuqwuf)
        val extra = intent.getStringExtra("LLL_EXTRA")
        webSettingsSetup = WebSettingsSetup(this@WebActivity, webView)
        webView.webChromeClient = webSettingsSetup.webChromeClient
        webView.webViewClient = webSettingsSetup.webViewClient
        webSettingsSetup.setup()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            OneSignal.Notifications.requestPermission(true)
        }
        webView.loadUrl(webSettingsSetup.webViewClient.getLastOpenedUrl(extra.toString()).toString())
        webView.visibility = View.VISIBLE
    }
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
