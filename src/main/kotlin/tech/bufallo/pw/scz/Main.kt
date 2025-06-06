package tech.bufallo.pw.scz

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import tech.bufallo.pw.scz.cmd.CrcCmd
import tech.bufallo.pw.scz.cmd.CrcTableCmd
import tech.bufallo.pw.scz.cmd.ToolCmd
import tech.bufallo.pw.scz.cmd.WarmUpCmd

fun main(args: Array<String>) = ToolCmd()
    .subcommands(CrcCmd(), CrcTableCmd(), WarmUpCmd())
    .main(args)

