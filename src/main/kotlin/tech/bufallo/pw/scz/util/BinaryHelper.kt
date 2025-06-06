package tech.bufallo.pw.scz.util

object BinaryHelper {

    fun convertToBytes(bits: String): ByteArray {
        return bits.reversed()
            .chunked(8)
            .map { it.reversed(); }
            .map { it.toUInt(2).toByte() }
            .reversed()
            .toByteArray()
    }

    fun convertToBinaryString(bytes: ByteArray): String {
        return bytes.joinToString("") {
            it.toUByte()
                .toString(2)
                .padStart(8, '0')
        }
    }

    fun convertToHexString(bytes: ByteArray): String {
        return bytes.joinToString("") {
            it.toUByte()
                .toString(16)
                .padStart(2, '0')
        }
    }
}