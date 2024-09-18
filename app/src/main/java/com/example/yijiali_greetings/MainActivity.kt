package com.example.yijiali_greetings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yijiali_greetings.ui.theme.YijiaLiGreetingsTheme
import java.util.*
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YijiaLiGreetingsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    // Holds the name input from the user
    var name by remember { mutableStateOf("") }
    // Holds the greeting message
    var greetingMessage by remember { mutableStateOf("Please enter your name") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for user to enter their name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to generate the greeting
        Button(onClick = {
            greetingMessage = generateGreeting(name)
        }) {
            Text("Show Greeting")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the greeting message
        Text(text = greetingMessage)
    }
}

// Function to generate personalized greeting based on the time of day
fun generateGreeting(name: String): String {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val timeOfDay = when (currentHour) {
        in 6..11 -> "morning"
        in 12..17 -> "afternoon"
        else -> "evening"
    }

    return if (name.isNotBlank()) {
        "Good $timeOfDay, $name!"
    } else {
        "Hello! Please enter your name."
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    YijiaLiGreetingsTheme {
        GreetingScreen()
    }
}
