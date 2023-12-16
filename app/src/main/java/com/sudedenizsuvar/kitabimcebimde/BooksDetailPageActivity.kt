package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityBooksdetailpageBinding
import com.sudedenizsuvar.kitabimcebimde.databinding.FragmentSavedbookspageBinding

class BooksDetailPageActivity : AppCompatActivity() {
    lateinit var binding: ActivityBooksdetailpageBinding
    lateinit var _binding: FragmentSavedbookspageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booksdetailpage)

        val binding = ActivityBooksdetailpageBinding.inflate(layoutInflater)
        setContentView(binding.root)




        var bundle: Bundle? = intent.extras
        var kitapAdi = bundle!!.getString("constAdi")
        var kitapAciklama = bundle!!.getString("constAciklama")
        var kitapPhoto = bundle!!.getInt("constPhoto")

        binding.detayKitapSmi.text = kitapAdi
        binding.detayKitapAcKlama.text = kitapAciklama
        binding.detayKitapFoto.setImageResource(kitapPhoto)


        // Geri dön butonu

        binding.detaygerigelbutton.setOnClickListener() {
            intent = Intent(applicationContext, BooksPageFragment::class.java)
            startActivity(intent)
            finish()
        }

        binding.detaykaydetbutton.setOnClickListener() {
            val sharedPreferences = MySharedPreferences(this)
            if (kitapAdi != null && kitapAciklama != null && kitapPhoto != null) {
                sharedPreferences.addDataToList("kitapAd", kitapAdi)
                sharedPreferences.addDataToList("kitapAciklama", kitapAciklama)
                sharedPreferences.addDataToList("kitapResim", kitapPhoto.toString())
                println("RESİM:" + kitapAciklama.toString())
                println("RESİM:" + kitapPhoto)
                Toast.makeText(applicationContext, "Kitap Başarıyla Kaydedildi."
                    , Toast.LENGTH_LONG).show()
            }
        }
        binding.detayKaydedilenlerdencKButton.setOnClickListener(){
            val  sharedPreferences = MySharedPreferences(this)

            // Silmek istediğiniz kitabın verilerini temsil eden anahtarları kullanarak silme işlemi
            sharedPreferences.removeData("kitapAd" )
            sharedPreferences.removeData("kitapAciklama")
            sharedPreferences.removeData("kitapResim")
            Toast.makeText(applicationContext, "Kitap Başarıyla Silindi.", Toast.LENGTH_LONG).show()
            /*intent = Intent(applicationContext, SavedBooksPageFragment::class.java)
            startActivity(intent)
            finish()*/

        }
    }
}





