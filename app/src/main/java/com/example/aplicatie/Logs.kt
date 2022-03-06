package com.example.aplicatie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Logs : Fragment(R.layout.fragment_logs) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var logsView = view.findViewById<TextView>(R.id.logsView)
    }
}