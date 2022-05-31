package br.com.truckhospital.modules.util

import java.util.*

object CNPJUtil {
    fun isValid(CNPJ: String): Boolean {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ == "00000000000000" || CNPJ == "11111111111111" || CNPJ == "22222222222222" || CNPJ == "33333333333333" || CNPJ == "44444444444444" || CNPJ == "55555555555555" || CNPJ == "66666666666666" || CNPJ == "77777777777777" || CNPJ == "88888888888888" || CNPJ == "99999999999999" ||
            CNPJ.length != 14
        ) return false
        val dig13: Char
        val dig14: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        return try {
            // Calculo do 1o. Digito Verificador
            sm = 0
            peso = 2
            i = 11
            while (i >= 0) {

                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (CNPJ[i].code - 48)
                sm += num * peso
                peso += 1
                if (peso == 10) peso = 2
                i--
            }
            r = sm % 11
            dig13 = if (r == 0 || r == 1) '0' else (11 - r + 48).toChar()

            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 2
            i = 12
            while (i >= 0) {
                num = (CNPJ[i].code - 48)
                sm += num * peso
                peso += 1
                if (peso == 10) peso = 2
                i--
            }
            r = sm % 11
            dig14 = if (r == 0 || r == 1) '0' else (11 - r + 48).toChar()

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            dig13 == CNPJ[12] && dig14 == CNPJ[13]
        } catch (e: InputMismatchException) {
            false
        }
    }

    fun mask(CNPJ: String): String {
        // máscara do CNPJ: 99.999.999.9999-99
        return CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
                CNPJ.substring(12, 14)
    }
}