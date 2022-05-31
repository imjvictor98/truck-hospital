package br.com.truckhospital.modules.util

import java.util.*

object CPFUtil {
    fun isCPF(text: String): Boolean {
        var cpf = text
        cpf = text.replace("-", "").replace(".", "")

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999" || cpf.length != 11) return false
        val dig10: Char
        val dig11: Char
        var sm: Int
        var i: Int
        var r: Int
        var num: Int
        var peso: Int

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        return try {
            // Calculo do 1o. Digito Verificador
            sm = 0
            peso = 10
            i = 0
            while (i < 9) {

                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = cpf[i].code - 48
                sm += num * peso
                peso -= 1
                i++
            }
            r = 11 - sm % 11
            dig10 =
                if (r == 10 || r == 11) '0' else (r + 48).toChar() // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0
            peso = 11
            i = 0
            while (i < 10) {
                num = cpf[i].code - 48
                sm += num * peso
                peso -= 1
                i++
            }
            r = 11 - sm % 11
            dig11 = if (r == 10 || r == 11) '0' else (r + 48).toChar()

            // Verifica se os digitos calculados conferem com os digitos informados.
            dig10 == cpf[9] && dig11 == cpf[10]
        } catch (e: InputMismatchException) {
            false
        }
    }

    fun cleanCpf(cpf: String): String {
        return cpf.replace("-", "").replace(".", "")
    }
}