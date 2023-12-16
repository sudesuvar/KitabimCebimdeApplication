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
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityProfilePageBinding

class ProfilePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfilePageBinding
    private lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // getInstance -->
        auth = FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("profile")

        var currentUser = auth.currentUser
        binding.ProfileEmailText.text ="Email: " + currentUser?.email

        // realtime -database'deki id'ye ulaşıp altındaki child'ların içindeki
        // veriyi sayfaya aktarma ---> AD SOYAD AKTARMA
        var userReference = databaseReference?.child(currentUser?.uid!!)
        userReference?.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.ProfileNameSurnameText.text="Ad Soyad: " + snapshot.child("adisoyadi").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        // Kullanıcı girişi butonuna tıklandığında
        // Anasayfaya gitmek için
        binding.UserLoginButton.setOnClickListener(){
            intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Admin girişi butonuna tıklandığında
        binding.AdminLoginbutton.setOnClickListener(){
            intent = Intent(applicationContext, AdminPanelPageActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}