package com.example.utilitymanager.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.utilitymanager.R
import com.example.utilitymanager.activities.login_page
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Home_fragment : Fragment() {

    private lateinit var logOut: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_fragment, container, false)
        auth = Firebase.auth
        logOut = view.findViewById<Button>(R.id.logoutbtn)

        logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireContext(), login_page::class.java)
            startActivity(intent)
            requireActivity().finish()


        }
        return view
    }

}