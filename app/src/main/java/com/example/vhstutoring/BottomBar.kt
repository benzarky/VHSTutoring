package com.example.vhstutoring

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavBar(
    modifier: Modifier,
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            "Tutors",
            painterResource(id = R.drawable.backpackselected),
            painterResource(id = R.drawable.backpackunselected),
            Screen.FindTutor.route
        ),

        BottomNavItem(
            "Students",
            painterResource(id = R.drawable.appleselected),
            painterResource(id = R.drawable.appleunselected),
            Screen.FindStudent.route
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(2)
    }

    BottomAppBar {
        NavigationBarItem(
            modifier = modifier,
            selected = selectedItemIndex == 1,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            onClick = {
                selectedItemIndex = 1
                navController.navigate(Screen.FindTutor.route)
            },
            icon = {
                if (selectedItemIndex == 1) {
                    Icon(
                        painter = painterResource(id = R.drawable.appleselected),
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.appleunselected),
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                }
            },
            label = {
                Text(text = "Tutors")
            }
        )

        NavigationBarItem(
            modifier = modifier,
            selected = selectedItemIndex == 2,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            onClick = {
                selectedItemIndex = 2
                navController.navigate(Screen.CreateListing.route)
            },
            icon = {
                if (selectedItemIndex == 2) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                }
            },
            label = {
                Text(text = "Create")
            }
        )

        NavigationBarItem(
            modifier = modifier,
            selected = selectedItemIndex == 3,
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            onClick = {
                selectedItemIndex = 3
                navController.navigate(Screen.FindStudent.route)
            },
            icon = {
                if (selectedItemIndex == 3) {
                    Icon(
                        painter = painterResource(id = R.drawable.backpackselected),
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.backpackunselected),
                        contentDescription = null,
                        modifier = Modifier
                            .height(25.dp)
                            .width(25.dp)
                    )
                }
            },
            label = {
                Text(text = "Students")
            }
        )
    }
}
