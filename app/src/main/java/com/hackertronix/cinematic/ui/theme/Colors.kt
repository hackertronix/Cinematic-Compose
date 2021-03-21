package com.hackertronix.cinematic.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xffff8a80)
val primaryDarkColor = Color(0xffc85a54)
val secondaryColor = Color(0xff64ffda)
val secondaryDarkColor = Color(0xff14cba8)

val LightColors = lightColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryDarkColor,
)

val DarkColors = darkColors(
    primary = primaryColor,
    primaryVariant = primaryDarkColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryDarkColor,
    background = Color.Black
)