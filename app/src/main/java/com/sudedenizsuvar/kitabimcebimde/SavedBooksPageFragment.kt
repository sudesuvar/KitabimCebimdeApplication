package com.sudedenizsuvar.kitabimcebimde

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.sudedenizsuvar.kitabimcebimde.databinding.ActivityBooksdetailpageBinding
import com.sudedenizsuvar.kitabimcebimde.databinding.FragmentBookspageBinding
import com.sudedenizsuvar.kitabimcebimde.databinding.BooksCardBinding

class SavedBooksPageFragment : Fragment() {
    private var _binding: FragmentBookspageBinding? = null
    private val binding get() = _binding!!

    var constList = ArrayList<booksClass>()
    var adapter: KitaplarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookspageBinding.inflate(inflater, container, false)
        val view = binding.root
        val adapter = KitaplarAdapter(requireContext(), constList)
        binding.listviewBooks.adapter = adapter
        //Shared olarak verileri getir
        // Verileri çekmek için

        val sharedPreferences = MySharedPreferences(requireContext())
        var list1 = sharedPreferences.getDataList("kitapAd")
        var list2 = sharedPreferences.getDataList("kitapAciklama")
        var list3 = sharedPreferences.getDataList("kitapResim")
        for ((index, item) in list1.withIndex()) {
            constList.add(booksClass(item, list2[index], list3[index].toInt()))
            adapter?.notifyDataSetChanged()
        }
        if(list1.count()==0)
            Toast.makeText(requireContext(), "Kaydedilen Bir kitap Bulunmamaktadır!", Toast.LENGTH_SHORT).show()



        return view
    }
    class KitaplarAdapter : BaseAdapter {
        lateinit var binding_: ActivityBooksdetailpageBinding
        var constList = ArrayList<booksClass>()
        var context: Context? = null

        constructor(context: Context, constList: ArrayList<booksClass>) : super() {
            this.constList = constList
            this.context = context//gerekli değişken ver kurucu metodları oluşturduk
        }

        override fun getCount(): Int {
            return constList.size
        }

        override fun getItem(position: Int): Any {
            return constList[position] //getItem constlistin positionunu veriyor
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val booksBinding: BooksCardBinding
            val view: View
            if (convertView == null) {
                val inflater =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                booksBinding = BooksCardBinding.inflate(inflater, parent, false)
                view = booksBinding.root
                view.tag = booksBinding
            } else {
                view = convertView
                booksBinding = view.tag as BooksCardBinding
            }
            val poskitaplar = constList[position]
            booksBinding.KitapAditextView.text = poskitaplar.constBookname
            booksBinding.KitapAciklamatextView.text = poskitaplar.constComment
            booksBinding.KitapImage.setImageResource(poskitaplar.constPhoto!!)
            booksBinding.KitapImage.setOnClickListener() {
                var intent = Intent(context, BooksDetailPageActivity::class.java)
                intent.putExtra("constAdi", poskitaplar.constBookname)
                intent.putExtra("constAciklama", poskitaplar.constComment)
                intent.putExtra("constResim", poskitaplar.constPhoto!!)
                context!!.startActivity(intent)
            }


            
            return view
        }
    }
}

