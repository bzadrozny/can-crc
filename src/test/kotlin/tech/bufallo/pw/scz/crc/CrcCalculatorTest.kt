package tech.bufallo.pw.scz.crc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@OptIn(ExperimentalUnsignedTypes::class)
class CrcCalculatorTest {

    companion object {
        @JvmStatic
        fun crcTestCases() = listOf(
            // message, expected CRC
            // Values confirmed with https://emn178.github.io/online-tools/crc/
            ubyteArrayOf() to 0x0000,
            ubyteArrayOf(0b11100101U) to 0x17ae,
            ubyteArrayOf(0xE5U) to 0x17ae,
            ubyteArrayOf(0b110011U) to 0x7D8B,
            ubyteArrayOf(0x33U) to 0x7D8B,
            ubyteArrayOf(0x01U) to 0x4599,
            ubyteArrayOf(0x01U, 0xABU.toUByte(), 0x1DU, 0x01U, 0xFF.toUByte(), 0x1U) to 0x6251,
            ubyteArrayOf(0x01U, 0x10U, 0x00U, 0x11U, 0x00U, 0x03U, 0x06U, 0x1AU, 0xC4.toUByte(), 0xBA.toUByte(), 0xD0.toUByte()) to 0x4C6D,
        )
    }

    @ParameterizedTest
    @MethodSource("crcTestCases")
    fun `should calculate correct CRC for given input`(testCase: Pair<UByteArray, Int>) {
        val (message, expectedCrc) = testCase
        println("Testing message: ${message.joinToString(", ") { "0x%02X".format(it.toInt()) }} with expected CRC: 0x%04X".format(expectedCrc))

        val actualCrc = CrcCalculator.calculate(message)
        assertEquals(
            expectedCrc,
            actualCrc,
            "CRC mismatch. Expected CRC: 0x%04X. Actual CRC: 0x%04X".format(expectedCrc, actualCrc)
        )
    }
}
