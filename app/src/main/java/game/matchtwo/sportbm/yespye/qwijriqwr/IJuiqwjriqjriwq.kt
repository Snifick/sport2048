package game.matchtwo.sportbm.yespye.qwijriqwr
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.airbnb.lottie.LottieAnimationView
import game.matchtwo.sportbm.yespye.codesi.IHUWQriwqir

class IJuiqwjriqjriwq(private val fijqwrijqwirji: Context,
                      private val qiwriqwriqwjri: WebView, val riqwjriqwjirji: LottieAnimationView) : WebViewClient() {

    private val rhijqwjriqhurqiwrj2 = IHUWQriwqir(fijqwrijqwirji)

    override fun onPageFinished(uhqwirqwrji: WebView?, ruqhwrqwjiri: String?) {
        super.onPageFinished(uhqwirqwrji, ruqhwrqwjiri)
        uhqwirqwrji?.visibility = View.VISIBLE
        riqwjriqwjirji.visibility = View.GONE
        rhijqwjriqhurqiwrj2.uqwhijrqwrqwrqwij(ruqhwrqwjiri.toString())
    }

    fun getLastOpenedUrl(ijqwjrijqwirji: String): String? {
        return rhijqwjriqhurqiwrj2.qhuwriqwrihiqrjji2(ijqwjrijqwirji)
    }

    override fun shouldOverrideUrlLoading(rjiqwrjwiqr: WebView?, rqwjriqwjri: WebResourceRequest?): Boolean {
        val rqwijriqwjriwji = rqwjriqwjri?.url.toString()
        if (!Ijiiiqwjriwqjrwir(rqwijriqwjriwji)) {
            val fqjwriwqjiri = Intent(Intent.ACTION_VIEW, Uri.parse(rqwijriqwjriwji))
            if (fqjwriwqjiri.resolveActivity(fijqwrijqwirji.packageManager) != null) {
                fijqwrijqwirji.startActivity(fqjwriwqjiri)
            }
            return true
        }
        rjiqwrjwiqr?.loadUrl(rqwijriqwjriwji)
        return false
    }

    private fun Ijiiiqwjriwqjrwir(riqjwriwqjirjiqwrji: String): Boolean {
        return riqjwriwqjirjiqwrji.startsWith("http://") || riqjwriwqjirjiqwrji.startsWith("https://")
    }
}