package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.maparound.R
import com.example.maparound.ui.navigation.Screen
import com.example.maparound.ui.screens.home.HomeScreenViewModel

@Composable
fun TopBar(
    navController : NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
){
    Column() {
        //Upper Bar
        NavigationBar(modifier = Modifier.height(65.dp)) {
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(50.dp)
                    .align(Alignment.CenterVertically))

            NavigationBarItem(
                icon = { Icon(
                    painter = painterResource(R.drawable.ic_baseline_tune_24),
                    contentDescription = null) },
                selected = false,
                onClick = {  },
            )
        }

        //Divider
        Divider()

        //Down Bar
        //var selectedItemBottomBar by remember { mutableStateOf(0) }
        val items = listOf("Scroll Visualization", "Map Visualization", "AR Visualization")
        val icons = listOf(
            R.drawable.ic_baseline_view_list_24,
            R.drawable.map,
            R.drawable.ic_baseline_view_in_ar_24)
        NavigationBar(
            modifier = Modifier.height(40.dp),
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(
                        painter = painterResource(icons.get(index)),
                        contentDescription = item) },
                    selected = viewModel.selectedItemBottomBar.value == index,
                    onClick = {
                        //selectedItemBottomBar = index
                        viewModel.selectedItemBottomBar.value = index
                        if(item == "Scroll Visualization"){
                            //navController.navigate(Screen.HomeScreen.route)
                        }else if(item == "Map Visualization"){
                            //navController.navigate(Screen.MapScreen.route)
                        }else if(item == "AR Visualization"){
                            //navController.navigate(Screen.ArScreen.route)
                        }

                    }
                )
            }
        }

    }
}

@Preview
@Composable
fun TopBarPreview(){
    TopBar(rememberNavController())
}

