package com.example.proyectoalgoritmica2

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoalgoritmica2.databinding.ActivityEncriptarBinding

class EncriptarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEncriptarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEncriptarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnAction.setOnClickListener {
            binding.Reultado.visibility = View.VISIBLE
            val encryptedList = Emisor.encriptMessage(binding.message.text.toString())
            val encryptedListSpace = encryptedList.joinToString( separator = " ")
            binding.tvResultado.setText(encryptedListSpace)
        }
        binding.fabCopyText.setOnClickListener {
            val textoACopiar = binding.tvResultado.text
            val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Texto Copiado", textoACopiar)
            clipboardManager.setPrimaryClip(clipData)
        }
    }
}