package com.sudedenizsuvar.kitabimcebimde

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class BookAdapter    (
    //BookAdapter sınıfı, özelleştirilmiş bir RecyclerView.Adapter sınıfıdır.
    //Bir liste öğesini (ArrayList<BookInfo>) ve bir Context öğesini alarak,
    //bu verileri RecyclerView içinde göstermek için kullanılır.
    //bookInfoArrayList, BookInfo adında başka bir sınıfın nesnelerinin bulunduğu bir ArrayList'i temsil eder.
    // Bu liste, RecyclerView içinde gösterilecek olan kitap bilgilerini içerir.
    private val bookInfoArrayList: ArrayList<BookInfo>, private val mcontext: Context
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_search_google_card, parent, false)
        return BookViewHolder(view)
        //onCreateViewHolder fonksiyonu, her bir öğe için bir ViewHolder oluşturur.
        //Bu fonksiyon, parent ViewGrouptan bir Context alarak book_rv_item layoutunu bu gruba bağlar.
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {


        val bookInfo = bookInfoArrayList[position]
        //ViewHolder'a kitap bilgilerini yerleştirme işlemleri
        holder.nameTV.text = bookInfo.title
        holder.publisherTV.text = bookInfo.publisher.toString()
        holder.pageCountTV.text = "No of Pages : " + bookInfo.pageCount
        holder.dateTV.text = bookInfo.publishedDate

        // below line is use to set image from URL in our image view.
        // Picassodan fotoğraf çeker
        Picasso.get().load(bookInfo.thumbnail).into(holder.bookIV)

        holder.itemView.setOnClickListener {
        // tıklandığında aşağıdaki bileşenler gösterilir.
            // title, subtitle gibi bileşenleri bookInfo'nun içine aktarır
            val i = Intent(mcontext, SearchGoogleBookDetailsActivity::class.java)
            i.putExtra("title", bookInfo.title) //başlık
            i.putExtra("subtitle", bookInfo.subtitle) //altbaşlık
            i.putExtra("authors", bookInfo.authors) //yazar
            i.putExtra("publisher", bookInfo.publisher) //yayınlayan
            i.putExtra("publishedDate", bookInfo.publishedDate) //yayınlanma tarihi
            i.putExtra("description", bookInfo.description) // açıklama
            i.putExtra("pageCount", bookInfo.pageCount) // sayfa sayısı
            i.putExtra("thumbnail", bookInfo.thumbnail) // küçük resim
            i.putExtra("previewLink", bookInfo.previewLink)
            i.putExtra("infoLink", bookInfo.infoLink)

            mcontext.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        // bookInfoArrayList'in boyutunu döndürür.
        return bookInfoArrayList.size
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //inner class BookViewHolder, her bir öğenin görünümünü temsil eder.
        //itemView içindeki bileşenleri initialize eder.
        //Burada BookViewHolder, RecyclerView içindeki her bir kitap öğesinin görünümünü temsil eder.
        //RecyclerView.ViewHolder sınıfından miras alır ve bu nedenle itemView içindeki bileşenlere erişim sağlar.
        var nameTV: TextView
        var publisherTV: TextView
        var pageCountTV: TextView
        var dateTV: TextView
        var bookIV: ImageView

        init {
            nameTV = itemView.findViewById(R.id.idTVBookTitle)
            publisherTV = itemView.findViewById(R.id.idTVpublisher)
            pageCountTV = itemView.findViewById(R.id.idTVPageCount)
            dateTV = itemView.findViewById(R.id.idTVDate)
            bookIV = itemView.findViewById(R.id.idIVbook)
        }
    }
}
