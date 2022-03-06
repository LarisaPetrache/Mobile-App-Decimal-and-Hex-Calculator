package com.example.aplicatie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.Room
import com.example.aplicatie.database.AppDatabase

class Istoric : Fragment(R.layout.fragment_istoric) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        // Adaugam in array-ul listView-ului lista din baza de date
        val randuri = db.istoricDao().getALL()
        val array = mutableListOf<String>()
        for(continut in randuri)
        {
            array.add(continut.rand.toString())
            //Log.e("------ Array:", continut.rand.toString())
        }
        val adapter = ArrayAdapter(view.context, R.layout.listview_item, array)
        val listView = view.findViewById<ListView>(R.id.istoricList)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        listView.smoothScrollToPosition(adapter.count - 1)

        val clear = view.findViewById<Button>(R.id.clearIstoric)
        clear.setOnClickListener{
            db.istoricDao().clearTable()
            db.calculDao().clearTable()
            adapter.clear()
        }

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val itemValue = listView.getItemAtPosition(position) as String

                //Toast the values
                Toast.makeText(
                    activity,
                    "Pozitia: $position\nValoarea: $itemValue",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}