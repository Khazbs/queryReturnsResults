package ru.shindei.qrr

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object ExampleDao : Table("example_table") {

    /**
     * This "cool_column" is added without any problem. Huge success!
     **/
    val noProblemHere = varchar("cool_column", 128)
        .default("I sure do love having a default value")

    /**
     * This "not_so_cool_column" cannot be added to an existing "example_table",
     * unless someone has mercy on it and provides a default value.
     **/
    val yesProblemHere = varchar("not_so_cool_column", 128)
        // .default("If only someone uncommented this line... *wink, wink, nudge, nudge*")

}

fun main() {

    val filePath = "./example.db3"
    val database = Database
        .connect("jdbc:sqlite:$filePath")

    transaction(database) {
        /**
         * Bites the dust with the exception: java.sql.SQLException: Query returns results.
         * Statement(s): ALTER TABLE example_table ADD not_so_cool_column VARCHAR(128) NOT NULL
         **/
        SchemaUtils.createMissingTablesAndColumns(ExampleDao)
    }

    println("I sure hope nothing wacky and uncharacteristic occurs and this gets printed")

}
