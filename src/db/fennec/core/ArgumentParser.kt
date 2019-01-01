package db.fennec.core

import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.apache.commons.cli.ParseException

class ArgumentParser {

    companion object {

        val OPTION_PORT = "p"

        fun options(): Options {
            val portOption = Option.builder(OPTION_PORT)
                    .required(false)
                    .hasArg(true)
                    .type(Int.javaClass)
                    .desc("Sets port Panthera is binding to.")
                    .build()

            val options = Options()
            options.addOption(portOption)
            return options
        }


        fun parse(args: Array<String>): CommandLine? {
            val options = options()

            val parser = DefaultParser()
            var cline: CommandLine? = null
            try {
                cline = parser.parse(options, args)
            } catch (e: ParseException) {
                println("Incorrect arguments specified.")
            }

            return cline
        }
    }
}