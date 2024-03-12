package dev.rushia.mynotesapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import java.sql.SQLException
import kotlin.jvm.Throws

class NoteHelper(context: Context) {
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase


    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen) database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DatabaseContract.NoteColumns.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${DatabaseContract.NoteColumns._ID} ASC"
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DatabaseContract.NoteColumns.TABLE_NAME,
            null,
            "${DatabaseContract.NoteColumns._ID} = ?",
            arrayOf(id),
            null,
            null,
            null,
            null,
        )
    }

    fun insert(values: ContentValues): Long {
        return database.insert(DatabaseContract.NoteColumns.TABLE_NAME, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(
            DatabaseContract.NoteColumns.TABLE_NAME,
            values,
            "${DatabaseContract.NoteColumns._ID} = ?",
            arrayOf(id)
        )
    }

    fun deleteById(id: String): Int {
        return database.delete(
            DatabaseContract.NoteColumns.TABLE_NAME,
            "${DatabaseContract.NoteColumns._ID} = '$id'",
            null
        )
    }

    companion object {
        private const val DATABASE_TITLE = DatabaseContract.NoteColumns.TABLE_NAME
        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper = INSTANCE ?: synchronized(this) {
            INSTANCE ?: NoteHelper(context)
        }
    }


}