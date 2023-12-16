package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityAdminpanelpageBinding


class AdminPanelPageActivity : AppCompatActivity() {
    // Binding tanımlama
    lateinit var binding: ActivityAdminpanelpageBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseFireStore: FirebaseFirestore
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAdminpanelpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Instance
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()


        //Çıkış yap butonuna basıldığında profil sayfasına geri dönmek için
        binding.LogOutButton.setOnClickListener() {
            intent = Intent(applicationContext, ProfilePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.BookSaveButton.setOnClickListener(){
            //hashMapOf<String, Any>() ile boş bir Map oluşturulur. Bu Map, Firestore'a eklenecek olan verileri içerir.
            val Map = hashMapOf<String, Any>()
            // kitap adını ve kitap açıklamasını Map'e ekler.
            Map.put("KitapAdi", binding.AdminBookText.text.toString())
            Map.put("KitapAçiklama", binding.AdminBookCommentText.text.toString())


            //  Map'i FirebaseFirestore içindeki "kitaplar" collection'una ekler.
            firebaseFireStore.collection("kitaplar").add(Map)
                .addOnCompleteListener() { task ->
                    if (task.isComplete && task.isSuccessful) {
                        // görev doğru ve başarılı ise Toast mesajı verir.
                        Toast.makeText(this, "Başarılı Yükleme", Toast.LENGTH_LONG)
                            .show()

                    }

                }.addOnFailureListener {
                    Toast.makeText(
                        this@AdminPanelPageActivity,
                        "Başarısız Yükleme",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

    }
}

