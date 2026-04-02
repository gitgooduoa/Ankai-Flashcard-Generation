package dev.apollointhehouse.plugins

import dev.apollointhehouse.database.FlashcardService
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases(): FlashcardService {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = "",
    )
    return FlashcardService(database)
}
