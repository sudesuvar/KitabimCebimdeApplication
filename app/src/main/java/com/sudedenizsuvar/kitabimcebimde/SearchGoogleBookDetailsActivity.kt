package com.sudedenizsuvar.kitabimcebimde

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivitySearchGoogleBookDetailsBinding


class SearchGoogleBookDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchGoogleBookDetailsBinding
    // creating variables for strings,text view, image views and button.
    var title: String? = null
    var subtitle: String? = null
    var publisher: String? = null
    var publishedDate: String? = null
    var description: String? = null
    var thumbnail: String? = null
    var previewLink: String? = null
    var infoLink: String? = null
    var pageCount = 0
    private val authors: ArrayList<String>? = null
    var titleTV: TextView? = null
    var subtitleTV: TextView? = null
    var publisherTV: TextView? = null
    var descTV: TextView? = null
    var pageTV: TextView? = null
    var publishDateTV: TextView? = null
    var previewBtn: Button? = null
    private var bookIV: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchGoogleBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // initializing our views..
        titleTV = findViewById(R.id.idTVTitle)
        subtitleTV = findViewById(R.id.idTVSubTitle)
        publisherTV = findViewById(R.id.idTVpublisher)
        descTV = findViewById(R.id.idTVDescription)
        pageTV = findViewById(R.id.idTVNoOfPages)
        publishDateTV = findViewById(R.id.idTVPublishDate)
        previewBtn = findViewById(R.id.searchdetailsBack)
        bookIV = findViewById(R.id.idIVbook)

        // getting the data which we have passed from our adapter class.
        title = intent.getStringExtra("title")
        subtitle = intent.getStringExtra("subtitle")
        publisher = intent.getStringExtra("publisher")
        publishedDate = intent.getStringExtra("publishedDate")
        description = intent.getStringExtra("description")
        pageCount = intent.getIntExtra("pageCount", 0)
        thumbnail = intent.getStringExtra("thumbnail")
        previewLink = intent.getStringExtra("previewLink")
        infoLink = intent.getStringExtra("infoLink")


        binding.idTVTitle.setText(title)
        binding.idTVSubTitle.setText(subtitle)
        binding.idTVpublisher.setText(publisher)
        binding.idTVPublishDate.setText("Published On : $publishedDate")
        binding.idTVDescription.setText(description)
        binding.idTVNoOfPages.setText("No Of Pages : $pageCount")
        Picasso.get().load(thumbnail).into(bookIV)


        binding.searchdetailsBack.setOnClickListener(View.OnClickListener {
            if (previewLink!!.isEmpty()) {

                Toast.makeText(this@SearchGoogleBookDetailsActivity, "No preview Link present", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }

            val uri = Uri.parse(previewLink)

            intent = Intent(applicationContext, SearchGoogleBooksFragment::class.java)
            startActivity(intent)
            finish()
        })


    }
}