package cn.xdf.myapplication.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * 优秀实践是为所有可组合函数提供默认的 Modifier，
 * 从而提高可重用性。它应作为第一个可选参数显示在参数列表中，位于所有必需参数之后。
 */
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {


    Column(modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today? ",
                    onClose = {
                        showTask = false
                    })
            }
            Text("You've had $count glasses.")
        }

        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
                Text(text = "Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp),
            ) {
                Text(text = "Clear water count")
            }
        }


    }


}

