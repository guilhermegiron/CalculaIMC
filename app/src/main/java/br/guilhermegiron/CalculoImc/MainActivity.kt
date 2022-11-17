package br.guilhermegiron.CalculoImc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.guilhermegiron.CalculaIMC.R
import br.guilhermegiron.CalculaIMC.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarButtomListener()
        configurarRG()
    }

    private fun configurarButtomListener() {
        var nome: String
        binding.btnEnviar.setOnClickListener {
            if (!binding.etNome.text.isNullOrEmpty()) nome =
                binding.etNome.text.toString() else nome = "Anônimo"
            if (binding.etPeso.text.isNullOrEmpty() or binding.etAltura.text.isNullOrEmpty()
                or (!binding.rbIdoso.isChecked && !binding.rbAdulto.isChecked)
            ) {
                Toast.makeText(
                    this, "Preencha o Peso, Altura e o Tipo do Cálculo! ", Toast.LENGTH_LONG
                ).show()
            } else {
                try {
                    val resultado: Double = calcular()
                    if ((resultado == 0.00) or (resultado.isNaN()) ) {
                        Toast.makeText(this, "Peso ou Altura deve ser Maior que 0! ", Toast.LENGTH_LONG).show()
                    } else {
                        val imagem: Int = verificarImagem()
                        val myIntent = Intent(this, TelaResultado::class.java)

                        //Envia os parametros para a Tela de Resultado
                        myIntent.putExtra(TAGIMAGEM, imagem)
                        myIntent.putExtra(TAGRESULTADO, resultado)
                        myIntent.putExtra(TAGNOME, nome)

                        startActivity(myIntent)
                    }
                } catch (ne: NumberFormatException) {
                    Toast.makeText(this, "Verifque o valor de Peso ou Altura! ", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun verificarImagem(): Int {
        //Se imagem = 1 - Alterar Imagem para imc_adultos.png || Se imagem = 2 - Alterar Imagem para imc_idosos.png
        var imagem = 0
        if (binding.rbAdulto.isChecked) imagem = 1
        if (binding.rbIdoso.isChecked) imagem = 2
        return imagem
    }

    private fun calcular(): Double {
        val resultado: Double
        val peso: Double
        val altura: Double

        //Recupera os Valores Informados de Peso e Altura
        peso = binding.etPeso.text.toString().toDouble()
        altura = binding.etAltura.text.toString().toDouble()

        //Realiza o calculo do IMC
        resultado = peso / (altura * altura)

        //Retorna o Valor Calculado
        return resultado
    }

    private fun configurarRG() {
        // Colocar _ para não passar campo obrigatorio
        binding.rgClassificacaoIdade.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAdulto -> {
                    mudarCorBotao(1)
                }
                R.id.rbIdoso -> {
                    mudarCorBotao(2)
                }
            }
        }
    }

    private fun mudarCorBotao(i: Int) {
        val color = when (i) {
            1 -> {
                Log.d("PUCMINAS", "Cor Azul Fraco")
                getColor(R.color.azulclaro)
            }
            else -> {
                Log.d("PUCMINAS", "Cor Verde Escuro")
                getColor(R.color.teal_700)
            }
        }
        binding.btnEnviar.setBackgroundColor(color)
    }

    companion object {
        const val TAGIMAGEM = "IMAGEM"
        const val TAGRESULTADO = "RESULTADO"
        const val TAGNOME = "NOME"
    }
}