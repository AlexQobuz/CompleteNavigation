package com.example.completenavigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.justchat.ui.theme.CompleteNavigationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent{
            CompleteNavigationTheme {
                MainActivityView()
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityView() {
    CompleteNavigationTheme{
        MainView()
    }
}

@Composable
fun MainView() {
    Box() {
        toolbar()
        bottomNavBar()
    }
}
@Composable
fun bottomNavBar() {
    Scaffold (
        bottomNavBar = {
            BottomAppBar {

            }
        }
    )


@Composable
fun toolbar() {
    TopAppBar(
        title = {
            Text(text = "Complete Nav")
            Color.White
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Btn")
            }
        },
        backgroundColor = Color.Blue,
        contentColor = Color.Gray,
        elevation = 2.dp
    )
}