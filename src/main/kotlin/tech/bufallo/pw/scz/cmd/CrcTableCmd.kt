package tech.bufallo.pw.scz.cmd

import com.github.ajalt.clikt.core.CliktCommand
import tech.bufallo.pw.scz.crc.CrcTable

class CrcTableCmd : CliktCommand(name = "crcTable") {
    override fun run() {
        println("Generating CRC-15 CAN Lookup Table:")
        CrcTable.calculate()
            .asList()
            .chunked(8)
            .forEach { crc -> println(crc.joinToString { "0x%04X".format(it) }) }
    }
}
