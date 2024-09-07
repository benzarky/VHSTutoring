package com.example.vhstutoring

import android.content.ContentValues.TAG
import android.graphics.Paint.Style
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SignIn(
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val auth = Firebase.auth

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "VHS Tutoring",
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = Color(1, 68, 33),
            fontFamily = FontFamily.Cursive
        )

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    if(passwordVisible) {
                        Icon(
                            painterResource(id = R.drawable.visibility_24dp_5f6368_fill0_wght400_grad0_opsz24),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painterResource(id = R.drawable.visibility_off_24dp_5f6368_fill0_wght400_grad0_opsz24),
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if(passwordVisible) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(1, 68, 33)),
            onClick = {
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    email = email.replace(" ", "")
                    password = password.replace(" ", "")
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() {
                        if(it.isSuccessful) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Signing In")
                            }
                            navController.navigate("createListing")
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(it.exception?.message.toString())
                            }
                        }
                    }
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Fill All Fields")
                    }
                }
            }
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )
        }
    }
    
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            onClick = {
                navController.navigate("createAccount")
            }
        ) {
            Text(
                text = "Create Account",
                color = Color(1, 68, 33)
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun CreateAccount(
    coroutineScope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val auth = Firebase.auth

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(1, 68, 33)
        )

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    if(passwordVisible) {
                        Icon(
                            painterResource(id = R.drawable.visibility_24dp_5f6368_fill0_wght400_grad0_opsz24),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            painterResource(id = R.drawable.visibility_off_24dp_5f6368_fill0_wght400_grad0_opsz24),
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if(passwordVisible) {
                VisualTransformation.None
            } else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(12.dp))
        
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(1, 68, 33)),
            onClick = { 
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    if(password.contains(" ") || email.contains(" ")) {
                        if(password.contains(" ")) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Password cannot contain spaces")
                            }
                            password = ""
                        }
                        if(email.contains(" ")) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Email cannot contain spaces")
                            }
                            email = ""
                        }
                    } else {
                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener() {
                            if(it.isSuccessful) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Account Created")
                                }
                                navController.navigate("signIn")
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(it.exception?.message.toString())
                                }
                            }
                        }
                    }
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Fill All Fields")
                    }
                }
            }
        ) {
            Text(
                text = "Create Account",
                color = Color.White
            )
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            onClick = {
                navController.navigate("signIn")
            }
        ) {
            Text(
                text = "Sign In",
                color = Color(1, 68, 33)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}