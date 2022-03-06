package com.example.aplicatie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.Room
import com.example.aplicatie.database.AppDatabase
import com.example.aplicatie.entities.InfoEmail
import com.example.aplicatie.entities.Listoric
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Email : Fragment(R.layout.fragment_email) {

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().build()

        // Adaug istoricul in ListView
        val randuri = db.istoricDao().getALL()

        val array = mutableListOf<String>()
        for(continut in randuri)
            array.add(continut.rand.toString())

        val adapter = ArrayAdapter(view.context, R.layout.listview_item, array)
        val listView = view.findViewById<ListView>(R.id.continutEmail)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        listView.smoothScrollToPosition(adapter.count - 1)


        val trimite = view.findViewById<Button>(R.id.trimiteEmail)
        val adresa = view.findViewById<AutoCompleteTextView>(R.id.adresa)
        val subiect = view.findViewById<EditText>(R.id.subiect)

        // Adaugare sugestii din baza de date campului de adresa
        var emailArray = arrayListOf<String>()

        for(x in db.emailDao().getALL())
            emailArray.add(x.email.toString())

        val adapterEmail = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, emailArray)
        adresa.setAdapter(adapterEmail)

        // Trimite istoricul pe email
        trimite.setOnClickListener{

            if(!adresa.text.toString().contentEquals("") && !subiect.text.toString().contentEquals(""))
            {
                val dataCurenta = Calendar.getInstance()
                val data = SimpleDateFormat("yyyyMMdd").format(dataCurenta.time)
                val ora = SimpleDateFormat("HH:mm:ss").format(dataCurenta.time)
                val adresaEmail = adresa.text.toString()

                //Inseram in BD
                db.emailDao()
                    .insertAll( InfoEmail(
                        db.emailDao().getALL().size + 1,
                        adresaEmail,
                        data,
                        ora
                    ))

                val email = Intent(Intent.ACTION_SEND)
                email.putExtra(Intent.EXTRA_EMAIL, adresaEmail)
                email.putExtra(Intent.EXTRA_SUBJECT, subiect.text.toString())

                val mesaj = StringBuilder()
                for (i in array) {
                    mesaj.append("$i\n")
                }
                email.putExtra(Intent.EXTRA_TEXT, "$mesaj")

                //need this to prompts email client only
                email.setType("message/rfc822")

                startActivity(Intent.createChooser(email, "Choose an Email client :"))
            }
            else Toast.makeText(context, "Completati adresa si subiectul", Toast.LENGTH_SHORT).show()
        }
    }

}