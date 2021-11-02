package com.codelab.basicscodelab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basicscodelab2.ui.theme.BasicsCodelab2Theme

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
    Greetings()
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
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello!")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.padding(2.dp)
            ) {
                Text(if (expanded.value) "Show Less" else "Show More")
            }
        }
    }
}

@Composable
fun Greetings(names: List<String> = listOf("Android", "Google", "JetBrain")) {
    Column {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsCodelab2Theme {
        MyApp()
    }
}