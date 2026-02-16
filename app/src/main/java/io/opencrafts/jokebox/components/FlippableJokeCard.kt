package io.opencrafts.jokebox.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  FlippableJokeCard(
    setup: String,
    punchline: String,
){
    var isPressed by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    
    // Animate the rotation angle
    val rotation by animateFloatAsState(
        targetValue = if (isPressed) 180f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "CardRotation"
    )
    LaunchedEffect(rotation > 90f) {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
            .pointerInput(Unit) {

                awaitPointerEventScope {
                    while (true){
                       // Wait for the initial touch down
                        val down = awaitFirstDown()
                        isPressed = true
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        
                        // Wait for the finger to lift up or the gesture to be consumed
                        waitForUpOrCancellation()
                        isPressed = false 
                    }
                }
                // Flip on long press (The "Reveal")
            }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                // Front Side (Setup)
                Text(
                    text = setup,
                    modifier = Modifier.padding(24.dp),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = punchline,
                    modifier = Modifier
                        .padding(24.dp)
                        .graphicsLayer { rotationY = 180f },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FlippableJokeCardPreview() = FlippableJokeCard("What do you call a fake noodle?", "An impasta!")