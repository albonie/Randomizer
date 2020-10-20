package com.example.losowanieaplikacja

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

var osoby = ArrayList<String>()
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wyswietlanie = findViewById<TextView>(R.id.lista)
        val imie = findViewById<EditText>(R.id.imie)
        val losowanie = findViewById<Button>(R.id.losowanie)
        val dodawanie = findViewById<Button>(R.id.dodawanie)
        val ileElementow = findViewById<TextView>(R.id.liczbaElementow)
        val rozmiar = findViewById<EditText>(R.id.numer)
        wyswietlanie.movementMethod = ScrollingMovementMethod()
        fun akcja() {
            var licznik = 0
            ileElementow.text = "0 elementów"
            wyswietlanie.text = ""
            dodawanie.setOnClickListener {
                if (imie.text.isNotEmpty()) {
                    osoby.add(imie.text.toString())
                    licznik++
                    ileElementow.text = "$licznik elementów"
                    wyswietlanie.text = "${wyswietlanie.text}\n$licznik. ${osoby[licznik - 1]}"
                    imie.text.clear()
                } else Toast.makeText(
                        applicationContext,
                        "Musisz coś wpisać!", Toast.LENGTH_SHORT
                ).show()
            }
            losowanie.setOnClickListener {
                if (licznik > 0) {
                    osoby.shuffle()
                    wyswietlanie.text = ""
                    var buff = 1
                    for (i in 0 until osoby.size) {
                        wyswietlanie.text = "${wyswietlanie.text}\n${i + 1}. ${osoby[i]}"
                        if (rozmiar.text.isNotEmpty()) {
                            if (buff == (rozmiar.text.toString().toInt())) {
                                wyswietlanie.text = "${wyswietlanie.text}\n"
                            }
                            if (buff == rozmiar.text.toString().toInt()) buff = 1
                            else buff++
                        }
                    }
                    dodawanie.text = "Resetuj"
                    dodawanie.setOnClickListener {
                        licznik = 0
                        osoby.clear()
                        dodawanie.text = "Dodaj"
                        imie.text.clear()
                        rozmiar.text.clear()
                        akcja()
                    }
                } else Toast.makeText(
                        applicationContext,
                        "Musisz coś wpisać!", Toast.LENGTH_SHORT
                ).show()
            }
            losowanie.setOnLongClickListener {
                Toast.makeText(
                        applicationContext,
                        "Easter egg!", Toast.LENGTH_SHORT).show()
                true
            }
        }
        akcja()
    }
}





