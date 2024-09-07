package com.example.vhstutoring

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.tasks.await

@Composable
fun MyListingsScreen(
    navController: NavController,
    coroutineScope: CoroutineScope,
    dataViewModel: MyListingsDataViewModel = viewModel()
) {
    val uid = Firebase.auth.currentUser?.uid
    var data = dataViewModel.state.value
    val personalData: MutableList<HashMap<String, String>> = mutableListOf<HashMap<String, String>>()

    if(data.size > 30) {
        data = data.take(30) as SnapshotStateList<HashMap<String, String>>
    }

    var sortingMap = mutableMapOf<Double, HashMap<String, String>>()

    data.forEach {
        if(it["UserID"] == uid) {
            personalData.add(it)
        }
    }

    personalData.forEach {
        it["TimeStamp"]?.let { it1 -> sortingMap[it1.toDouble()] = it }
    }

    sortingMap = sortingMap.toSortedMap()

    var myEntries = mutableListOf<HashMap<String, String>>()

    sortingMap.forEach {
        myEntries.add(it.value)
    }

    myEntries = myEntries.asReversed()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        
        myEntries.forEach {
            if(it["GPA"] == null) {
                Spacer(modifier = Modifier.height(16.dp))
                StudentRemoveEntryBox(navController, coroutineScope, it)
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                TutorRemoveEntryBox(navController, coroutineScope, it)
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            }
        }
    }
}
