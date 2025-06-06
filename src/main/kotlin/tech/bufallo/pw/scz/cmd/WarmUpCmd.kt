package tech.bufallo.pw.scz.cmd

import com.github.ajalt.clikt.core.CliktCommand
import tech.bufallo.pw.scz.crc.CrcCalculator

class WarmUpCmd : CliktCommand(name = "warmup") {

    override fun run() {
        repeat(1000) {
            //generates a random bits sequence up to 96 bits and parse it as an Int
            val size = (1..96).random()
            val bits = (1..size).map { (0..1).random() }.joinToString("").toInt(2)

            //calculate CRC
//            CrcCalculator.calculate(bits)
        }
    }
}