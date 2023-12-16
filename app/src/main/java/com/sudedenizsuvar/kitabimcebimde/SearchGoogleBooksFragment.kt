package com.sudedenizsuvar.kitabimcebimde

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.android.volley.RequestQueue
import com.sudedenizsuvar.kitabimcebimde.databinding.FragmentSearchgooglebooksBinding
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import java.util.ArrayList;


class SearchGoogleBooksFragment : Fragment() {
    lateinit var binding: FragmentSearchgooglebooksBinding

    val apiKey = "AIzaSyAbSqRTqWe2XQ0STbZbLvd-tA06IWOYcH4"


    private var mRequestQueue: RequestQueue? = null
    private var bookInfoArrayList: ArrayList<BookInfo>? = null
    private var progressBar: ProgressBar? = null
    private var searchEdt: EditText? = null
    private var searchBtn: Button? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchgooglebooksBinding.inflate(inflater, container, false)
        val view = binding.root
        progressBar = binding.idLoadingPB
        searchEdt = binding.idEdtSearchBooks
        searchBtn = binding.idBtnSearch
        recyclerView = binding.idRVBooks

        val linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        // BookInfo ve RequestQueue nesnelerini oluşturma
        bookInfoArrayList = ArrayList()
        mRequestQueue = Volley.newRequestQueue(requireContext())


        // Arama yap butonuna basıldığında
        binding.idBtnSearch.setOnClickListener(){
            progressBar!!.visibility = View.VISIBLE

            if (searchEdt!!.text.toString().isEmpty()) {
                searchEdt!!.error = "Please enter search query"
                return@setOnClickListener
            }

           getBooksInfo(binding.idEdtSearchBooks.text.toString())
        }


        return view
    }
    private fun getBooksInfo(query: String) {
        //getBooksInfo fonksiyonu: query adında bir dize parametresi alır.
        //bu parametre ile Google Books API'sine yapılan istek sonucunda kitap bilgilerini alır.
        val url = "https://www.googleapis.com/books/v1/volumes?q=$query"


        //JsonObjectRequest kullanarak Google Books API'sine yapılan GET isteği ile kitaplar hakkında bilgi alınır.
        //Cevap başarıyla alındığında (onResponse), cevapta bulunan kitap bilgileri ayrıştırılır ve BookInfo sınıfının nesneleri oluşturulur.
        //Bu bilgiler, bookInfoArrayList adlı bir ArrayList'e eklenir.
        val booksObjrequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                progressBar!!.visibility = View.GONE
                try {
                    val itemsArray = response.getJSONArray("items")
                    for (i in 0 until itemsArray.length()) {
                        val itemsObj = itemsArray.getJSONObject(i)
                        val volumeObj = itemsObj.getJSONObject("volumeInfo")
                        val title = volumeObj.optString("title")
                        val subtitle = volumeObj.optString("subtitle")
                        val authorsArray = volumeObj.getJSONArray("authors")
                        val publisher = volumeObj.optString("publisher")
                        val publishedDate = volumeObj.optString("publishedDate")
                        val description = volumeObj.optString("description")
                        val pageCount = volumeObj.optInt("pageCount")
                        val imageLinks = volumeObj.optJSONObject("imageLinks")
                        val thumbnail = imageLinks.optString("thumbnail")
                        val previewLink = volumeObj.optString("previewLink")
                        val infoLink = volumeObj.optString("infoLink")
                        val authorsArrayList = ArrayList<String>()

                        for (j in 0 until authorsArray.length()) {
                            authorsArrayList.add(authorsArray.optString(j))
                        }

                        val bookInfo = BookInfo(
                            title, subtitle, authorsArrayList, publisher,
                            publishedDate, description, pageCount, thumbnail,
                            previewLink, infoLink
                        )

                        bookInfoArrayList?.add(bookInfo)
                    }

                    // BookAdapter'ı güncelle ve RecyclerView'a set et
                    adapter = bookInfoArrayList?.let { BookAdapter(it, requireContext()) }!!
                    recyclerView.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Aranılan Kitap Bulunamadı $", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                val errorMessage = error.message
                Toast.makeText(
                    requireContext(), errorMessage, Toast.LENGTH_LONG
                ).show()

            })

        mRequestQueue!!.add(booksObjrequest)
    }
}






