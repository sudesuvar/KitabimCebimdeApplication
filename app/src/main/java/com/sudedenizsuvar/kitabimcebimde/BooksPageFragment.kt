package com.sudedenizsuvar.kitabimcebimde

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.sudedenizsuvar.kitabimcebimde.databinding.FragmentBookspageBinding
import com.sudedenizsuvar.kitabimcebimde.databinding.BooksCardBinding

class BooksPageFragment : Fragment() {
    // Binding tanımlama kısmı
    private var _binding: FragmentBookspageBinding? = null
    private val binding get() = _binding!!
    // ArrayList ve Adapter tanımlama
    var constList = ArrayList<booksClass>()
    var adapter: KitaplarAdapter? = null
    private lateinit var firebaseFirestore: FirebaseFirestore
    var database: FirebaseDatabase?= null
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): LinearLayout {
        _binding = FragmentBookspageBinding.inflate(inflater, container, false)
        val view = binding.root

        //Instance
        database= FirebaseDatabase.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()

        val adapter = KitaplarAdapter(requireContext(), constList)
        binding.listviewBooks.adapter = adapter

        // Adapter'ı listView'a bağlamak için
        //binding.listviewBooks.adapter = adapter
        //adapter.notifyDataSetChanged()



        // constList'e veri ekleme işlemi buraya taşındı
        constList.add(booksClass("Çalıkuşu", "\"Çalıkuşu\"," +
                " Reşat Nuri Güntekin'in en ünlü eserlerinden biridir. Roman, 1922 yılında yayımlandı ve Türk edebiyatının klasikleri arasında önemli bir yere sahiptir. Hikaye, genç bir öğretmen olan Feride'nin yaşam öyküsünü anlatır. Feride, Anadolu'nun farklı şehirlerinde öğretmenlik yaparken, hayatın zorluklarıyla mücadele ederken bir yandan da aşkı, arkadaşlığı ve toplumsal meseleleri deneyimler.", R.drawable.calikusu)
        )
        constList.add(
            booksClass("Amok Koşucusu",
                "\"Amok Koşucusu,1922 yılında Alman yazar Stefan Zweig tarafından yazılmış bir novelladır. Bu öykü, bir adamın deliliğe ve çılgınlığa sürüklenişini anlatır. Hikaye, Endonezya'da yaşayan ve tıp doktoru olan bir adamın, toplumun baskıcı ve sıkıcı kurallarıyla çevrili olduğu zamanlarda yaşadığı içsel çatışmaları konu alır. Ana karakter, çevresindeki sosyal sınırlamalardan bunalmıştır ve kendini özgürlük arayışında bulur. Bir süre sonra, toplumdaki baskılardan kaçmak ve içsel özgürlüğü bulmak amacıyla deliliğe doğru sürüklenir."
                , R.drawable.amok_kosucusu)
        )
        constList.add(booksClass("Serenad", "Her şey, 2001 yılının Şubat ayında soğuk bir gün, İstanbul Üniversitesi'nde halkla ilişkiler görevini yürüten Maya Duran'ın (36) ABD'den gelen Alman asıllı Profesör Maximilian Wagner'i (87) karşılamasıyla başlar. 1930'lu yıllarda İstanbul Üniversitesi'nde hocalık yapmış olan profesörün isteği üzerine, Maya bir gün onu Şile'ye götürür. Böylece, katları yavaş yavaş açılan dokunaklı bir aşk hikâyesine karışmakla kalmaz," +
                " dünya tarihine ve kendi ailesine ilişkin birtakım sırları da öğrenir. Serenad, 60 yıldır süren bir aşkı ele alırken, ister herkesin bildiği Yahudi Soykırımı olsun isterse çok az kimsenin bildiği Mavi Alay, bütün siyasi sorunlarda asıl harcananın, gürültüye gidenin hep insan olduğu gerçeğini de göz önüne seriyor." +
                " Okurunu sımsıkı kavrayan Serenad'da Zülfü Livaneli'nin romancılığının en temel niteliklerinden biri yine başrolde: İç içe geçmiş, kaynaşmış kişisel ve toplumsal tarihlerin kusursuz Dengesi.", R.drawable.serenad)
        )
        constList.add(booksClass("İstanbul Hatırası", "Byzantion'dan İstanbul'a uzanan, heyecan yüklü bir serüven... Sarayburnu'nda, Atatürk heykelinin ayaklarının dibinde bir ceset, Avuçlarında antik bir pere.... " +
                "Ama ne bu ceset son kurban, ne de bu antik para son sikke... " +
                "Yedi kurban, yedi hükümdar, yedi sikke, yedi kadim mekân. Ve tek bir gerçek: Bu şehrin gizemli tarihi. " , R.drawable.istanbulhatirasi)
        )
        constList.add(booksClass("Nutuk" , "\"Nutuk\", Türkiye Cumhuriyeti'nin kurucusu ve ilk Cumhurbaşkanı Mustafa Kemal Atatürk'ün, Türkiye Büyük Millet Meclisi'nin 15-20 Ekim 1927 tarihleri arasında yaptığı bir konuşmadır." +
                " Bu konuşma, genellikle Atatürk'ün Cumhuriyet'in ilk yıllarındaki politikalarını, devrimlerini ve Türkiye'nin geleceği için izlediği yolu anlattığı uzun bir belgedir." +
                " \"Nutuk\", Atatürk'ün Türkiye Cumhuriyeti'ni kurarken karşılaştığı zorlukları, Milli Mücadele sürecini, Osmanlı İmparatorluğu'nun çöküşünü, ulusal bağımsızlık mücadelesini, halkın direnişini ve savaşın kazanılmasını anlatır. Ayrıca, cumhuriyetin ilan edilmesi ve Türkiye'nin modernleşme adımları gibi önemli olayları da içerir." , R.drawable.nutuk)
        )
        constList.add(booksClass("Kayıp Tanrılar Ülkesi" , "Babasız çocuklar tanrıya sığınırdı ama o tanrı olmayı seçti. Berlin Emniyet Müdürlüğü’nün cevval başkomiseri Yıldız Karasu ve yardımcısı Tobias Becker, göçmenlerin, işgal evlerinin ve sokak sanatçılarının renklendirdiği Berlin sokaklarından Bergama’ya uzanan bir macerada, hayatı ve insanları yok etmeye muktedir sırların peşinde bir seri cinayetler dizisini çözmeye çalışıyor." +
                " Soruşturmanın Türkiye ayağında sürpriz bir ismin olaya dahil olmasıyla heyecanın dozu gitgide artıyor. Kayıp Tanrılar Ülkesi, Zeus Altarı ve Pergamon Tapınağı’nın gölgesinde mitlere günümüzde yeniden hayat verirken, suçun çağlar ve kültürler boyu değişmeyen doğasını bir tokat gibi yüzümüze çarpıyor." +
                " “O yüzden unuttuk dediğiniz yerden başlayacağım. Unutmanın bedelini ödeyecek unutanlar. Cezaların en şiddetlisiyle ödüllendirilecek saygısızlık yapanlar, kalbi yerinden çıkarılacak beni kalbinden çıkaranların, yüzlerinin derisi yüzülecek benden yüz çevirenlerin…”" , R.drawable.kayiptanrilarulkesi)
        )
        constList.add(booksClass("Kürk Mantolu Madonna" , "Romanda, 20'li yaşlarında babasının isteği üzerine gittiği Berlin'de, sanata olan ilgisi sayesinde bir sanat galerisini ziyaret eden Raif Efendi, galerideki tablolar arasında bir sanatçının otoportresini görür ve tablodaki kadını hiç tanımamasına rağmen platonik olarak aşık olur." +
                " Bu tablo onda daha önce hiç hissetmediği duygular uyandırır. Raif Efendi tablodaki portrenin, Andrea Del Sarto tarafından yapılmış \"Madonna delle Arpie\" isimli tablodaki Madonna'nın portresine benzediğini düşünür. Tabloya o kadar hayran olur ki fırsat buldukça tabloyu görmeye gider, fakat başka gözlerin onu takip ettiğini fark etmez. Artık ritüel halini alan bu tabloyu seyretme seansınlarından birinde bir kadın onun yanına gelir. Bu kadın, tablonun sahibi olan sanatçı Maria Puder'dir." +
                " Bir tablodan başlayan aşkı anlatan roman için Sabahattin Ali'nin Andrea Del Sarto imzalı \"Madonna delle Arpie\" tablosundan ilham aldığı biliniyor." , R.drawable.kurkmantolumadonna)
        )

        constList.add(booksClass("İçimizdeki Şeytan" , "'İsteyip istemediğimi doğru dürüst bilmediğim, fakat neticesi aleyhime çıkarsa istemediğimi iddia ettiğim bu nevi söz ve fiillerimin: daimi bir mesulünü bulmuştum: Buna içimdeki şeytan diyordum, müdafaasını üzerime almaktan korktuğum bütün hareketlerimi ona yüklüyor ve kendi suratıma tüküreceğim yerde, haksızlığa, tesadüfün cilvesine uğramış bir mazlum gibi nefsimi şefkat ve ihtimama layık görüyordum." +
                " Halbuki ne şeytanı azizim, ne şeytanı? Bu bizim gururumuzun, salaklığımızın uydurması... İçimizdeki şeytan pek de kurnazca olmayan bir kaçamak yolu... İçimizde şeytan yok... İçimizde aciz var... Tembellik var... İradesizlik, bilgisizlik ve bunların hepsinden daha korkunç bir şey: hakikatleri görmekten kaçmak itiyadı var...' Bu romanında, toplumsal gündemin kişilikler üzerindeki baskısını ve güçsüz insanın 'kapana kısılmışlığını' gösteriyor Sabahattin Ali." +
                " Aydın geçinenlerin karanlığına, 'insanın içindeki şeytan'a keskin bir bakış." , R.drawable.icimizdekiseytan)
        )
        constList.add(
            booksClass("Aşk-ı Memnu" , "Halid Ziya’ya kadar, romancı muhayyilesiyle doğmuş tek muharririmiz yoktur. Hepsi roman veya hikâye yazmaya hevesli insanlardır.Ahmet Hamdi TanpınarBu aşk, Bihter’in aşkı, Bihter’le Behlûl’ün yasak aşkı, yazarın asıl yazmak istediği ve en çok başarılı olduğu bölüm... " +
                    "Çünkü romanın asıl hayatı Nihal’in vakası olduğu ve onda çok başarılı olduğu halde yazar bunda yüz kat fazla başarılı olmuş, ona oranla “benzersiz” denilecek kadar bu aşkı kusursuz ve nefis bir biçimde yazmıştır. Bu aşkı başlangıcından sonuna kadar bütün anları ve dönemleriyle kılı kırk yararcasına çözümleme ve açıklamada büyük, pek büyük bir kudret ve sanat var." +
                    " Bu kadar yakından ve bu kadar derinden tanıdığımız bu genç kadının ruh ve aşkı bizi büyülüyor ve sarhoş ediyor; Nihal’le ruhumuzun en ince ve gözyaşı dolu etkilenmelerimiz heyecana getirildikten sonra Bihter’in, “bu İstanbul’un en nefis kadını”nın hayat ve ruhuna girerek o kadar belirsiz, özleyen, ıtırlı bir tutku rüyasına katılıyoruz. Ve bunda o kadar derin ve karanlık bir duygulanma var ki biz de temiz kalmakla düşkünlük arasında Behlûl gibi kararsız kalıyoruz ve hangisini tercih edeceğimize uzun müddet tereddüt ediyoruz.Mehmed Raufİlk baskıları üzerinde yapılmış karşılaştırmalı çalışma ve açıklamalı notlarıyla hâlâ yepyeni bir roman.Aşk-ı Memnu, her zaman okunacak." , R.drawable.askmemnu)
        )
        constList.add(booksClass("Suç ve Ceza" , "Suç ve Ceza, yazarın \"olgunluk\" döneminin ilk büyük romanı olarak kabul edilir. Roman, parası için bir tefeci kadını öldürmeyi tasarlayan, Saint Petersburg'da yaşayan fakir bir öğrenci olan Rodion Romanoviç Raskolnikov'un manevi ıstırabı, pişmanlığı ve ahlaki ikilemlerine odaklanır.", R.drawable.sucveceza)
        )

        // Firebase'den verileri alıp constlist içine ekleme (Adminin eklediği kitaplar)
        firebaseFirestore.collection("kitaplar").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val kitapAdi = document.getString("KitapAdi")
                    val kitapAciklama = document.getString("KitapAçiklama")

                    if (kitapAdi != null && kitapAciklama != null) {
                        constList.add(booksClass(kitapAdi, kitapAciklama, R.drawable.books))

                        // adapter.notifyDataSetChanged() ile adapter'a değişiklik olduğunu bildirilir.
                        adapter.notifyDataSetChanged()

                    }

                }
            }
            .addOnFailureListener { exception ->
                // Veri alınırken bir hata oluşursa buradan dönüt verilir.
            }


        return view

    }
}
class KitaplarAdapter: BaseAdapter {
    var constList = ArrayList<booksClass>()
    var context:Context?=null
    constructor(context:Context,  constList:ArrayList<booksClass>):super(){
        this.context=context
        this.constList=constList
    }
    override fun getCount(): Int {
        return constList.size
    }

    override fun getItem(position: Int): Any {
        return constList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val kitaplarBinding: BooksCardBinding
        val view: View
        if (convertView == null) {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            kitaplarBinding = BooksCardBinding.inflate(inflater, parent, false)
            view = kitaplarBinding.root
            view.tag = kitaplarBinding
        } else {
            view = convertView
            kitaplarBinding = view.tag as BooksCardBinding
        }

        val posKitaplar = constList[position]

        kitaplarBinding.KitapAditextView.text = posKitaplar.constBookname
        kitaplarBinding.KitapAciklamatextView.text = posKitaplar.constComment
        kitaplarBinding.KitapImage.setImageResource(posKitaplar.constPhoto!!)

        //Kitapların Ayrıntılı Sayfasına gitmek için
        kitaplarBinding.KitapImage.setOnClickListener(){
            var intent = Intent(context,BooksDetailPageActivity::class.java)
            intent.putExtra("constAdi", posKitaplar.constBookname)
            intent.putExtra("constAciklama", posKitaplar.constComment)
            intent.putExtra("constPhoto", posKitaplar.constPhoto!!)
            context!!.startActivity(intent)
        }

        return view
    }
}

