package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityPasswordresetpageBinding


class PasswordResetPageActivity : AppCompatActivity() {
    lateinit var binding:ActivityPasswordresetpageBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPasswordresetpageBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_passwordresetpage)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //Şifremi unuttum butonuna basılınca
        binding.ResetButton.setOnClickListener(){
            var pSifirlaEmail = binding.ResetEmailtext.text.toString().trim()
            if(TextUtils.isEmpty(pSifirlaEmail)){
                binding.ResetEmailtext.error= "Lütfen E-mail adresinizi yazınız."
            }else{
                auth.sendPasswordResetEmail(pSifirlaEmail)
                    .addOnCompleteListener(this){sifirlama ->
                        if(sifirlama.isSuccessful){
                            Toast.makeText(applicationContext, "E-mail Adresinize Sıfırlama Bağlantısı Gönderildi."
                                , Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(applicationContext, "Sıfırlama Bağlantısı Gönderilemedi Tekrar Deneyin."
                                , Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        // Giriş sayfasına gitmek için
        binding.LoginButton.setOnClickListener(){
            intent = Intent(applicationContext, LoginPageActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}