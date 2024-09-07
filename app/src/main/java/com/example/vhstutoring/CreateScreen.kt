package com.example.vhstutoring

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar

var entrySuccess: Boolean = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    coroutineScope: CoroutineScope,
    snackBarState: SnackbarHostState
) {
    val intentElements = listOf("I am going to be a student", "I am going to be a tutor")
    val subjectElements = listOf("Math", "Science", "English", "History")
    val gradeElements = listOf("Freshman", "Sophomore", "Junior", "Senior")

    var name by remember{
        mutableStateOf("")
    }
    var specificSubject by remember{
        mutableStateOf("")
    }
    var gpa by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember{
        mutableStateOf("")
    }
    var intentExpandedState by remember {
        mutableStateOf(false)
    }
    var selectedIntent by remember {
        mutableStateOf(intentElements[0])
    }
    var subjectExpandedState by remember {
        mutableStateOf(false)
    }
    var selectedSubject by remember {
        mutableStateOf(subjectElements[0])
    }
    var gradeExpandedState by remember {
        mutableStateOf(false)
    }
    var selectedGrade by remember {
        mutableStateOf(gradeElements[0])
    }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        
        Text(
            text = "About You",
            fontSize = 24.sp,
            color = Color(1, 68, 33)
        )

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = intentExpandedState,
            onExpandedChange = {
                intentExpandedState = !intentExpandedState
            }
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = selectedIntent,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Intent",
                        color = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(1, 68, 33),
                    unfocusedContainerColor = Color(1, 68, 33)
                )
            )

            ExposedDropdownMenu(
                expanded = intentExpandedState,
                onDismissRequest = {
                    intentExpandedState = false
                }
            ) {
                intentElements.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it)
                        },
                        onClick = {
                            selectedIntent = it
                            intentExpandedState = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(
                    text = "Enter Name",
                    color = Color.White
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(1, 68, 33),
                unfocusedContainerColor = Color(1, 68, 33)
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = gradeExpandedState,
            onExpandedChange = {
                gradeExpandedState = !gradeExpandedState
            }
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = selectedGrade,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Grade",
                        color = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(1, 68, 33),
                    unfocusedContainerColor = Color(1, 68, 33)
                )
            )

            ExposedDropdownMenu(
                expanded = gradeExpandedState,
                onDismissRequest = {
                    gradeExpandedState = false
                }
            ) {
                gradeElements.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it)
                        },
                        onClick = {
                            selectedGrade = it
                            gradeExpandedState = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Academic",
            fontSize = 24.sp,
            color = Color(1, 68, 33)
        )

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        ExposedDropdownMenuBox(
            expanded = subjectExpandedState,
            onExpandedChange = {
                subjectExpandedState = !subjectExpandedState
            }
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = selectedSubject,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "Subject",
                        color = Color.White
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(1, 68, 33),
                    unfocusedContainerColor = Color(1, 68, 33)
                )
            )

            ExposedDropdownMenu(
                expanded = subjectExpandedState,
                onDismissRequest = {
                    subjectExpandedState = false
                }
            ) {
                subjectElements.forEach {
                    DropdownMenuItem(
                        text = {
                            Text(text = it)
                        },
                        onClick = {
                            selectedSubject = it
                            subjectExpandedState = false
                        }
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = specificSubject, 
            onValueChange = {
                specificSubject = it
            },
            label = {
                if (selectedIntent == intentElements[0]) {
                    Text(
                        text = "Current $selectedSubject Class",
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Highest $selectedSubject Class Taken",
                        color = Color.White
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(1, 68, 33),
                unfocusedContainerColor = Color(1, 68, 33)
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        if(selectedIntent == intentElements[1]) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = gpa,
                onValueChange = {
                    gpa = it
                },
                label = {
                    Text(
                        text = "GPA",
                        color = Color.White
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color(1, 68, 33),
                    unfocusedContainerColor = Color(1, 68, 33)
                ),
                singleLine = true
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Text(
            text = "Contact",
            fontSize = 24.sp,
            color = Color(1, 68, 33)
        )
        
        Divider()
        
        Spacer(modifier = Modifier.height(12.dp))
        
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber, 
            onValueChange = {
                phoneNumber = it
            },
            label = {
                Text(
                    text = "Phone Number",
                    color = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(1, 68, 33),
                unfocusedContainerColor = Color(1, 68, 33)
            ),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(1, 68, 33)),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                submitEntry(selectedIntent, name, selectedGrade, selectedSubject, specificSubject, gpa, phoneNumber, coroutineScope, snackBarState)

                if(entrySuccess) {
                    name = ""
                    selectedGrade = gradeElements[0]
                    selectedSubject = subjectElements[0]
                    specificSubject = ""
                    gpa = ""
                    phoneNumber = ""

                    entrySuccess = false
                }
            }
        ) {
            Text(
                text = "Post Listing",
                fontSize = 20.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

fun submitEntry(
    selectedIntent: String,
    name: String,
    grade: String,
    selectedSubject: String,
    specificSubject: String,
    gpa: String,
    phoneNumber: String,
    coroutineScope: CoroutineScope,
    snackBarState: SnackbarHostState
) {
    var missingFields: Int = 0
    val db = Firebase.firestore

    val requiredFields: List<String> = if(selectedIntent == "I am going to be a student") {
        listOf(name, specificSubject, phoneNumber, grade, selectedSubject)
    } else listOf(name, specificSubject, phoneNumber, grade, selectedSubject, gpa)

    requiredFields.forEach {
        if(it.isEmpty()) missingFields++
    }

    if(missingFields == 0) {
        val timeStamp = getTimeStamp().toString()

        if(selectedIntent == "I am going to be a student") {


            val entry = hashMapOf(
                "UserID" to Firebase.auth.currentUser?.uid,
                "TimeStamp" to timeStamp,
                "Name" to requiredFields[0],
                "Grade" to requiredFields[3],
                "Subject" to requiredFields[4],
                "ClassTaking" to requiredFields[1],
                "PhoneNumber" to requiredFields[2]
            )

            db.collection("Students").add(entry)
            coroutineScope.launch {
                snackBarState.showSnackbar("Listing Posted")
            }
        } else {
            val entry = hashMapOf(
                "UserID" to Firebase.auth.currentUser?.uid,
                "TimeStamp" to timeStamp,
                "Name" to requiredFields[0],
                "Grade" to requiredFields[3],
                "Subject" to requiredFields[4],
                "HighestClass" to requiredFields[1],
                "GPA" to requiredFields[5],
                "PhoneNumber" to requiredFields[2]
            )

            db.collection("Tutors").add(entry)
            coroutineScope.launch {
                snackBarState.showSnackbar("Listing Posted")
            }
        }

        entrySuccess = true
    } else {
        coroutineScope.launch {
            snackBarState.showSnackbar("Fill all fields")
        }
    }
}

fun getTimeStamp(): Double {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
    val adjustedDay =  dayOfYear + ((year - 2024) * 365)
    val hour = calendar.get(Calendar.HOUR).toFloat()
    val minute = calendar.get(Calendar.MINUTE).toFloat()
    val second = calendar.get(Calendar.SECOND).toFloat()

    var secondOfDay: Double = hour * 3600.0 + minute * 60 + second

    while(secondOfDay >= 1) {
        secondOfDay /= 10
    }

    return adjustedDay + secondOfDay
}