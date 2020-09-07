package com.example.tbookreader.constant

enum class Theme {
    Dark,Light,Auto;

    companion object{
        fun getTheme():Theme{
            return if (Appconfig.isNightTheme) {
                Dark
            } else Light
        }
    }
}