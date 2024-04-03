package game.matchtwo.sportbm.yespye.qwijriqwr

import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

fun setupCookie(view: WebView) {
    view.settings.setupJsAndOtherSettings()
    CookieManager.getInstance().setAcceptCookie(true)
    CookieManager.getInstance().setAcceptThirdPartyCookies(view, true)
}

fun WebSettings.setupJsAndOtherSettings() {
    domStorageEnabled = true
    allowContentAccess = true
    allowFileAccess = true
    setSupportMultipleWindows(false)
    setSupportZoom(true)
    cacheMode = WebSettings.LOAD_DEFAULT
    useWideViewPort = true
    builtInZoomControls = true
    loadWithOverviewMode = true
    useWideViewPort = true
    databaseEnabled = true
    javaScriptEnabled = true
    builtInZoomControls = true
    setSupportZoom(true)
    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    javaScriptCanOpenWindowsAutomatically = true

    allowFileAccessFromFileURLs = true
    allowUniversalAccessFromFileURLs = true
    displayZoomControls = false
    userAgentString = userAgentString.replace("; wv", "").replace(" Version/4.0", "")
}

