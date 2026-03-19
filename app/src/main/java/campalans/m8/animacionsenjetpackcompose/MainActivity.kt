package campalans.m8.animacionsenjetpackcompose

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import campalans.m8.animacionsenjetpackcompose.ui.theme.AnimacionsEnJetpackComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimacionsEnJetpackComposeTheme {
                Greeting( )

            }
        }
    }
}

@Composable
fun Greeting() {
    var visible by remember { mutableStateOf(true) }
    var count by remember { mutableIntStateOf(0) }

    LazyColumn (modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()
    )
    {
        item {

            Column {
                Button(onClick = { visible = !visible }) {
                    Text(if (visible) "Hide" else "Show")
                }

                AnimatedVisibility(visible) {
                    Text("This text animates in/out")
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { count++ }) {
                    Text("Increase")
                }

                AnimatedContent(targetState = count) { targetCount ->
                    Text("Count: $targetCount")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimacionsEnJetpackComposeTheme {
        Greeting()
    }
}