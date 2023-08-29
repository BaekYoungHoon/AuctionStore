package com.hoon.AuctionStore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

class Fragment_Chat : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Greeting("fuck!")
                exgam()
                MaterialTheme {
                    // In Compose world
                    Text("Hello 한글!")

                }
            }
        }
    }
}
@Composable
fun Greeting(name: String){
    MaterialTheme{
        Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue) // 여기에서 원하는 색상을 지정하세요.
    )
        Text(text = "hello 안녕 $name")
    }


}
@Composable
fun exgam(){
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "A day wandering through the sandhills " +
                        "in Shark Fin Cove, and a few of the " +
                        "sights I saw",
                style = typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Davenport, California",
                style = typography.body2
            )
            Text(
                text = "December 2018",
                style = typography.body2
            )
        }
    }
}
@Preview
@Composable
fun PreviewGreeting(){
    Greeting("병신")
}