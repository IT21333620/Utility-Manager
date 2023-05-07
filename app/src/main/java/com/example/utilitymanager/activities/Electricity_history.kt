package com.example.utilitymanager.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.utilitymanager.R
import com.example.utilitymanager.notification.electricity_warning_notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class electricity_history : AppCompatActivity() {
    private lateinit var entWeek1: EditText
    private lateinit var entWeek2: EditText
    private lateinit var entWeek3: EditText
    private lateinit var entWeek4: EditText
    private lateinit var currUsage: TextView

    private lateinit var btnweek1: Button
    private lateinit var btnweek2: Button
    private lateinit var btnweek3: Button
    private lateinit var btnweek4: Button
    private lateinit var btnDeleteUsage: Button

    private var week1Entered = false
    private var week2Entered = false
    private var week3Entered = false
    private var week4Entered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electricity_history)

        val currUsageTextView = findViewById<TextView>(R.id.txtEstElec)
        val intent = intent
        val estimate = intent.getStringExtra("usage")

        currUsageTextView.text = estimate.toString()

        entWeek1 = findViewById(R.id.week1Units)
        entWeek2 = findViewById(R.id.week2Units)
        entWeek3 = findViewById(R.id.week3Units)
        entWeek4 = findViewById(R.id.week4Units)
        currUsage = findViewById(R.id.totUsage)

        btnweek1 = findViewById(R.id.btnWeek1)
        btnweek2 = findViewById(R.id.btnWeek2)
        btnweek3 = findViewById(R.id.btnWeek3)
        btnweek4 = findViewById(R.id.btnWeek4)
        btnDeleteUsage = findViewById(R.id.btnNewMonth)

        entWeek1.setText(getUsageForWeek(1).toString())
        entWeek2.setText(getUsageForWeek(2).toString())
        entWeek3.setText(getUsageForWeek(3).toString())
        entWeek4.setText(getUsageForWeek(4).toString())

        btnDeleteUsage.setOnClickListener {
            deleteAllUsage()
        }


        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val database = FirebaseDatabase.getInstance().getReference("users").child(uid).child("usage")
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                var totalUsage = 0
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // loop through all child nodes and update usage data if available
                    for (weekSnapshot in dataSnapshot.children) {
                        val weekNum = weekSnapshot.key
                        val weekUsage = weekSnapshot.getValue(Integer::class.java)
                        if (weekNum == "1") {
                            entWeek1.setText(weekUsage.toString())
                            week1Entered = true
                            entWeek1.isEnabled = false
                            entWeek1.setTextColor(resources.getColor(R.color.orange))
                            btnweek1.visibility = View.GONE
                        } else if (weekNum == "2") {
                            entWeek2.setText(weekUsage.toString())
                            week2Entered = true
                            entWeek2.isEnabled = false
                            entWeek2.setTextColor(resources.getColor(R.color.orange))
                            btnweek2.visibility = View.GONE
                        } else if (weekNum == "3") {
                            entWeek3.setText(weekUsage.toString())
                            week3Entered = true
                            entWeek3.isEnabled = false
                            entWeek3.setTextColor(resources.getColor(R.color.orange))
                            btnweek3.visibility = View.GONE
                        } else if (weekNum == "4") {
                            entWeek4.setText(weekUsage.toString())
                            week4Entered = true
                            entWeek4.isEnabled = false
                            entWeek4.setTextColor(resources.getColor(R.color.orange))
                            btnweek4.visibility = View.GONE
                        }
                        totalUsage += weekUsage?.toInt() ?: 0
                    }
                    currUsage.text = totalUsage.toString()

                    var est = estimate?.toDouble()
                    if (est != null) {
                        if ( totalUsage > est*(80/100) ){
                            var intent = Intent(this@electricity_history,
                                electricity_warning_notification::class.java)
                            startActivity(intent)
                        }
                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            })
        }

        btnweek1.setOnClickListener {
            val usage = entWeek1.text.toString().toInt()
            saveUsageForWeek(1, usage)
            week1Entered = true
            btnweek1.visibility = View.GONE
            entWeek1.isEnabled = false
            entWeek1.setTextColor(resources.getColor(R.color.orange))

            updateTotalUsage()
        }
        btnweek2.setOnClickListener {
            val usage = entWeek2.text.toString().toInt()
            saveUsageForWeek(2, usage)
            week2Entered = true
            btnweek2.visibility = View.GONE
            entWeek2.isEnabled = false
            entWeek2.setTextColor(resources.getColor(R.color.orange))

            updateTotalUsage()
        }
        btnweek3.setOnClickListener {
            val usage = entWeek3.text.toString().toInt()
            saveUsageForWeek(3, usage)
            week3Entered = true
            btnweek3.visibility = View.GONE
            entWeek3.isEnabled = false
            entWeek3.setTextColor(resources.getColor(R.color.orange))

            updateTotalUsage()
        }
        btnweek4.setOnClickListener {
            val usage = entWeek4.text.toString().toInt()
            saveUsageForWeek(4, usage)
            week4Entered = true
            btnweek4.visibility = View.GONE
            entWeek4.isEnabled = false
            entWeek4.setTextColor(resources.getColor(R.color.orange))

            updateTotalUsage()
        }

    }
    private fun updateTotalUsage() {
        var totalUsage = 0
        if (week1Entered) {
            totalUsage += entWeek1.text.toString().toInt()
        }
        if (week2Entered) {
            totalUsage += entWeek2.text.toString().toInt()
        }
        if (week3Entered) {
            totalUsage += entWeek3.text.toString().toInt()
        }
        if (week4Entered) {
            totalUsage += entWeek4.text.toString().toInt()
        }
        currUsage.text = totalUsage.toString()
    }

    fun saveUsageForWeek(weekNum: Int, usage: Int) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val database = FirebaseDatabase.getInstance().getReference("users").child(uid).child("usage").child(weekNum.toString())
            database.setValue(usage)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // usage data saved successfully
                    } else {
                        // handle error
                    }
                }
        }
    }

    // get the usage for a given week from the database
    fun getUsageForWeek(week: Int): Int {
        var usage = 0
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val database = FirebaseDatabase.getInstance().getReference("users").child(uid)
            database.child("usage").child("week$week").get()
                .addOnSuccessListener { dataSnapshot ->
                    usage = dataSnapshot.getValue(Int::class.java) ?: 0
                }
                .addOnFailureListener {
                    // handle error
                }
        }
        return usage
    }

    fun deleteAllUsage() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val database = FirebaseDatabase.getInstance().getReference("users").child(uid).child("usage")
            database.removeValue()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // usage data deleted successfully
                        entWeek1.setText("")
                        entWeek2.setText("")
                        entWeek3.setText("")
                        entWeek4.setText("")
                        week1Entered = false
                        week2Entered = false
                        week3Entered = false
                        week4Entered = false
                        entWeek1.isEnabled = true
                        entWeek2.isEnabled = true
                        entWeek3.isEnabled = true
                        entWeek4.isEnabled = true
                        btnweek1.visibility = View.VISIBLE
                        btnweek2.visibility = View.VISIBLE
                        btnweek3.visibility = View.VISIBLE
                        btnweek4.visibility = View.VISIBLE
                        currUsage.text = "0"
                    } else {
                        // handle error
                    }
                }
        }
    }
}