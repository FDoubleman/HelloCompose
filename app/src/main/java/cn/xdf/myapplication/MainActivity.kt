package cn.xdf.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.Size
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.google.android.material.search.SearchBar
import androidx.compose.material3.NavigationBarItem



class MainActivity : ComponentActivity() {
    val alignData = mutableListOf<String>()
    val favoriteData = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        initData()

        setContent {
            MyApplicationTheme {
                MySootheAppPortrait()

//                SoothBottomNavigation()
//                FavoriteCollectionCard()
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    Conversation(getMessages())
//                    MessageCard(Message("Compose", "Hello  I`m Compose"))
//                }

            }
        }
    }

    private fun initData() {
        for (i in 1..30) {
            alignData.add("align $i item")
            favoriteData.add("favorite $i item")
        }
    }


    @Composable
    fun MySootheAppPortrait(){
        Scaffold(
            bottomBar = {
                SoothBottomNavigation()
            }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }


    @Composable
    private fun SoothBottomNavigation(modifier: Modifier= Modifier) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surfaceBright,
            modifier = modifier
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text("首页")
                },
                selected = true,
                onClick = {
                    Log.d("fmm","首页点击了。。。")
                }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                label = {
                    Text("搜索")
                },
                selected = false,
                onClick = {
                    Log.d("fmm","搜索点击了。。。")
                }
            )

        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        Column(modifier) {
            Spacer(Modifier.height(16.dp))
            SearchBar(Modifier.padding(horizontal = 16.dp))
            HomeSection("标题1") {
                AlignYourBodyRow()
            }
            HomeSection("标题2") {
                FavoriteCollectionsGrid()
            }
            Spacer(Modifier.height(16.dp))
        }
    }

    @Composable
    fun HomeSection(
        title: String,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                "$title",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }

    }


    @Composable
    fun FavoriteCollectionsGrid(
        modifier: Modifier = Modifier
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.height(168.dp)
        ) {
            items(favoriteData, key = { it }) { item ->
                FavoriteCollectionCard(item)
            }
        }
    }

    @Composable
    fun SearchBar(modifier: Modifier = Modifier) {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(stringResource(R.string.placeholder_search))
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
        )
    }

    @Composable
    fun AlignYourBodyRow(
        modifier: Modifier = Modifier
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
//                .padding(horizontal = 1.dp)
                .background(Color.Gray),
//                .padding(horizontal = 20.dp)

        ) {
            items(alignData, key = { it }) { item ->
                AlignYourBodyElement(modifier, item)
            }
        }
    }

    @Composable
    fun AlignYourBodyElement(
        modifier: Modifier = Modifier, name: String
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(Color.Cyan),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "$name",
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }


    @Composable
    fun FavoriteCollectionCard(
        name: String,
        modifier: Modifier = Modifier
    ) {
        Surface(
            // 对卡片添加圆角
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(255.dp)
                    .background(Color.Cyan)
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = "$name",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }

    @Composable
    fun Greeting() {
        // 1、变量设置不同的值不会使 Compose 将其检测为状态更改，因此不会产生任何效果。
        // 更改此变量不会触发重组的原因是 Compose 并未跟踪此更改。
        // 此外，每次调用 Greeting 时，都会将该变量重置为 false。
//         var expanded = false
        /**?
         * Compose 应用通过调用可组合函数将数据转换为界面。如果您的数据发生变化，Compose 会使用新数据重新执行这些函数，
         * 从而创建更新后的界面，此过程称为重组。Compose 还会查看各个可组合项需要哪些数据，
         * 以便只需重组数据发生了变化的组件，而避免重组未受影响的组件。
         */
        // 2、如需向可组合项添加内部状态，可以使用 mutableStateOf 函数，该函数可让 Compose 重组读取该 State 的函数。
        // 不能只是将 mutableStateOf 分配给可组合项中的某个变量。
        val expanded = mutableStateOf(false)

        // 3、重组后保留状态，请使用 remember 记住可变状态。
        // remember 可以起到保护作用，防止状态在重组时被重置。
        // 如果从屏幕的不同部分调用同一可组合项，则会创建不同的界面元素，且每个元素都会拥有自己的状态版本。
        // 您可以将内部状态视为类中的私有变量。
//        val expanded = remember { mutableStateOf(false) }

        val extraPadding = if (expanded.value) 48.dp else 0.dp
        Surface(
            color = Color.Yellow,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello")
                    Text(text = "World")
                }
                ElevatedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }
        }
    }

    @Composable
    fun OnBoardingScreen(modifier: Modifier = Modifier) {
        var shouldShowOnBoarding by remember { mutableStateOf(true) }
        Column(modifier = modifier) { }


    }
    // TODO:

    fun getMessages(): List<Message> {
        val messages = mutableListOf<Message>()

        for (i in 1..20) {
            val message = Message(
                "Tom", "Hello $i times," +
                        "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                        "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                        "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                        "添加发发撒的发撒大幅度发撒的发as范德萨大法师地方水岸东方" +
                        "as地方水岸东方啥大事大法师大法师孙大发SD发"
            )
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
            Column(modifier = Modifier.clickable(onClick = {
                isExpanded = !isExpanded
            })) {
                Text(
                    text = msg.author ?: "tom",
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp,
                    color = Color.Yellow,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = msg.body ?: "hello",
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }


}