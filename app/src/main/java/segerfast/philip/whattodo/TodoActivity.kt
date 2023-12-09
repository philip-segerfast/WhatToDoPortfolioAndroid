package segerfast.philip.whattodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import segerfast.philip.whattodo.compose.WhatDoToApp
import segerfast.philip.whattodo.ui.theme.WhatToDoPortfolioAndroidTheme

@AndroidEntryPoint
class TodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
        setContent {
            WhatToDoPortfolioAndroidTheme {
                WhatDoToApp()
            }
        }
    }
}
