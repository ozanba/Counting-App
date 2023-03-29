package com.ozanzero.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ozanzero.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {

    var tapCounter = remember {
        mutableStateOf(0)
    }
    var multiple = remember {
        mutableStateOf(1)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), color = Color(0xFF546E7A)
    ) {


        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$ ${tapCounter.value}", style = TextStyle(
                    color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.Bold

                )
            )
            Spacer(modifier = Modifier.height(130.dp))
            // CreatCircle fonskiyonu çalıştığında gönderidiğimiz tap counter değerini alır
            // ve updateTapcounter fonksiyonu çalıştıır ve bu fonskiyon . söz edilen foksiyon da parametre olarak gönderilir.
            // + and -
            Row() {

                CreateCircle("Tap +", tapCounter = tapCounter.value, stl = true) {
                    tapCounter.value = it + 1*multiple.value
                }
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                CreateCircle("Tap -", tapCounter = tapCounter.value, stl = true) {
                    tapCounter.value = it - 1*multiple.value
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            // NUMBERS
            Row() {
                CreateCircle("5", tapCounter = tapCounter.value, stl = false) {
                    multiple.value = 5
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                CreateCircle("10", tapCounter = tapCounter.value, stl = false) {
                    multiple.value = 10
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                CreateCircle("50", tapCounter = tapCounter.value, stl = false) {
                    multiple.value = 50
                }


            }
        }


    }

}

@Composable
fun CreateCircle(text: String, tapCounter: Int, stl: Boolean, updateTapCounter: (Int) -> Unit) {
    val cSize = if (stl) 150 else 60
    var color = remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .size(cSize.dp)
            .clickable
            {
                updateTapCounter(tapCounter)
                color.value = !color.value

            },
        shape = CircleShape, backgroundColor = if (color.value) Color.Yellow else Color.White
    ) {
        Box(
            modifier = Modifier, contentAlignment = Alignment.Center
        ) {

            Text(text = "$text", fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MyApp()

    }
}