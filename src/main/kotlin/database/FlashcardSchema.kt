package dev.apollointhehouse.database

import dev.apollointhehouse.models.Flashcard as FlashcardModel
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class FlashcardService(database: Database) {
    object Flashcards : Table() {
        val id = integer("id").autoIncrement()
        val title = varchar("title", length = 50)
        val question = varchar("question", length = 255)
        val answer = varchar("answer", length = 255)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(Flashcards)
        }
    }

    suspend fun create(flashcard: FlashcardModel): Int = dbQuery {
        Flashcards.insert {
            it[title] = flashcard.title
            it[question] = flashcard.question
            it[answer] = flashcard.answer
        }[Flashcards.id]
    }

    suspend fun findByNote(title: String, content: String): FlashcardModel? {
        return dbQuery {
            Flashcards.selectAll()
                .where { (Flashcards.title eq title) and (Flashcards.question eq content) }
                .map { FlashcardModel(it[Flashcards.title], it[Flashcards.question], it[Flashcards.answer]) }
                .singleOrNull()
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}

