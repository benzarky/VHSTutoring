package com.example.vhstutoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val drawerNavItems = listOf(
    DrawerNavItem(
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home,
        Screen.Home.route
    ),
    DrawerNavItem(
        "Settings",
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
        Screen.Settings.route
    ),
    DrawerNavItem(
        "My Listings",
        Icons.Filled.List,
        Icons.Outlined.List,
        Screen.MyListings.route
    )
)

@Composable
fun DrawerContent(
    navController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            drawerNavItems.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    label = {
                        Text(text = item.title)
                    },
                    selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navController.navigate(item.route)
                    }
                )
            }

            NavigationDrawerItem(
                label = {
                    Text(text = "Logout")
                },
                selected = false,
                onClick = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate("signIn")
                    Firebase.auth.signOut()
                }
            )
        }
    }
}