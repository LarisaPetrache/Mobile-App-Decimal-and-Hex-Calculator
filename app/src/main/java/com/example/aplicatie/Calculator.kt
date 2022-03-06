package com.example.aplicatie

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.aplicatie.database.AppDatabase
import com.example.aplicatie.entities.Calcul
import com.example.aplicatie.entities.Listoric
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Calculator : Fragment(R.layout.fragment_calculator), View.OnClickListener {

    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var buttonA: Button
    lateinit var buttonB: Button
    lateinit var buttonC: Button
    lateinit var buttonD: Button
    lateinit var buttonE: Button
    lateinit var buttonF: Button

    lateinit var buttonBack: Button
    lateinit var buttonDelete: ImageView
    lateinit var inputBox: TextView
    lateinit var outputBox: TextView
    lateinit var string: String

    lateinit var buttonPlus: Button
    lateinit var buttonMinus: Button
    lateinit var buttonInmultire: Button
    lateinit var buttonEgal: Button

    // Se retin primul numar si operatia selectata
    var nr1 = 0;
    var operatie = ""

    var OK = true // Setam baza 10 ca fiind cea implicita

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button0 = view.findViewById(R.id.button0)
        button1 = view.findViewById(R.id.button1)
        button2 = view.findViewById(R.id.button2)
        button3 = view.findViewById(R.id.button3)
        button4 = view.findViewById(R.id.button4)
        button5 = view.findViewById(R.id.button5)
        button6 = view.findViewById(R.id.button6)
        button7 = view.findViewById(R.id.button7)
        button8 = view.findViewById(R.id.button8)
        button9 = view.findViewById(R.id.button9)
        buttonA = view.findViewById(R.id.buttonA)
        buttonB = view.findViewById(R.id.buttonB)
        buttonC = view.findViewById(R.id.buttonC)
        buttonD = view.findViewById(R.id.buttonD)
        buttonE = view.findViewById(R.id.buttonE)
        buttonF = view.findViewById(R.id.buttonF)

        inputBox = view.findViewById(R.id.inputBox)
        outputBox = view.findViewById(R.id.outputBox)

        buttonBack = view.findViewById(R.id.buttonBack)
        buttonDelete = view.findViewById(R.id.deleteIcon)
        buttonPlus = view.findViewById(R.id.buttonPlus)
        buttonMinus = view.findViewById(R.id.buttonMinus)
        buttonInmultire = view.findViewById(R.id.buttonInmultire)
        buttonEgal = view.findViewById(R.id.buttonEgal)

        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonA.setOnClickListener(this)
        buttonB.setOnClickListener(this)
        buttonC.setOnClickListener(this)
        buttonD.setOnClickListener(this)
        buttonE.setOnClickListener(this)
        buttonF.setOnClickListener(this)

        buttonBack.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)
        buttonPlus.setOnClickListener(this)
        buttonInmultire.setOnClickListener(this)
        buttonEgal.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)

        val btnBaza = view.findViewById<Button>(R.id.buttonBaza)

        buttonA.isEnabled = false
        buttonB.isEnabled = false
        buttonC.isEnabled = false
        buttonD.isEnabled = false
        buttonE.isEnabled = false
        buttonF.isEnabled = false
        buttonEgal.isEnabled = false //butonul egal este initial dezactivat

        // Functie care schimba baza
        btnBaza.setOnClickListener {
            if (OK) {
                OK = false //schimbam in baza 16
                btnBaza.setBackgroundColor(Color.parseColor("#5C6BC0")) //setam culoarea pentru baza 16
                btnBaza.setText("Baza 10")
                //Activam butoanele A-F
                buttonA.isEnabled = true
                buttonB.isEnabled = true
                buttonC.isEnabled = true
                buttonD.isEnabled = true
                buttonE.isEnabled = true
                buttonF.isEnabled = true
                inputBox.text = "0"
                outputBox.text = ""
                Snackbar.make(view.findViewById(R.id.buttonBaza), "Baza 16", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                OK = true //schimbam in baza 10
                btnBaza.setBackgroundColor(Color.parseColor("#26A69A")) // Setam culoarea pentru baza 10
                btnBaza.setText("Baza 16")
                //Dezactivam butoanele A-F
                buttonA.isEnabled = false
                buttonB.isEnabled = false
                buttonC.isEnabled = false
                buttonD.isEnabled = false
                buttonE.isEnabled = false
                buttonF.isEnabled = false
                inputBox.text = "0"
                outputBox.text = ""
                Snackbar.make(view.findViewById(R.id.buttonBaza), "Baza 10", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onClick(v: View) {
        buttonEgal.isEnabled =
            true // Se activeaza butonul Egal cand va fi apasat un buton de la tastatura
        when (v.id) {
            R.id.button0 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("0")
            }
            R.id.button1 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("1")
                else inputBox.text = "1"
            }
            R.id.button2 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("2")
                else inputBox.text = "2"
            }
            R.id.button3 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("3")
                else inputBox.text = "3"
            }
            R.id.button4 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("4")
                else inputBox.text = "4"
            }
            R.id.button5 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("5")
                else inputBox.text = "5"
            }
            R.id.button6 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("6")
                else inputBox.text = "6"
            }
            R.id.button7 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("7")
                else inputBox.text = "7"
            }
            R.id.button8 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("8")
                else inputBox.text = "8"
            }
            R.id.button9 -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("9")
                else inputBox.text = "9"
            }
            R.id.buttonA -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("A")
                else inputBox.text = "A"
            }
            R.id.buttonB -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("B")
                else inputBox.text = "B"
            }
            R.id.buttonC -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("C")
                else inputBox.text = "C"
            }
            R.id.buttonD -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("D")
                else inputBox.text = "D"
            }
            R.id.buttonE -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("E")
                else inputBox.text = "E"
            }
            R.id.buttonF -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("0"))
                    inputBox.append("F")
                else inputBox.text = "F"
            }
            R.id.buttonBack -> {
                string = inputBox.text.toString()
                /*
                    Daca inputBox are text in el, cand se apasa butonul Back trebuie
                    sters ultimul caracter, in caz contrar, butonul back nu va face nimic
                */
                if (!string.contentEquals(""))
                    inputBox.text = string.substring(0, inputBox.text.length - 1)
            }
            R.id.deleteIcon -> {
                inputBox.text = "0"
                outputBox.text = ""
                operatie = ""

                buttonDelete.animate().apply {
                    duration = 500
                    rotationYBy(360f)
                }.start()
            }
            R.id.buttonPlus -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("")) {
                    operatie = "+"
                    if (OK) {
                        outputBox.text = string
                        nr1 = string.toInt()
                        outputBox.append(" + ")
                        inputBox.text = ""
                    } else {
                        outputBox.text = string.toUpperCase()
                        nr1 = Integer.parseInt(string, 16)
                        outputBox.append(" + ")
                        inputBox.text = ""
                    }
                } else if (!operatie.contentEquals("+")) {
                    outputBox.text = outputBox.text.substring(0, outputBox.text.length - 2)
                    outputBox.append("+ ")
                    operatie = "+"
                }
            }
            R.id.buttonMinus -> {
                string = inputBox.text.toString()

                if ((string.contentEquals("") || string.contentEquals("0")) && operatie.contentEquals(
                        ""
                    ) && OK
                )
                    inputBox.text = "-"
                else if (!string.contentEquals("") && !string.endsWith("+ ") && !string.endsWith("* ") && !string.endsWith(
                        "- "
                    )
                ) {
                    if (OK) // Daca suntem in baza 10
                    {
                        outputBox.text = string
                        nr1 = string.toInt()
                        outputBox.append(" - ")
                        operatie = "-"
                        inputBox.text = ""
                    } else // Suntem in baza 16
                    {
                        outputBox.text = string.toUpperCase()
                        nr1 = Integer.parseInt(string, 16)
                        outputBox.append(" - ")
                        operatie = "-"
                        inputBox.text = ""
                    }
                } else if (!operatie.contentEquals("-")) {
                    outputBox.text = outputBox.text.substring(0, outputBox.text.length - 2)
                    outputBox.append("- ")
                    operatie = "-"
                }
            }
            R.id.buttonInmultire -> {
                string = inputBox.text.toString()

                if (!string.contentEquals("")) {
                    operatie = "*"
                    if (OK) // Daca suntem in baza 10
                    {
                        outputBox.text = string
                        nr1 = string.toInt()
                        outputBox.append(" * ")
                        inputBox.text = ""
                    } else {
                        outputBox.text = string.toUpperCase()
                        nr1 = Integer.parseInt(string, 16)
                        outputBox.append(" * ")
                        inputBox.text = ""
                    }
                } else if (!operatie.contentEquals("*")) {
                    outputBox.text = outputBox.text.substring(0, outputBox.text.length - 2)
                    outputBox.append("* ")
                    operatie = "*"
                }
            }
            R.id.buttonEgal -> {

                var calcul_imposibil = false
                var calcul = 0
                var string = inputBox.text.toString() // Aici va fi numarul 2

                // Daca avem cele 2 numere (nr1 si n2) si operatia (+,- sau *), atunci butonul = va face calculul
                if (!nr1.toString()
                        .contentEquals("") && !string.contentEquals("") && (operatie.contentEquals("+") || operatie.contentEquals(
                        "-"
                    ) || operatie.contentEquals("*"))
                ) {
                    if (OK) // Daca suntem in baza 10
                    {
                        var nr2_b10 = string // Numarul 2 pentru baza 10

                        if (operatie.contentEquals("+"))
                            calcul = nr1 + nr2_b10.toInt()
                        else if (operatie.contentEquals("-"))
                            calcul = nr1 - nr2_b10.toInt()
                        else if (operatie.contentEquals("*"))
                            calcul = nr1 * nr2_b10.toInt()

                        inputBox.text = calcul.toString()
                        outputBox.text = nr1.toString()
                        outputBox.append(" $operatie $nr2_b10 =")

                        //Ne conectam la BD
                        val db = Room.databaseBuilder(
                            requireActivity().applicationContext,
                            AppDatabase::class.java, "database"
                        ).allowMainThreadQueries().build()

                        //Adaugam in baza de date a calculului
                        val dataCurenta = Calendar.getInstance()
                        val data = SimpleDateFormat("yyyyMMdd").format(dataCurenta.time)
                        val ora = SimpleDateFormat("HH:mm:ss").format(dataCurenta.time)

                        db.calculDao().insertAll(Calcul(db.calculDao().getALL().size + 1, "dec",
                                "$nr1", nr2_b10, operatie, "$calcul", data, ora))

                        // Verificare in Log a tabelului Calcul
                        /*
                        val randuri = db.calculDao().getALL()
                        for(continut in randuri)
                            Log.e("------ Baza 10:", continut.toString())
                         */

                        //Adaugam in baza de date a istoricului
                        db.istoricDao()
                            .insertAll(Listoric(db.istoricDao().getALL().size + 1, "Baza 10"))
                        db.istoricDao().insertAll(
                            Listoric(
                                db.istoricDao().getALL().size + 1,
                                "$nr1 $operatie"
                            )
                        )
                        db.istoricDao()
                            .insertAll(Listoric(db.istoricDao().getALL().size + 1, "$nr2_b10 ="))
                        db.istoricDao().insertAll(
                            Listoric(
                                db.istoricDao().getALL().size + 1,
                                "$calcul = $nr1 $operatie $nr2_b10"
                            )
                        )

//                        // Trimite Request catre server
//                        val queue = Volley.newRequestQueue(context)
//                        val url = "http://idp.upg-ploiesti.ro/z/zdemo9/calcjson"
//                        val json: JSONObject = JSONObject()
//                        json.put("user", "user39")
//                        json.put("base", "dec")
//                        json.put("oper", operatie)
//                        json.put("op1", "$nr1")
//                        json.put("op2", nr2_b10)
//                        //Log.e("------ JSON", json.toString())
//                        // Request response
//                        val stringRequest = JsonObjectRequest(Request.Method.GET, url, json,
//                            {
//                                response ->
//                                Log.e("----- Raspuns", "Response: %s".format(response.toString()))
//                            },
//                            {
//                                error ->
//                                Log.e("---- Eroare", "Connect server: failed")
//                            }
//                        )
//                        queue.add(stringRequest)

                    } else {
                        var nr2_b16 = Integer.parseInt(string, 16)

                        if (operatie.contentEquals("+"))
                            calcul = nr1 + nr2_b16
                        else if (operatie.contentEquals("-"))
                            calcul = nr1 - nr2_b16
                        else if (operatie.contentEquals("*"))
                            calcul = nr1 * nr2_b16

                        if (calcul > 0) {
                            inputBox.text = Integer.toHexString(calcul).uppercase();
                            outputBox.text = Integer.toHexString(nr1).uppercase();
                            outputBox.append(
                                " $operatie ${
                                    Integer.toHexString(nr2_b16).uppercase()
                                } ="
                            )

                            //Ne conectam la DB
                            val db = Room.databaseBuilder(
                                requireActivity().applicationContext,
                                AppDatabase::class.java, "database"
                            ).allowMainThreadQueries().build()

                            //Adaugam in baza de date a calculului
                            val dataCurenta = Calendar.getInstance()
                            val data = SimpleDateFormat("yyyyMMdd").format(dataCurenta.time)
                            val ora = SimpleDateFormat("HH:mm:ss").format(dataCurenta.time)

                            db.calculDao().insertAll(
                                Calcul(
                                    db.calculDao().getALL().size + 1,
                                    "hex",
                                    Integer.toHexString(nr1).uppercase(),
                                    Integer.toHexString(nr2_b16).uppercase(),
                                    operatie,
                                    Integer.toHexString(calcul).uppercase(),
                                    data,
                                    ora
                                )
                            )

                            // Verificare in Log a tabelului Calcul
                            /*
                            val randuri = db.calculDao().getALL()
                            for(continut in randuri)
                                Log.e("------ Baza 16:", continut.toString())
                             */

                            //Adaugam in baza de date a istoricului
                            db.istoricDao()
                                .insertAll(Listoric(db.istoricDao().getALL().size + 1, "Baza 16"))
                            db.istoricDao().insertAll(
                                Listoric(
                                    db.istoricDao().getALL().size + 1,
                                    "${Integer.toHexString(nr1).uppercase()} $operatie"
                                )
                            )
                            db.istoricDao().insertAll(
                                Listoric(
                                    db.istoricDao().getALL().size + 1,
                                    "${Integer.toHexString(nr2_b16).uppercase()} ="
                                )
                            )
                            db.istoricDao().insertAll(
                                Listoric(
                                    db.istoricDao().getALL().size + 1,
                                    "${Integer.toHexString(calcul).uppercase()} = " +
                                            "${Integer.toHexString(nr1).uppercase()} " +
                                            "$operatie ${
                                                Integer.toHexString(nr2_b16).uppercase()
                                            }"
                                )
                            )

                            // Trimite Request catre server
                            val queue = Volley.newRequestQueue(context)
                            val url = "http://idp.upg-ploiesti.ro/z/zdemo9/calcjson"
                            val json: JSONObject = JSONObject()
                            json.put("user", "user39")
                            json.put("base", "hex")
                            json.put("oper", operatie)
                            json.put("op1", Integer.toHexString(nr1).uppercase())
                            json.put("op2", Integer.toHexString(nr2_b16).uppercase())

                            //Log.e("------ JSON", json.toString())

                            // Request response
                            val stringRequest = JsonObjectRequest(Request.Method.GET, url, json,
                                {
                                        response ->
                                    Log.e("----- Raspuns", "Response: %s".format(response.toString()))
                                },
                                {
                                        error ->
                                    Log.e("---- Eroare", "Connect server: failed")
                                }
                            )

                            queue.add(stringRequest)

                        } else {
                            calcul_imposibil = true
                            Toast.makeText(
                                activity,
                                "Nu se poate efectua acest calcul",
                                Toast.LENGTH_SHORT
                            ).show()
                            inputBox.text = ""
                        }
                    }

                    if (!calcul_imposibil) {
                        buttonEgal.isEnabled = false
                        operatie = ""
                        nr1 = 0
                    }
                }
            }
        }
    }
}