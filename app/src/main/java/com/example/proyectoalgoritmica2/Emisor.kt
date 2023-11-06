package com.example.proyectoalgoritmica2

import java.math.BigInteger

class Emisor {

    companion object {
        fun transformToInt(text: String): BigInteger {
            var num: BigInteger = BigInteger.valueOf(0)
            text.forEach { character ->
                val charCode: Long = character.code.toLong()
                num = num.multiply(BigInteger.valueOf(1000)) + BigInteger.valueOf(charCode)
            }
            return num
        }

        fun encriptMessage(message: String): List<String> {
            val messagePartitioned = mutableListOf<String>()
            var partition = ""

            message.forEach {
                partition += it
                if(partition.length == 6){
                    val numericMessage = transformToInt(partition)
                    val encriptedMessage = Receptor.power(numericMessage, Receptor.e, Receptor.n).toString()
                    messagePartitioned.add(encriptedMessage)
                    partition = ""
                }
            }
            if(partition.isNotEmpty()){
                val numericMessage = transformToInt(partition)
                val encriptedMessage = Receptor.power(numericMessage, Receptor.e, Receptor.n).toString()
                messagePartitioned.add(encriptedMessage)
            }

            return messagePartitioned
        }

    }
}
