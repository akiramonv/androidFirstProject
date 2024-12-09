package com.example.newpoptoot

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                login TEXT UNIQUE,
                email TEXT,
                password TEXT
            )
        """
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    // Метод для добавления пользователя
    fun addUser(user: User) {
        val values = ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("password", user.password)
        }

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    // Метод для авторизации пользователя (проверка логина и пароля)
    fun getUser(login: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE login = ? AND password = ?",
            arrayOf(login, password)
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return exists
    }

    // Метод для проверки существования пользователя с данным логином
    fun isUserExists(login: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE login = ?",
            arrayOf(login)
        )
        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()
        return exists
    }
}
