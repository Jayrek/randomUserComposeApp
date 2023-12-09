package com.jrektabasa.randomuser.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jrektabasa.randomuser.R

// Set of Material typography styles to start with

val appFontFamily = FontFamily(
    fonts = listOf(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_light, FontWeight.W500),
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_italic, FontWeight.Normal)
    )
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

)
//private val defaultTypography = Typography()
//
//private val appFontFamily = FontFamily(
//    fonts = listOf(
//        Font(R.font.poppins_regular, FontWeight.Normal),
//        Font(R.font.poppins_light, FontWeight.W500),
//        Font(R.font.poppins_bold, FontWeight.Bold),
//        Font(R.font.poppins_italic, FontWeight.Normal)
//    )
//)
//
//val appTypography = Typography(
//    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = appFontFamily),
//    h2 = defaultTypography.h2.copy(fontFamily = appFontFamily),
//    h3 = defaultTypography.h3.copy(fontFamily = appFontFamily),
//    h4 = defaultTypography.h4.copy(fontFamily = appFontFamily),
//    h5 = defaultTypography.h5.copy(fontFamily = appFontFamily),
//    h6 = defaultTypography.h6.copy(fontFamily = appFontFamily),
//    subtitle1 = defaultTypography.subtitle1.copy(fontFamily = appFontFamily),
//    subtitle2 = defaultTypography.subtitle2.copy(fontFamily = appFontFamily),
//    body1 = defaultTypography.body1.copy(fontFamily = appFontFamily),
//    body2 = defaultTypography.body2.copy(fontFamily = appFontFamily),
//    button = defaultTypography.button.copy(fontFamily = appFontFamily),
//    caption = defaultTypography.caption.copy(fontFamily = appFontFamily),
//    overline = defaultTypography.overline.copy(fontFamily = appFontFamily)
//)

