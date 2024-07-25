package com.example.project.ui.login

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun loginWithSlack() {

        // Declare a string that contains a url
        val Url = "https://econovation-2018.slack.com/oauth?client_id=437291124342.7141431332214&scope=chat%3Awrite%2Cchat%3Awrite.customize%2Cchat%3Awrite.public%2Cemoji%3Aread%2Cfiles%3Awrite%2Cincoming-webhook&user_scope=chat%3Awrite%2Cusers.profile%3Aread&redirect_uri=https%3A%2F%2Fecono-calendar.com%2Flogin&state=&granular_bot_scope=1&single_channel=0&install_redirect=&tracked=1&team="
        // Adding a WebView inside AndroidView
        // with layout as full screen
    val uriHandler = LocalUriHandler.current
    uriHandler.openUri(Url)



}