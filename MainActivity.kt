package com.ehsieh2.memez

import MemeViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.ehsieh2.memez.ui.theme.MemezTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemeViewModel::class.java)

        setContent {
            MemezTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MemeViewModel) {
    val context = LocalContext.current
    var memeQuote by remember { mutableStateOf(viewModel.generateRandomMemeQuote(context)) }
    var memeImage by remember { mutableStateOf(viewModel.generateRandomMemeImage(context)) }
    var memeCounter by remember { mutableStateOf(0) }
    var screenColor by remember { mutableStateOf(Color.White) } // Define screenColor here

    Box(
        modifier = Modifier.fillMaxSize().background(color = screenColor), // Set background color
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
                    painter = painterResource(id = memeImage),
                    contentDescription = "Meme Image",
                    modifier = Modifier.fillMaxSize()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 8.dp)
                        .background(Color.Black)
                ) {
                    Text(
                        text = memeQuote,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .width(300.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Clear Button
            Button(
                onClick = {
                    memeCounter = 0
                    Toast.makeText(context, (R.string.clear_counter_toast), Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = stringResource(R.string.clearButton))
            }
            // Next Button
            Button(
                onClick = {
                    memeCounter++
                    val message = context.getString(R.string.show_meme_counter, memeCounter)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

                    memeQuote = viewModel.generateRandomMemeQuote(context)
                    memeImage = viewModel.generateRandomMemeImage(context)
                },
            ) {
                Text(text = stringResource(R.string.nextButton))
            }
        }

        //TODO: CUSTOM FEATURE- Buttons to change the screen color
        //      Colors change according to the buttons the user presses
        //      The buttons include "RED" "WHITE (DEFAULT)" "BLACK" "GRAY" and "BLUE"
        //      A toast message also pops out once the background color changes.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 400.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    screenColor = Color.Red
                    Toast.makeText(
                        context,
                        R.string.colorRed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("R", color = Color.Red, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(6.dp)) 

            Button(
                onClick = {
                    screenColor = Color.Blue
                    Toast.makeText(
                        context,
                        R.string.colorBlue,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("B", color = Color.Blue, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = {
                    screenColor = Color.White
                    Toast.makeText(
                        context,
                        R.string.colorDefault,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("DFLT", color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = {
                    screenColor = Color.DarkGray
                    Toast.makeText(
                        context,
                        R.string.colorGray,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("GRAY", color = Color.DarkGray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                onClick = {
                    screenColor = Color.Black
                    Toast.makeText(
                        context,
                        R.string.colorBlack,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Text("BLK", color = Color.Black, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val mockViewModel = MemeViewModel()
    MemezTheme {
        MainScreen(mockViewModel)
    }
}