package org.d3ifcool.hicilacap.ui.transportasi

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail_transportasi.*
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.model.Jenis
import org.d3ifcool.hicilacap.network.KEY_DATA

class TransportasiDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transportasi)

        val itemJenis = intent.getSerializableExtra(KEY_DATA)!! as Jenis
        initView(itemJenis)
    }

    fun initView(item: Jenis) {
        nama.text = item.nama_transportasi
        tujuan.text = item.keberangkatan_transportasi
        berangkat.text = item.jam_berangkat
        datang.text = item.jam_datang
        harga.text = item.harga
        kontak.text = item.kontak
        val requestOptions = RequestOptions()
        Glide.with(applicationContext).load(item.img_transportasi)
            .apply(requestOptions.frame(1))
            .into(imageView)

        Phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${item.kontak}")
            startActivity(intent)
        }
    }
}