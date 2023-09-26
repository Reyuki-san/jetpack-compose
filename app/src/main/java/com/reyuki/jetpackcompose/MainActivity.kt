package com.reyuki.jetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reyuki.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen { shouldShowOnboarding = false }
        } else {
            Greetings()
        }
    }
}

@Composable
private fun Greetings(modifier: Modifier = Modifier, names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name -> Greeting(name = name) }
    }
}

@Composable
private fun CardContent(name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier.padding(24.dp).animateContentSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Hello,")
            Text(text = name, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            if (expanded.value) Text(text = ("Composem ipsum color sit lazy, " + "padding theme elit, sed do bouncy. ").repeat(4))
        }
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(imageVector = if (expanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = if (expanded.value) stringResource(
                R.string.show_less
            ) else stringResource(R.string.show_more))
        }
    }
}

@Composable
private fun Greeting(name: String) {
    ElevatedCard(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    ), modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 8.dp)
        .fillMaxWidth()) {
        CardContent(name)
    }
}

@Composable
fun OnboardingScreen(callback: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to the basics codelab!")
        Button(modifier = Modifier.padding(vertical = 24.dp),onClick = callback) {
            Text(text = "Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    JetpackComposeTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 620)
@Composable
fun OnboardingPreview() {
    JetpackComposeTheme {
        OnboardingScreen {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320, heightDp = 620)
@Composable
fun MyAppPreview() {
    JetpackComposeTheme {
        MyApp()
    }
}