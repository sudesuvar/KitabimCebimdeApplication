package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityNewmemberpageBinding

class NewMemberPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewmemberpageBinding
    private lateinit var auth:FirebaseAuth
    var databaseReference:DatabaseReference?=null
    var database:FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewmemberpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // getInstance -->
        auth = FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("profile")

        // kaydet butonuna tıklandığında firebase'e kaydetme işlemi
        binding.NewSaveButton.setOnClickListener(){

                var uyeIsimSoyisim=binding.NewNameSurnameText.text.toString()
                var uyeMail=binding.NewEmailText.text.toString()
                var uyeSifre=binding.NewPasswordText.text.toString()
                if(TextUtils.isEmpty(uyeIsimSoyisim))
                {
                    binding.NewNameSurnameText.error="Lütfen isim ve soyisminizi giriniz"
                    return@setOnClickListener
                }
                else if(TextUtils.isEmpty(uyeMail))
                {
                    binding.NewEmailText.error="Lütfen mail adresinizi giriniz"
                    return@setOnClickListener
                }
                else if(TextUtils.isEmpty(uyeSifre))
                {
                    binding.NewPasswordText.error="Lütfen şifrenizi giriniz"
                    return@setOnClickListener
                }

                //Email,parola ve kullanıcı bilgilerini veri tabanına ekleme.
                auth.createUserWithEmailAndPassword(binding.NewEmailText.text.toString(),binding.NewPasswordText.text.toString())
                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful)
                        {
                            //Şuanki kullanıcı bilgilerini alalım.
                            var currentUser = auth.currentUser

                            //Kullanıcı id'sini alıp o id adı altında adımızı ve soyadımızı kaydedelim.
                            var currentUserDb=currentUser?.let { it1 ->databaseReference?.child(it1.uid) }
                            currentUserDb?.child("adisoyadi")?.setValue(binding.NewNameSurnameText.text.toString())
                            currentUserDb?.child("mail")?.setValue((binding.NewEmailText.text.toString()))
                            Toast.makeText(this@NewMemberPageActivity,"Kayıt Başarılı",Toast.LENGTH_LONG).show()

                            /*intent= Intent(applicationContext,HomeActivity::class.java) /* Kayıt başarılı ise kayıt olduktan sonra
                        direkt olarak uygulamanın içine atsın. */
                            startActivity(intent)
                            finish()*/

                        }
                        else
                        {
                            Toast.makeText(this@NewMemberPageActivity, task.exception?.message,Toast.LENGTH_LONG).show()
                        }
                    }

            }
        // Giriş yap butonuna basıldığında giriş sayfasına gitmek için --->
        binding.NewLoginButton.setOnClickListener(){
            intent = Intent(applicationContext,LoginPageActivity::class.java)
            startActivity(intent)
            // UyeGirisActivity sayfasını kapatmak için ---> finish()
            finish()
        }


    }
}