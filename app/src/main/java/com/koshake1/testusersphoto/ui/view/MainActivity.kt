package com.koshake1.testusersphoto.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.koshake1.testusersphoto.FRAGMENTS
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            navigateTo(UsersFragment.newInstance())
        }
    }

    fun navigateTo(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(FRAGMENTS)
            .commit()
    }
}