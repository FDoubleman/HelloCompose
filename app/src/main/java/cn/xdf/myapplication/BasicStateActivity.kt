package cn.xdf.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cn.xdf.myapplication.ui.widget.MyApplicationTheme
import cn.xdf.myapplication.viewmodel.WellnessViewModel
import cn.xdf.myapplication.widget.StatefulCounter
import cn.xdf.myapplication.widget.WaterCounter
import cn.xdf.myapplication.widget.WellnessTasksList

class BasicStateActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                WellnessScreen()
            }
        }
    }


    @Composable
    fun WellnessScreen(
        modifier: Modifier = Modifier,
        wellnessViewModel: WellnessViewModel = viewModel()
    ) {
        Column(modifier = modifier) {
            StatefulCounter()

            WellnessTasksList(
                list = wellnessViewModel.tasks,
                onCheckedTask = { task, checked ->
                    wellnessViewModel.changeTaskChecked(task, checked)
                },
                onCloseTask = { task -> wellnessViewModel.remove(task) })
        }
    }

}