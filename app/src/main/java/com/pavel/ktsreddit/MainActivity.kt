package com.pavel.ktsreddit

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val mutableBgColor = remember{ mutableStateOf(Color.DarkGray) }
            val configuration = LocalConfiguration.current
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                StartScreenPortrait(color=mutableBgColor.value)
            } else {
                mutableBgColor.value = Color.LightGray
                StartScreenLandscape(color=mutableBgColor.value)
            }
        }
    }
}

@Composable
fun StartScreenButton() {
    val context = LocalContext.current
    val toastText = stringResource(id = R.string.start_screen_toast_text)
    Button(
        modifier = Modifier.padding(16.dp),
        onClick = {
            Toast.makeText(context,
                toastText,
                Toast.LENGTH_LONG)
                .show()
        }
    ) {
        Text(text = stringResource(R.string.start_screen_button_text))
    }
}

@Composable
fun StartScreenImage() {
    Image(
        modifier = Modifier
            .padding(16.dp)
            .size(150.dp, 220.dp),
        imageVector = ImageVector.vectorResource(R.drawable.reddit_logo),
        contentDescription = null
    )
}

@Composable
fun StartScreenLabel() {
    Text(
        modifier = Modifier
            .padding(16.dp),
        text = stringResource(id = R.string.start_screen_text_label), fontSize = 24.sp)
}

@Composable
fun StartScreenPortrait(color: Color) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        StartScreenImage()
        StartScreenLabel()
        StartScreenButton()
    }
}

@Composable
fun StartScreenLandscape(color: Color) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        StartScreenImage()

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            StartScreenLabel()
            StartScreenButton()
        }
    }
}