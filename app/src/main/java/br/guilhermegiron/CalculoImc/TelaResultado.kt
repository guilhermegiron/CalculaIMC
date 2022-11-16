package br.guilhermegiron.CalculoImc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.guilhermegiron.CalculaIMC.R
import br.guilhermegiron.CalculaIMC.databinding.ActivityTelaResultadoBinding
import java.text.DecimalFormat


class TelaResultado : AppCompatActivity() {

    private lateinit var binding : ActivityTelaResultadoBinding
    var resultado : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarTela()
    }

    private fun configurarTela() {
        val i = intent
        var textoinformativo : String = ""
        var txtabela : String = ""
        var cormsginformativa : Int = 0

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
                textoinformativo = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de " +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico. "
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 18.5 && resultado <= 24.9){
                txtabela = "Peso Normal"
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 25 && resultado <= 29.9){
                txtabela = "Excesso de Peso"
                textoinformativo = "Atenção! Alguns quilos a mais já são suficientes para que algumas pessoas desenvolvam doenças associadas, como " +
                        "diabetes e hipertensão. É importante rever seus hábitos. Procure um médico."
                cormsginformativa = getColor(R.color.amarelofraco)
            }

            if (resultado >= 30 && resultado <= 34.9){
                txtabela = "Obesidade de Classe 1"
                textoinformativo = "Sinal de alerta! O excesso de peso é fator de risco para desenvolvimento de outros problemas de saúde. A boa notícia" +
                        "é que uma pequena perda de peso já traz benefícios à saúde. Procure um médico para definir o tratamento mais adequado para voce"
                cormsginformativa = getColor(R.color.amarelo)
            }

            if (resultado >= 35 && resultado <= 39.9){
                txtabela = "Obesidade de Classe 2"
                textoinformativo = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas está entre alto e muito alto. Busque " +
                        "ajuda de um profissional de saúde; não perca tempo."
                cormsginformativa = getColor(R.color.vermelho)
            }

            if (resultado >= 40){
                txtabela = "Obesidade de Classe 3"
                textoinformativo = "Sinal vermelho! Ao atingir este nível de IMC, o risco de doenças associadas é muito alto. Busque ajuda de um " +
                        "profissional de saúde; não perca tempo."
                cormsginformativa = getColor(R.color.vermelho)
            }
        }

        //Se Foi Selecionado Adulto - Imagem = 1   || Se Foi Selecionado Idoso - Imagem = 2
        if (imagem == 2){
            binding.imTabelaImc.setImageResource(R.drawable.imc_idosos)
            if (resultado <= 22){
                txtabela = "Baixo Peso"
                textoinformativo = "Você está abaixo do peso ideal. Isso pode ser apenas uma característica pessoal, mas também pode ser um sinal de " +
                        "desnutrição ou de algum problema de saúde. Caso esteja perdendo peso sem motivo aparente, procure um médico."
                cormsginformativa = getColor(R.color.amarelofraco)
            }

            if (resultado >= 22 && resultado < 27){
                txtabela = "Adequado ou Eutrófico"
                cormsginformativa = getColor(R.color.azulclaro)
            }

            if (resultado >= 27){
                txtabela = "SobrePeso"
                textoinformativo = "Atenção! Alguns quilos a mais já são \n" + "suficientes para que algumas pessoas desenvolvam doenças associadas," +
                        "\n como diabetes e hipertensão. Procure um médico."
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