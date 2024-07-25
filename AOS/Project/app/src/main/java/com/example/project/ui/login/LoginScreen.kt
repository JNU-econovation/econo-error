package com.example.project.ui.login

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.MyApplication
import com.example.project.R
import com.example.project.data.local.UiState
import com.example.project.data.remote.EventRequest
import com.example.project.ui.calendar.Calendar
import com.example.project.ui.calendar.CalendarViewModel
import com.example.project.ui.list.List
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), navController: NavHostController = rememberNavController()) {
    val scope = rememberCoroutineScope()
    val uriHandler = LocalUriHandler.current // 코드 값을 기억
    val uiState by loginViewModel.uuiState.collectAsState()
    var code by remember { mutableStateOf(uiState.code) }

    LaunchedEffect(code) {
        Log.d("LoginScreen", "code: ${code}")
        if (code.isNotBlank()) { // 코드 값이 비어있지 않으면 실행

            loginViewModel.loginWithSlack(code) // 코드 값으로 로그인
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(id = R.drawable.login_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Surface(
            color = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .padding(30.dp),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Black
                    )
                )
                Text(
                    text = "ERROR",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFff9999)
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.login_btn),
                    contentDescription = "로그인 버튼",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(50.dp)
                        .clickable {

                            val url =
                                "https://econovation-2018.slack.com/oauth?client_id=437291124342.7141431332214&scope=chat%3Awrite%2Cchat%3Awrite.customize%2Cchat%3Awrite.public%2Cemoji%3Aread%2Cfiles%3Awrite%2Cincoming-webhook&user_scope=chat%3Awrite%2Cusers.profile%3Aread&redirect_uri=https%3A%2F%2Fecono-calendar.com%2Flogin&state=&granular_bot_scope=1&single_channel=0&install_redirect=&tracked=1&team="
                            uriHandler.openUri(url)
                        }
                )
                Text(
                    "에코노베이션 회원이 아니신 경우 로그인이 불가합니다.",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}



@Composable
fun webView(){
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val Url = "https://econovation-2018.slack.com/oauth?client_id=437291124342.7141431332214&scope=chat%3Awrite%2Cchat%3Awrite.customize%2Cchat%3Awrite.public%2Cemoji%3Aread%2Cfiles%3Awrite%2Cincoming-webhook&user_scope=chat%3Awrite%2Cusers.profile%3Aread&redirect_uri=https%3A%2F%2Fecono-calendar.com%2Flogin&state=&granular_bot_scope=1&single_channel=0&install_redirect=&tracked=1&team="
    uriHandler.openUri(Url)

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun LoginScreenPreview() {
    LoginScreen()
}