package com.example.vhstutoring

import android.os.Build
import android.view.RoundedCorner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun StudentsScreen(
    dataViewModel: StudentDataViewModel = viewModel()
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

    var studentEntries = mutableListOf<HashMap<String, String>>()

    sortingMap.forEach {
        studentEntries.add(it.value)
    }

    studentEntries = studentEntries.asReversed()
    
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        
        studentEntries.forEach {
            StudentEntryBox(entry = it)
        }
        
        Spacer(modifier = Modifier.height(80.dp))
    }
}
