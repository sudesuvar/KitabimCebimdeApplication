package com.sudedenizsuvar.kitabimcebimde

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // bottomNavigationView (alt gezinme çubuğu) öğesine seçilen bir öğe (menuItem)
        // ekleyerek, her bir menuItem'e tıklandığında farklı fragment'leri
        // göstermeyi amaçlıyor.
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener() { menuItem ->
            when (menuItem.itemId) {
                R.id.buton_Giris -> {
                    replaceFragment(BooksPageFragment())
                    true
                }
                R.id.buton_arama -> {
                    replaceFragment(SearchGoogleBooksFragment())
                    true
                }
                R.id.buton_kaydedilenler -> {
                    replaceFragment(SavedBooksPageFragment())
                    true
                }
                R.id.buton_profile -> {
                    replaceFragment(ApplicationProfilePageFragment())
                    true
                }
                R.id.buton_satınAl -> {
                    replaceFragment(ShoppingPageFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(BooksPageFragment())
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()

    }
}