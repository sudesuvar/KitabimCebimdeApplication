package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityLoginpageBinding




class LoginPageActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // kullanıcının oturum açıp açmadığını kontrol etmek.
        var currentUser = auth.currentUser
        if (currentUser!= null){
            startActivity(Intent(this@LoginPageActivity, ProfilePageActivity::class.java))
            finish()
        }

        // Giriş yap butonuna tıklandığında
        binding.Inputbutton.setOnClickListener(){

            var girisEmail = binding.InputEmailText.text.toString()
            var girisSifre = binding.InputPasswordText.text.toString()
            if(girisEmail==null){
                binding.InputEmailText.error = "E-mail boş bırakılamaz."
                return@setOnClickListener }
            if (girisSifre==null){
                binding.InputPasswordText.error= "Şifre boş bırakılamaz."
                return@setOnClickListener }

            if(girisEmail != null && girisSifre != null){
                // Giriş bilgilerini doğrulamak ve giriş yapmak
                auth.signInWithEmailAndPassword(girisEmail , girisSifre)
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            intent = Intent(applicationContext, ProfilePageActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Giriş hatalı, lütfen tekrar deneyin."
                                , Toast.LENGTH_LONG).show()
                        }
                    }
            }



        }

        // Yeni üyelik sayfasına gitmek için
        binding.NewMemberText.setOnClickListener(){
            intent = Intent(applicationContext, NewMemberPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Parolamı unuttum sayfasına gitmek için
        binding.InputForgotPasswordText.setOnClickListener(){
            intent = Intent(applicationContext, PasswordResetPageActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}