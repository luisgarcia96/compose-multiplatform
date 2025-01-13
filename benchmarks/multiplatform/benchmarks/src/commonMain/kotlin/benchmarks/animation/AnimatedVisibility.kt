@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
package benchmarks.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose_benchmarks.benchmarks.generated.resources.Res
import compose_benchmarks.benchmarks.generated.resources.compose_multiplatform
import compose_benchmarks.benchmarks.generated.resources.img
import delays
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.yield
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.LocalResourceReader
import org.jetbrains.compose.resources.ResourceReader
import org.jetbrains.compose.resources.painterResource
import scheduleAfterMyDelay

private var i = 0

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AnimatedVisibility() {
    MaterialTheme {
        val res = painterResource(Res.drawable.img)
//        println("AnimatedVisibility1 ${i++}")
        var showImage by remember { mutableStateOf(false) }
//        println("AnimatedVisibility3 ${i++}")
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            println("AnimatedVisibility4 ${i++}")
            AnimatedVisibility(showImage) {
//                Text("AnimatedVisibility")
//                println("Time ($showImage) =  " + this.transition.playTimeNanos)
//                println("AnimatedVisibility5 ${i++}")
//                println("res = $res, ${res.intrinsicSize}")
                Image(res, null)
            }
        }
        LaunchedEffect(showImage) {
//            println("AnimatedVisibilityLE1 ${i++}")
            myDelay(200)
//            println("AnimatedVisibilityLE2 ${i++}")
            showImage = !showImage
        }
    }
}

suspend fun myDelay(time: Long) {
    suspendCancellableCoroutine {
        scheduleAfterMyDelay(time, it)
    }
}