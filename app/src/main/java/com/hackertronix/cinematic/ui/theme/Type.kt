package com.hackertronix.cinematic.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.hackertronix.cinematic.R

val rubik = FontFamily(
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_one, FontWeight.ExtraBold)
)

val chivo = FontFamily(
    Font(R.font.chivo_regular, FontWeight.Normal),
    Font(R.font.chivo_bold, FontWeight.Bold)
)

val  typography = Typography(

    h4 = TextStyle(
        fontSize = 36.sp,
        fontFamily = rubik,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = (-8).sp
    ),

    h5 = TextStyle(
        fontSize = 24.sp,
        fontFamily = chivo,
        fontWeight = FontWeight.Bold
    ),

    h6 = TextStyle(
        fontSize = 22.sp,
        fontFamily = chivo,
        fontWeight = FontWeight.Bold
    ),

    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontFamily = chivo,
        fontWeight = FontWeight.Normal
    ),

    subtitle2 = TextStyle(
        fontSize = 16.sp,
        fontFamily = chivo,
        fontWeight = FontWeight.Bold
    ),

    body2 = TextStyle(
        fontSize = 14.sp,
        fontFamily = chivo,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    ),

    button = TextStyle(
        fontSize = 14.sp,
        fontFamily = rubik,
        fontWeight = FontWeight.Normal
    )
)