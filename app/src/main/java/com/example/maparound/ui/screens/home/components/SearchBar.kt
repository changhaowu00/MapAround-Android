package com.example.maparound.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maparound.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        value = "",
        onValueChange = {
        },
        enabled = false,
        trailingIcon =  {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                //modifier = modifier
            )
        },
        placeholder = {Text(text = "Buscar", modifier = modifier)}
    )
}

@Preview
@Composable
fun PreviewSearchBar(){
    SearchBar(modifier = Modifier.height(55.dp))
}
