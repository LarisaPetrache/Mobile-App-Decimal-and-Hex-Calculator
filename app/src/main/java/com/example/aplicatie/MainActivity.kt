package com.example.aplicatie

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val calculator = Calculator()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, calculator).commit()

        // Schimba fragmentele
        var tabLayoutManager = findViewById<TabLayout>(R.id.TabLayoutManager)
        tabLayoutManager.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {

                    if (tab?.text == "calculator") {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<Calculator>(R.id.fragment_container_view)
                        }
                    }

                    if (tab?.text == "istoric") {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<Istoric>(R.id.fragment_container_view)
                        }
                    }

                    if (tab?.text == "email") {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<Email>(R.id.fragment_container_view)
                        }
                    }

                    if (tab?.text == "logs") {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<Logs>(R.id.fragment_container_view)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })


    }
}