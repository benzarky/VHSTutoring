package com.example.vhstutoring

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vhstutoring.ui.theme.VHSTutoringTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VHSTutoringTheme {
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val snackBarState = remember { SnackbarHostState() }

                var showTopBar by rememberSaveable {
                    mutableStateOf(true)
                }
                var showBottomBar by rememberSaveable {
                    mutableStateOf(true)
                }
                val currentDestination by navController.currentBackStackEntryAsState()
                showTopBar = when(currentDestination?.destination?.route) {
                    "signIn" -> false
                    "createAccount" -> false
                    else -> true
                }
                showBottomBar = when(currentDestination?.destination?.route) {
                    "signIn" -> false
                    "createAccount" -> false
                    else -> true
                }
                // Firebase.auth.signOut()

                ModalNavigationDrawer(
                    drawerContent = {
                        DrawerContent(
                            navController = navController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState
                        )
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = snackBarState)
                        },
                        topBar = {
                            if(showTopBar) {
                                TopBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    coroutineScope = coroutineScope,
                                    drawerState = drawerState
                                )
                            }
                        },
                        bottomBar = {
                            if(showBottomBar) {
                                BottomNavBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    navController = navController
                                )
                            }
                        }
                    ) {padding ->
                        Body(
                            modifier = Modifier.padding(padding)
                        )
                        Navigation(navController, coroutineScope, snackBarState)
                    }
                }
            }
        }
    }
}

@Composable
fun Body(
    modifier: Modifier
) {

}
