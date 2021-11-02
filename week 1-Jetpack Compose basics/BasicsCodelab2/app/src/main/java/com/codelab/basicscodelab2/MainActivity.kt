package com.codelab.basicscodelab2

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basicscodelab2.ui.theme.BasicsCodelab2Theme
import com.codelab.basicscodelab2.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelab2Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Basics CodeLab!",
                style = MaterialTheme.typography.subtitle1
            )
            Button(onClick = onContinueClicked, modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Continue"
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun onBoardingPreview() {
    BasicsCodelab2Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.padding(5.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp)) // padding 이 음수값 되지 않게
            ) {
                Text(text = "Hello!", style = MaterialTheme.typography.h6)
                Text(
                    text = name, style = MaterialTheme.typography.h4
                )
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.padding(1.dp),
                shape = Shapes.small
            ) {
                Text(
                    if (expanded.value) "less <" else "more >"
                )
            }
        }
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn {
        items(items = names) {
            Greeting(name = it)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsCodelab2Theme {
        MyApp()
    }
}