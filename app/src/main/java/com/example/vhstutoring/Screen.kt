package com.example.vhstutoring

sealed class Screen(
    val title: String,
    val route: String
) {
    data object FindTutor: Screen(
        "Find Tutor",
        "findTutor"
    )
    data object CreateListing: Screen(
        "Create Listing",
        "createListing"
    )
    data object FindStudent: Screen(
        "Find Student",
        "findStudent"
    )
    data object Home: Screen(
        "Home",
        "createListing"
    )
    data object Settings: Screen(
        "Settings",
        "settings"
    )
    data object MyListings: Screen(
        "My Listings",
        "myListings"
    )
    data object SignIn: Screen(
        "Sign In",
        "signIn"
    )
    data object CreateAccount: Screen(
        "Create Account",
        "createAccount"
    )
}