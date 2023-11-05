package com.example.proyectoalgoritmica2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalgoritmica2.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        fun navEncriptar() {
            val intent = Intent(this, EncriptarActivity::class.java)
            startActivity(intent)
        }

        fun navDesencriptar() {
            val intent = Intent(this, DesencriptarActivity::class.java)
            startActivity(intent)
        }
        binding.btnNavEncriptar.setOnClickListener { navEncriptar() }
        binding.btnNavDesencriptar.setOnClickListener { navDesencriptar() }
    }
}