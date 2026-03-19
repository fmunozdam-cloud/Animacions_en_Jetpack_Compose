package campalans.m8.animacionsenjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import campalans.m8.animacionsenjetpackcompose.ui.theme.AnimacionsEnJetpackComposeTheme
import androidx.constraintlayout.compose.ConstraintSet
import kotlinx.coroutines.delay


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

@OptIn(ExperimentalMotionApi::class)
@Composable
fun Greeting() {



    LazyColumn (modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()
    )
    {
        item {

            //Primer exemple
            var visible by remember { mutableStateOf(true) }
            Column {
                Button(onClick = { visible = !visible }) {
                    Text(if (visible) "Hide" else "Show")
                }

                AnimatedVisibility(visible) {
                    Text("This text animates in/out")
                }
            }
            // Works well for fade, slide, expand/shrink animations.

            //Segon exemple
            var count by remember { mutableIntStateOf(0) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { count++ }) {
                    Text("Increase")
                }

                AnimatedContent(targetState = count) { targetCount ->
                    Text("Count: $targetCount")
                }
            }

            //Tercer exemple
            val progress = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                progress.animateTo(1f, animationSpec = tween(2000))
            }

            MotionLayout(
                start = ConstraintSet {
                    val box = createRefFor("box")
                    constrain(box) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                },
                end = ConstraintSet {
                    val box = createRefFor("box")
                    constrain(box) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                },
                progress = progress.value
            ) {
                Box(
                    Modifier
                        .size(60.dp)
                        .background(Color.Red)
                        .layoutId("box")
                )
            }

            //Quart exemple
            var rotation by remember { mutableStateOf(0f) }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(16)
                    rotation += 2f
                }
            }

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .graphicsLayer(rotationZ = rotation)
                    .background(Color.Green)
            )
            // Rotate, scale, or translate composables.


            //Cinqué exemple



            //Sisé exemple



            //Seté exemple

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