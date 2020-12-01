package org.d3ifcool.hicilacap.ui.kuliner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_transportasi.*
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import kotlinx.android.synthetic.main.detail_kuliner.*
import kotlinx.android.synthetic.main.detail_kuliner.Phone
import kotlinx.android.synthetic.main.detail_kuliner.imageView
import kotlinx.android.synthetic.main.detail_kuliner.kontak
import kotlinx.android.synthetic.main.detail_kuliner.nama
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.model.Kategori
import org.d3ifcool.hicilacap.network.KEY_DATA


class KulinerDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_kuliner)

        val itemKategori = intent.getSerializableExtra(KEY_DATA)!! as Kategori
        initView(itemKategori)
    }
    fun initView(item: Kategori){
        nama.text = item!!.nama_kuliner
        keterangan.text = item.detail_kuliner
        alamat.text = item.alamat_kuliner
        kontak.text = item.kontak
        val requestOptions = RequestOptions()
        Glide.with(applicationContext).load(item.img_kuliner)
            .apply(requestOptions.frame(1))
            .into(imageView)

        Phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${item.kontak}")
            startActivity(intent)
        }

        Lokasi.setOnClickListener {
            val mapIntentUri = Uri.parse(item.lokasi_kuliner)
            val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}
