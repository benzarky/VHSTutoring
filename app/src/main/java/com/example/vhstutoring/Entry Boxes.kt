package com.example.vhstutoring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.logging.Handler

@Composable
fun TutorEntryBox(
    entry: HashMap<String, String>
) {
    val subject = entry["Subject"]
    val highestClass = entry["HighestClass"]
    val grade = entry["Grade"]
    val gpa = entry["GPA"]
    val phoneNumber = entry["PhoneNumber"]

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = entry["Name"].toString(),
        color = Color(1, 68, 33),
        fontSize = 24.sp
    )

    Divider()

    Spacer(modifier = Modifier.height(12.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(1, 68, 33), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Tutors: $subject",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Highest Class: $highestClass",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "GPA: $gpa",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Grade: $grade",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Phone Number: $phoneNumber",
                color = Color.White
            )
        }
    }
}

@Composable
fun StudentEntryBox(
    entry: HashMap<String, String>
) {
    val subject = entry["Subject"]
    val currentClass = entry["ClassTaking"]
    val grade = entry["Grade"]
    val phoneNumber = entry["PhoneNumber"]

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = entry["Name"].toString(),
        fontSize = 24.sp,
        color = Color(1, 68, 33)
    )

    Divider()

    Spacer(modifier = Modifier.height(12.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(1, 68, 33), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Needs Help With: $subject",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Current Class: $currentClass",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Grade: $grade",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Phone Number: $phoneNumber",
                color = Color.White
            )
        }
    }
}

@Composable
fun TutorRemoveEntryBox(
    navController: NavController,
    coroutineScope: CoroutineScope,
    entry: HashMap<String, String>
) {
    val subject = entry["Subject"]
    val timeStamp = entry["TimeStamp"]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(1, 68, 33), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "You created as a tutor to help with $subject",
                color = Color.White
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    coroutineScope.launch {
                        deleteTutorListing(timeStamp)
                    }
                    android.os.Handler().postDelayed({
                        navController.navigate("myListings")
                    }, 700)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Delete Listing",
                    color = Color(1, 68, 33)
                )
            }
        }
    }
}

@Composable
fun StudentRemoveEntryBox(
    navController: NavController,
    coroutineScope: CoroutineScope,
    entry: HashMap<String, String>
) {
    val classTaking = entry["ClassTaking"]
    val timeStamp = entry["TimeStamp"]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(1, 68, 33), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "You created as a student for help in $classTaking",
                color = Color.White
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    coroutineScope.launch {
                        deleteStudentListing(timeStamp)
                    }
                    android.os.Handler().postDelayed({
                        navController.navigate("myListings")
                    }, 700)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Delete Listing",
                    color = Color(1, 68, 33)
                )
            }
        }
    }
}

suspend fun deleteStudentListing(timeStamp: String?) {
    val db = Firebase.firestore

    db.collection("Students").get().await().forEach {
        if(it["TimeStamp"] == timeStamp) {
            db.collection("Students").document(it.id).delete()
        }
    }
}

suspend fun deleteTutorListing(timeStamp: String?) {
    val db = Firebase.firestore

    db.collection("Tutors").get().await().forEach {
        if(it["TimeStamp"] == timeStamp) {
            db.collection("Tutors").document(it.id).delete()
        }
    }
}