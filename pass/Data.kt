package com.example.sqlite

import java.io.Serializable

class Data(n: String,p: String) : Serializable{
    var name = n
    var password = p
}