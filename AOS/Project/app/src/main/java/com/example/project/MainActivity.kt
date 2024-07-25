package com.example.project

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import com.example.project.ui.login.LoginUiState
import com.example.project.ui.login.LoginViewModel
import com.example.project.ui.login.loginWithSlack
import com.example.project.ui.login.webView
import com.example.project.ui.navigation.NavigationGraph
import com.example.project.ui.theme.ProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    val navController = rememberNavController()
                    MyApplication.prefs.setAToken("")
                    MyApplication.prefs.setCode("")
                    NavigationGraph(context = this, navController = navController)
                }
            }
        }
        onNewIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            Log.d("Redirected uri", uri.toString() ?: "No uri found")
            if (uri != null) {
                val code = uri.getQueryParameter("code")
                MyApplication.prefs.setCode(code ?: "")

                // ViewModel에 코드 업데이트 요청
                val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
                viewModel.updateCode(code ?: "")

                // 리디렉트된 URI에서 코드 파라미터를 추출하여 사용
                Log.d("Redirected Code", code ?: "No code found")
                Log.d("코드", MyApplication.prefs.getCode() ?: "코드 없음")
                Log.d("코코드", viewModel.uuiState.value.code ?: "코드 없음")
            }
        }
    }
}

