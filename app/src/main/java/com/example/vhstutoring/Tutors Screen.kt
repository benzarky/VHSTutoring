package com.example.vhstutoring

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TutorsScreen(
    dataViewModel: TutorDataViewModel = viewModel()
) {
    var data = dataViewModel.state.value

    if(data.size > 30) {
        data = data.take(30) as SnapshotStateList<HashMap<String, String>>
    }

    var sortingMap = mutableMapOf<Double, HashMap<String, String>>()

    data.forEach {
        it["TimeStamp"]?.let { it1 -> sortingMap[it1.toDouble()] = it }
    }

    sortingMap = sortingMap.toSortedMap()

    var tutorEntries = mutableListOf<HashMap<String, String>>()

    sortingMap.forEach {
        tutorEntries.add(it.value)
    }

    tutorEntries = tutorEntries.asReversed()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        tutorEntries.forEach {
            TutorEntryBox(entry = it)
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}