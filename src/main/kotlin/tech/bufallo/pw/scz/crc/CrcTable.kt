package tech.bufallo.pw.scz.crc

object CrcTable {
    const val CRC_LENGTH = 15
    const val POLYNOMIAL = 0x4599
    const val MASK = 1 shl (CRC_LENGTH - 1)
    const val N = 256

    fun calculate(): IntArray {
        val tableCRC = IntArray(N)

        for (i in 0 until N) {
            var crc = (i shl 7) and 0xFFFF  // 8-bit input, aligned to MSB of 15-bit register
            repeat(8) {
                crc = if ((crc and MASK) != 0) {
                    ((crc shl 1) xor POLYNOMIAL) and 0xFFFF
                } else {
                    (crc shl 1) and 0xFFFF
                }
            }
            tableCRC[i] = crc
        }

        return tableCRC
    }
}