package com.rahul.hellovivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Gold = Color(0xFFF5B841)
private val Ink = Color(0xFF0E0E12)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val version = packageManager.getPackageInfo(packageName, 0).versionName ?: "?"
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(primary = Gold, background = Ink)) {
                HelloScreen(version)
            }
        }
    }
}

@Composable
fun HelloScreen(version: String) {
    var taps by remember { mutableIntStateOf(0) }
    val pop by animateFloatAsState(
        targetValue = if (taps % 2 == 0) 1f else 1.15f,
        animationSpec = spring(dampingRatio = 0.4f),
        label = "pop"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Ink)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "👋", fontSize = 72.sp, modifier = Modifier.scale(pop))
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Hello Rahul",
            color = Color.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Built by Claude · Installed on your vivo X300 Ultra\nVersion $version",
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(40.dp))
        Button(
            onClick = { taps++ },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Ink)
        ) {
            Text(
                text = if (taps == 0) "Tap me" else "Tapped $taps ${if (taps == 1) "time" else "times"}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}
