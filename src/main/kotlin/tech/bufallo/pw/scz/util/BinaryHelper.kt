package tech.bufallo.pw.scz.util

@OptIn(ExperimentalUnsignedTypes::class)
object BinaryHelper {

    fun convertToBytes(bits: String): UByteArray {
        return bits.reversed()
            .chunked(8)
            .map { it.reversed(); }
            .map { it.toUByte(2) }
            .reversed()
            .toUByteArray()
    }
    
}