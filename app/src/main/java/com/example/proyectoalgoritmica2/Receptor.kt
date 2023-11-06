package com.example.proyectoalgoritmica2

import java.math.BigInteger

class Receptor {
    companion object {

        private val p = BigInteger.valueOf(1000000009)  // 1e9+7
        private val q = BigInteger.valueOf(1000000007)  // 1e9+9
        val n = p * q
        val e: BigInteger = BigInteger.valueOf(5)
        private val d = modularInverse(
            e,
            (p - BigInteger.valueOf(1)) * (q - BigInteger.valueOf(1))
        )

        fun extendedEuclidian(a: BigInteger, b: BigInteger): Pair<BigInteger, BigInteger> {
            var x = BigInteger.ONE
            var y = BigInteger.ZERO
            var x1 = BigInteger.ZERO
            var y1 = BigInteger.ONE
            var a1 = a
            var b1 = b

            while (b1 != BigInteger.ZERO) {
                val q = a1 / b1
                val tempX = x
                val tempY = y

                x = x1
                x1 = tempX - q * x1

                y = y1
                y1 = tempY - q * y1

                val tempA = a1
                a1 = b1
                b1 = tempA - q * b1
            }

            return Pair(x, y)
        }

        fun modularInverse(b: BigInteger, mod: BigInteger): BigInteger {
            val gcd = extendedEuclidian(b, mod)
            val x: BigInteger = gcd.first
            return (x % mod + mod) % mod
        }

        fun power(b: BigInteger, e: BigInteger, mod: BigInteger): BigInteger{
            if (e == BigInteger.ZERO) return BigInteger.ONE
            if (e == BigInteger.ONE) return b

            var aux = power(b, e.divide(BigInteger.valueOf(2)), mod)
            aux = (aux * aux) % mod

            if (e.mod(BigInteger.valueOf(2)) != BigInteger.ZERO) {
                aux = (aux * b) % mod
            }

            return aux
        }

        data class Congruence(val a: BigInteger, val m: BigInteger)

        fun chineseRemainderTheorem(congruences: List<Congruence>): BigInteger {
            var M = BigInteger.ONE
            congruences.forEach { congruence ->
                M *= congruence.m
            }

            var solution = BigInteger.ZERO
            congruences.forEach { congruence ->
                val a_i = congruence.a
                val M_i = M.divide(congruence.m)
                val N_i = modularInverse(M_i,congruence.m)
                solution = (solution + a_i * M_i * N_i).mod(M)
            }
            return solution
        }

        fun toString(x: BigInteger): String {
            var message = ""
            var number = x

            while (number != BigInteger.ZERO) {
                val remainder = number.mod(BigInteger.valueOf(1000))
                message += (remainder.toInt()).toChar()
                number /= BigInteger.valueOf(1000)
            }

            return message.reversed()
        }

        fun desencryptMessage(messages: List<String>): String{
            var decryptedMessage: String = ""
            messages.forEach{ message ->
                val numericMessage = BigInteger(message)
                val equations = listOf(
                    Congruence(power(numericMessage.mod(p), d, p), p),
                    Congruence(power(numericMessage.mod(q), d, q), q)
                )
                val decrypted = chineseRemainderTheorem(equations)
                decryptedMessage += toString(decrypted)
            }
            return decryptedMessage
        }
    }
}