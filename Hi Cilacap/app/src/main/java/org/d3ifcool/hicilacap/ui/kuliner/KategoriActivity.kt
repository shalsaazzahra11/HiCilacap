package org.d3ifcool.hicilacap.ui.kuliner

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_kuliner.*
import kotlinx.android.synthetic.main.item_main.view.*
import org.d3ifcool.hicilacap.AdapterUtil
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.databinding.ActivityKategoriBinding
import org.d3ifcool.hicilacap.model.Kategori
import org.d3ifcool.hicilacap.model.Kuliner
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.KEY_DATA

class KategoriActivity : AppCompatActivity() {
    private lateinit var adapterKategori: AdapterUtil<Kategori>
    private lateinit var binding: ActivityKategoriBinding
    private lateinit var itemKuliner: Kuliner
    private lateinit var viewModel: KulinerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KulinerViewModel::class.java)
        
        itemKuliner = intent.getSerializableExtra(KEY_DATA)!! as Kuliner

        viewModel.getDataKuliner().observe(this, Observer {
            adapterKategori = AdapterUtil(
                R.layout.item_main,
                itemKuliner.kategori,
                { itemView, item ->
                    itemView.nama.text = item.nama_kuliner
                    val requestOptions = RequestOptions()
                    Glide.with(this@KategoriActivity)
                        .load(item.img_kuliner)
                        .apply(requestOptions.frame(1))
                        .into(itemView.img)

                },
                { position, item->
                    val intent = Intent(this@KategoriActivity,KulinerDetailActivity::class.java)
                        .putExtra(KEY_DATA,item)
                    startActivity(intent)
                })

            binding.rvKategori.apply {
                this.layoutManager = LinearLayoutManager(this@KategoriActivity)
                this.adapter = adapterKategori
            }
        })

        viewModel.getStatus().observe(this@KategoriActivity, Observer {
            updateProgress(it)
        })

    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                progressBar
                    .visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                progressBar.visibility = View.GONE
                networkError.visibility = View.VISIBLE
            }
        }
    }
}