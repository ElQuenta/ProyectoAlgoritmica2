package com.example.proyectoalgoritmica2

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proyectoalgoritmica2.databinding.ActivityDesencriptarBinding

class DesencriptarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDesencriptarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesencriptarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnAction.setOnClickListener {
            binding.Reultado.visibility = View.VISIBLE
        }
        binding.fabCopyText.setOnClickListener {
            val textoACopiar = binding.tvResultado.text
            val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Texto Copiado", textoACopiar)
            clipboardManager.setPrimaryClip(clipData)
        }
    }
}