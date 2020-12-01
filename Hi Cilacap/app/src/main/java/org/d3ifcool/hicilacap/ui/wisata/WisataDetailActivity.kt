package org.d3ifcool.hicilacap.ui.wisata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_wisata.*
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.model.Wisata
import org.d3ifcool.hicilacap.network.KEY_DATA

class WisataDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)

        val itemWisata = intent.getSerializableExtra(KEY_DATA)!! as Wisata
        initView(itemWisata)
    }

    private fun initView(item: Wisata) {
        nama.text = item!!.nama_wisata
        detail.text = item.detail_wisata
        val requestOptions = RequestOptions()
        Glide.with(applicationContext).load(item.img_wisata)
            .apply(requestOptions.frame(1))
            .into(imageView)

        btn_lokasi.setOnClickListener {
            val mapIntentUri = Uri.parse(item.lokasi_wisata)
            val mapIntent = Intent(Intent.ACTION_VIEW,mapIntentUri)

            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}