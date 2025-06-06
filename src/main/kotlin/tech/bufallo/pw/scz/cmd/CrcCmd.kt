package tech.bufallo.pw.scz.cmd

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.arguments.validate
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import tech.bufallo.pw.scz.crc.CrcCalculator
import tech.bufallo.pw.scz.crc.CrcTable

class CrcCmd : CliktCommand(name = "crc") {

    private val input: String by argument("message", help = "Binary sequence (max 96 bytes, e.g. 0110 0011 1 00 ...)")
        .convert { it.replace(" ", "") }
        .validate {
            if (it.all { c -> c == '0' || c == '1' }.not() || it.length > 96) {
                fail("Input sequence must be max 96 bits and must be in binary notation, e.g. 1 00 11 000 1111")
            }
        }

    private val iterations: Long by option("--iterations", "-i", help = "Number of iterations to run (default: 10000)")
        .convert { it.toLong() }
        .default(10000)
        .validate { if (it <= 0 || it > 1_000_000_000) fail("Iterations must be between 1 and 10^9") }

    override fun run() {
        val processingIterations = iterations.toInt()
        println("Iterations = $processingIterations")
        val bytes = convertToBytes(input)

        var crc = 0
        val startTime = System.nanoTime()
        repeat(processingIterations) {
            crc = CrcCalculator.calculate(bytes)
        }
        val endTime = System.nanoTime()

        println("CRC = 0x%04X".format(crc))
        println("Time taken: %.6f seconds".format((endTime - startTime) / 1e9))
    }

    private fun convertToBytes(bits: String): ByteArray {
        return bits.reversed()
            .chunked(8)
            .map { it.reversed(); }
            .map { it.toByte(2) }
            .reversed()
            .toByteArray()
    }
}
