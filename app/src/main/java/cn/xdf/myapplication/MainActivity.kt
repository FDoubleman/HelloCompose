package cn.xdf.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import cn.xdf.myapplication.bean.Message
import cn.xdf.myapplication.ui.widget.MyApplicationTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.mutableStateOf


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {


                    Conversation(getMessages())
//                    MessageCard(Message("Compose", "Hello  I`m Compose"))
                }

            }
        }
    }

    fun getMessages(): List<Message> {
        val messages = mutableListOf<Message>()

        for (i in 1..20) {
            val message = Message("Tom", "Hello $i times," +
                    "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                    "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                    "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                    "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                    "as地方水岸东方啥大事大法师大法师孙大发SD发")
            messages.add(message)
        }

        return messages
    }


    @Composable
    fun MessageCard(msg: Message) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)// // size 会同时设置 width 和 height
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(8.dp))
//            var isExpanded by remember { mutableSetOf(false) }
            var isExpanded by remember { mutableStateOf(false) }
            Column (modifier = Modifier.clickable(onClick = {
                isExpanded = !isExpanded
            })){
                Text(
                    text = msg.author ?: "tom",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp,
                    color = Color.Yellow,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body ?: "hello",
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded)Int.MAX_VALUE else 1 ,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn{
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }


}