package com.example.maparound.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.maparound.R

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize().fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var height = 10.dp
        //ICON
        Icon(
            painter = painterResource(R.drawable.logo_black),
            contentDescription = null,
            modifier = Modifier
                .size(230.dp)
                .padding(top = 10.dp)
        )

        //LOGIN TEXT
        Text(
            text = "Login" ,
            modifier = Modifier
                .padding(end = 266.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp
        )

        //EMAIL FIELD
        val errorMessage = "Text input too long"
        var text by rememberSaveable { mutableStateOf("") }
        var isError by rememberSaveable { mutableStateOf(false) }
        val charLimit = 10
        fun validate(text: String) {
            isError = text.length > charLimit
        }


        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Email") },
            singleLine = true,
            placeholder = { Text("example@gmail.com") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 3.dp),
        )

        //PASSWORD FIELD
        var password by rememberSaveable { mutableStateOf("") }
        var passwordHidden by rememberSaveable { mutableStateOf(true) }
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Enter password") },
            visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    // Please provide localized description for accessibility services
                    val description = if (passwordHidden) "Show password" else "Hide password"
                    Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp,vertical = 3.dp),
        )

        //LOGIN BUTTON
        Button(
            onClick = { /* Do something! */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp,vertical = 3.dp)
                .height(50.dp)
            ,
            shape = RoundedCornerShape(2.dp)
        ) {
            Text("Iniciar sesión")
        }
        val bottomOptionFontSize = 16.sp
        Text(
            text = "¿Se te ha olvidado la contraseña?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = bottomOptionFontSize,
            modifier = Modifier.padding(end=110.dp, top = 20.dp)
        )
        Text(
            text = "¿No tienes cuenta?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = bottomOptionFontSize,
            modifier = Modifier.padding(end=215.dp, top = 20.dp)
        )
        Text(
            text = "Política de privacidad",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = bottomOptionFontSize,
            modifier = Modifier.padding(end=195.dp, top = 20.dp)
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}

