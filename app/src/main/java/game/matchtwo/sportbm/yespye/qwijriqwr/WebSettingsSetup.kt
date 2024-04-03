package game.matchtwo.sportbm.yespye.qwijriqwr

import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class WebSettingsSetup(private val activity: AppCompatActivity, private val webview: WebView) {
    val webViewClient = WebViewClientSuper(activity)
    val webChromeClient = object : WebChromeClient(){}

    fun setup() {
        webview.webChromeClient = webChromeClient
        webview.webViewClient = webViewClient
        setupCookie(webview)
    }
}
