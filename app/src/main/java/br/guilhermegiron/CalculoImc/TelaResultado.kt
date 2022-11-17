package br.guilhermegiron.CalculoImc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.guilhermegiron.CalculaIMC.R
import br.guilhermegiron.CalculaIMC.databinding.ActivityTelaResultadoBinding
import java.text.DecimalFormat


class TelaResultado : AppCompatActivity() {

    private lateinit var binding : ActivityTelaResultadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarTela()
    }

    private fun configurarTela() {
        val i = intent
        var textoinformativo = ""
        var txtabela = ""
        var cormsginformativa = 0

        //Recuperar os Valores da Intent (Main Activity)
        val nome : String? = i.getStringExtra(MainActivity.TAGNOME)
        val imagem = i.getIntExtra(MainActivity.TAGIMAGEM, 0)
        val resultado = i.getDoubleExtra(MainActivity.TAGRESULTADO, 0.00)

        //Se Foi Selecionado Adulto - Imagem = 1   || Se Foi Selecionado Idoso - Imagem = 2
        if (imagem == 1){
            binding.imTabelaImc.setImageResource(R.drawable.imc_adultos)

            // Compara os Resultados + Insere Texto Informativo + Cor do Texto
            if (resultado <= 18.5){
                txtabela = "Baixo Peso"
                textoinformativo = getString(R.string.baixo_peso)
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 18.5 && resultado <= 24.9){
                txtabela = "Peso Normal"
                textoinformativo = getString(R.string.peso_normal)
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 25 && resultado <= 29.9){
                txtabela = "Excesso de Peso"
                textoinformativo = getString(R.string.excesso_peso)
                cormsginformativa = getColor(R.color.amarelofraco)
            }

            if (resultado >= 30 && resultado <= 34.9){
                txtabela = "Obesidade de Classe 1"
                textoinformativo = getString(R.string.obesidadeI)
                cormsginformativa = getColor(R.color.amarelo)
            }

            if (resultado >= 35 && resultado <= 39.9){
                txtabela = "Obesidade de Classe 2"
                textoinformativo = getString(R.string.obesidadeII)
                cormsginformativa = getColor(R.color.vermelho)
            }

            if (resultado >= 40){
                txtabela = "Obesidade de Classe 3"
                textoinformativo = getString(R.string.obesidadeIII)
                cormsginformativa = getColor(R.color.vermelho)
            }
        }

        //Se Foi Selecionado Adulto - Imagem = 1   || Se Foi Selecionado Idoso - Imagem = 2
        if (imagem == 2){
            binding.imTabelaImc.setImageResource(R.drawable.imc_idosos)
            if (resultado <= 22){
                txtabela = "Baixo Peso"
                textoinformativo = getString(R.string.baixo_peso_idoso)
                cormsginformativa = getColor(R.color.amarelofraco)
            }

            if (resultado >= 22 && resultado < 27){
                txtabela = "Adequado"
                textoinformativo = getString(R.string.peso_normal)
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 27){
                txtabela = "SobrePeso"
                textoinformativo = getString(R.string.sobrepeso_idoso)
                cormsginformativa = getColor(R.color.vermelho)
            }
        }

        //Setar Texto Resultado
        val df = DecimalFormat("#,###.00")

        binding.txResultado.text = "Prezado $nome" + ", seu IMC é de: " + df.format(resultado) +
                " De Acordo Com a Tabela Voce Está em: $txtabela"

        //Setar Texto Informativo + Cor do Texto
        binding.txInformativo.text = textoinformativo
        binding.txInformativo.setTextColor(cormsginformativa)
    }
}