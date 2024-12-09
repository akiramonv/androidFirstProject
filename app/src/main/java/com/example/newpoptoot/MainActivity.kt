package com.example.newpoptoot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPassword: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            // Инициализация базы данных
            val db = DbHelper(this, null)

            // Проверка на пустые поля
            if (login.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все данные", Toast.LENGTH_LONG).show()
            }
            // Проверка уникальности логина
            else if (db.isUserExists(login)) {
                Toast.makeText(this, "Логин уже занят, выберите другой", Toast.LENGTH_LONG).show()
            }
            // Проверка email
            else if (!isValidEmail(email)) {
                Toast.makeText(this, "Введите корректный email", Toast.LENGTH_LONG).show()
            }
            // Проверка длины пароля
            else if (!isValidPassword(password)) {
                Toast.makeText(this, "Пароль должен быть не менее 6 символов", Toast.LENGTH_LONG).show()
            }
            // Если все проверки пройдены
            else {
                val user = User(login, email, password)
                db.addUser(user)

                Toast.makeText(this, "Пользователь $login добавлен", Toast.LENGTH_LONG).show()

                // Очистка полей после успешной регистрации
                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()
            }
        }
    }

    // Функция для проверки email
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        return email.matches(emailPattern.toRegex())
    }

    // Функция для проверки длины пароля
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
}
