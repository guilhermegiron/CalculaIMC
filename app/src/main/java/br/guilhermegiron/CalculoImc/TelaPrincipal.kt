package br.guilhermegiron.CalculoImc


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.guilhermegiron.CalculaIMC.databinding.ActivityTelaPrincipalBinding

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding : ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}