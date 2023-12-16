package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityUpdateinformationpageBinding

class UpdateInformationPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateinformationpageBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateinformationpage)
        val binding = ActivityUpdateinformationpageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("profile")


        var currentUser= auth.currentUser
        binding.UpdateEmailText.setText(currentUser?.email)
        //realtime database de bulunan kullanıcının ID sine erişip ad soyadını alma
        var userReference = databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.UpdateNameSurnameText.setText(snapshot.child("adisoyadi").value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // Bilgilerimi güncelle butonuna basıldığında
        binding.UpdateButton.setOnClickListener(){
            //E mail güncelleme
            var guncelleemail = binding.UpdateEmailText.text.toString().trim()
            currentUser!!.updateEmail(guncelleemail)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "E mail güncellendi",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext, "E mail güncelleme başarısız.",
                            Toast.LENGTH_LONG).show()
                    }
                }

            //Parola güncelleme
            var guncelleParola = binding.UpdatePasswordText.text.toString().trim()
            currentUser!!.updatePassword(guncelleParola)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Parola güncellendi",
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(applicationContext, "Parola güncelleme başarısız.",
                            Toast.LENGTH_LONG).show()
                    }
                }

                // ad soyad güncelleme
            var currentUserDb = currentUser?.let { it1 -> databaseReference?.child(it1.uid) }
            currentUserDb?.removeValue()
            currentUserDb?.child("adisoyadi")?.setValue(binding.UpdateNameSurnameText.text.toString())
            Toast.makeText(applicationContext, "Adı ve Soyadı güncellendi.",
                Toast.LENGTH_LONG).show()
        }

        binding.UpdateLoginButton.setOnClickListener(){
            intent = Intent(applicationContext, ProfilePageActivity::class.java)
            startActivity(intent)
            finish()

        }


    }
}