package com.example.vhstutoring

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.CoroutineScope

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Navigation(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    snackBarState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {
        composable(route = Screen.FindTutor.route) {
            TutorsScreen()
        }
        composable(route = Screen.CreateListing.route) {
            CreateScreen(coroutineScope, snackBarState)
        }
        composable(route = Screen.FindStudent.route) {
            StudentsScreen()
        }
        composable(route = Screen.Settings.route) {
            ListingScreen(
                modifier = Modifier.fillMaxSize(),
                title = "Settings",
                icon = Icons.Default.Settings
            )
        }
        composable(route = Screen.MyListings.route) {
            MyListingsScreen(navController, coroutineScope)
        }
        composable(route = Screen.SignIn.route) {
            SignIn(coroutineScope, snackBarState, navController)
        }
        composable(route = Screen.CreateAccount.route) {
            CreateAccount(coroutineScope, snackBarState, navController)
        }
    }
}