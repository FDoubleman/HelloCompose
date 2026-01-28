package cn.xdf.myapplication.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier


fun getWellnessTasks() = List(30) { i ->
    WellnessTask(i, "Task # $i")
}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
) {
    val list = remember { getWellnessTasks().toMutableStateList() }
    LazyColumn(modifier = modifier) {
        items(list) { task ->
            WellnessTaskItem(taskName = task.label, onClose = { list.remove(task) })
        }
    }
}