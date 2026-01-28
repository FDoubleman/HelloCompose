package cn.xdf.myapplication.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
                    taskName = "Have you taken your 15 minute walk today? ")
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

/**
 * 状态提升
 * 提升状态时，有三条规则可帮助您弄清楚状态应去向何处：
 * 1. 将状态提升到最近的父级，并使用一个参数或 lambda 传递该状态。
 * (如果哥哥要上网，妹妹也要上网，那 Wi-Fi 密码就不能只写在哥哥的笔记本上，而应该贴在客厅的墙上（爸爸妈妈管）。)
 * 2. 提升到“可以发生变化（写入）的最高级别”
 * (谁有权改密码，密码就得提到谁那里。)
 * 3. 响应相同事件，提升到同一级别
 * 比如你点击“购买”按钮，余额会减少，库存也会减少
 */
@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    // 状态提升
    StatelessCounter(count, {
        count++
    }, modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        // onClick 产生的事件向上抛，交给上层容器进行处理
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text(text = "Add one")
        }
    }
}
