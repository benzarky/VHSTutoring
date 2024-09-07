package com.example.vhstutoring

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TutorDataViewModel: ViewModel() {
    val state = mutableStateOf(mutableStateListOf<HashMap<String, String>>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getTutorEntries()
        }
    }
}

class StudentDataViewModel: ViewModel() {
    val state = mutableStateOf(mutableStateListOf<HashMap<String, String>>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getStudentEntries()
        }
    }
}

class MyListingsDataViewModel: ViewModel() {
    val state = mutableStateOf(mutableStateListOf<HashMap<String, String>>())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getMyEntries()
        }
    }
}

suspend fun getTutorEntries(): SnapshotStateList<HashMap<String, String>> {
    val db = Firebase.firestore
    val entry: SnapshotStateList<HashMap<String, String>> = SnapshotStateList()

    db.collection("Tutors").get().await().forEach {
        val entryHashMap = HashMap<String, String>()
        entryHashMap["TimeStamp"] = it["TimeStamp"].toString()
        entryHashMap["Name"] = it["Name"].toString()
        entryHashMap["Grade"] = it["Grade"].toString()
        entryHashMap["Subject"] = it["Subject"].toString()
        entryHashMap["HighestClass"] = it["HighestClass"].toString()
        entryHashMap["GPA"] = it["GPA"].toString()
        entryHashMap["PhoneNumber"] = it["PhoneNumber"].toString()

        entry.add(entryHashMap)
    }

    return entry
}

suspend fun getStudentEntries(): SnapshotStateList<HashMap<String, String>> {
    val db = Firebase.firestore
    val entry: SnapshotStateList<HashMap<String, String>> = SnapshotStateList()

    db.collection("Students").get().await().forEach {
        val entryHashMap = HashMap<String, String>()
        entryHashMap["TimeStamp"] = it["TimeStamp"].toString()
        entryHashMap["Name"] = it["Name"].toString()
        entryHashMap["Grade"] = it["Grade"].toString()
        entryHashMap["Subject"] = it["Subject"].toString()
        entryHashMap["ClassTaking"] = it["ClassTaking"].toString()
        entryHashMap["PhoneNumber"] = it["PhoneNumber"].toString()

        entry.add(entryHashMap)
    }

    return entry
}

suspend fun getMyEntries(): SnapshotStateList<HashMap<String, String>> {
    val db = Firebase.firestore
    val entry: SnapshotStateList<HashMap<String, String>> = SnapshotStateList()

    db.collection("Students").get().await().forEach {
        val entryHashMap = HashMap<String, String>()
        entryHashMap["UserID"] = it["UserID"].toString()
        entryHashMap["TimeStamp"] = it["TimeStamp"].toString()
        entryHashMap["Name"] = it["Name"].toString()
        entryHashMap["Grade"] = it["Grade"].toString()
        entryHashMap["Subject"] = it["Subject"].toString()
        entryHashMap["ClassTaking"] = it["ClassTaking"].toString()
        entryHashMap["PhoneNumber"] = it["PhoneNumber"].toString()

        entry.add(entryHashMap)
    }

    db.collection("Tutors").get().await().forEach {
        val entryHashMap = HashMap<String, String>()
        entryHashMap["UserID"] = it["UserID"].toString()
        entryHashMap["TimeStamp"] = it["TimeStamp"].toString()
        entryHashMap["Name"] = it["Name"].toString()
        entryHashMap["Grade"] = it["Grade"].toString()
        entryHashMap["Subject"] = it["Subject"].toString()
        entryHashMap["HighestClass"] = it["HighestClass"].toString()
        entryHashMap["GPA"] = it["GPA"].toString()
        entryHashMap["PhoneNumber"] = it["PhoneNumber"].toString()

        entry.add(entryHashMap)
    }

    return entry
}