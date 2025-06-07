package tech.bufallo.pw.scz.crc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import tech.bufallo.pw.scz.util.BinaryHelper.convertToBinaryString
import tech.bufallo.pw.scz.util.BinaryHelper.convertToHexString

class CrcCalculatorTest {

    companion object {
        @JvmStatic
        fun crcTestCases() = listOf(
            // message, expected CRC
            // Values confirmed with https://emn178.github.io/online-tools/crc/ and https://ninjacalc.mbedded.ninja/calculators/software/crc-calculator
            byteArrayOf() to 0x0000,
            byteArrayOf(0b11100101.toByte()) to 0x17ae,
            byteArrayOf(0xE5.toByte()) to 0x17ae,
            byteArrayOf(0b10101.toByte(), 0b11001010.toByte(), 0b01010111.toByte(), 0b11100101.toByte()) to 0x1588,
            byteArrayOf(0x15.toByte(), 0xCA.toByte(), 0x57.toByte(), 0xE5.toByte()) to 0x1588,
            byteArrayOf(0b110011.toByte()) to 0x7D8B,
            byteArrayOf(0x33.toByte()) to 0x7D8B,
            byteArrayOf(0x01.toByte()) to 0x4599,
            byteArrayOf(0x01.toByte(), 0xAB.toByte(), 0x1D.toByte(), 0x01.toByte(), 0xFF.toByte(), 0x1.toByte()) to 0x6251,
            byteArrayOf(
                0x01.toByte(),
                0x10.toByte(),
                0x00.toByte(),
                0x11.toByte(),
                0x00.toByte(),
                0x03.toByte(),
                0x06.toByte(),
                0x1A.toByte(),
                0xC4.toByte(),
                0xBA.toByte(),
                0xD0.toByte()
            ) to 0x4C6D,
        )
    }

    @ParameterizedTest
    @MethodSource("crcTestCases")
    fun `should calculate correct CRC for given input`(testCase: Pair<ByteArray, Int>) {
        val (message, expectedCrc) = testCase
        println(
            "Testing message: ${convertToBinaryString(message)} - ${convertToHexString(message)} with expected CRC: 0x%04X"
                .format(expectedCrc)
        )

        val actualCrc = CrcCalculator.calculate(message)
        assertEquals(
            expectedCrc,
            actualCrc,
            "CRC mismatch. Expected CRC: 0x%04X. Actual CRC: 0x%04X".format(expectedCrc, actualCrc)
        )
    }
}
