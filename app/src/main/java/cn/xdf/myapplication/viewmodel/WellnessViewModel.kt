package cn.xdf.myapplication.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import cn.xdf.myapplication.widget.WellnessTask

class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks


    fun getWellnessTasks() = List(30) { i ->
        WellnessTask(i, "Task # $i")
    }

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }


    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
        tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
    }

}