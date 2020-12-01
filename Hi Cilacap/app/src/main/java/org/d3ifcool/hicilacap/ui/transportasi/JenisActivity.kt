package org.d3ifcool.hicilacap.ui.transportasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_transportasi.*
import kotlinx.android.synthetic.main.item_main.view.*
import org.d3ifcool.hicilacap.AdapterUtil
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.databinding.ActivityJenisBinding
import org.d3ifcool.hicilacap.model.Jenis
import org.d3ifcool.hicilacap.model.Transportasi
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.KEY_DATA

class JenisActivity : AppCompatActivity() {

    private lateinit var adapterJenis: AdapterUtil<Jenis>
    private lateinit var binding: ActivityJenisBinding
    private lateinit var itemTransportasi: Transportasi
    private lateinit var viewModel: TransportasiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJenisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TransportasiViewModel::class.java)

        itemTransportasi = intent.getSerializableExtra(KEY_DATA)!! as Transportasi

        viewModel.getDataTransportasi().observe(this, Observer {
            adapterJenis =
                AdapterUtil(R.layout.item_main, itemTransportasi.jenis, { itemView, item ->
                    itemView.nama.text = item.nama_transportasi
                    val requestOptions = RequestOptions()
                    Glide.with(this@JenisActivity)
                        .load(item.img_transportasi)
                        .apply(requestOptions.frame(1))
                        .into(itemView.img)
                }, { position, item ->
                    val intent = Intent(this@JenisActivity, TransportasiDetailActivity::class.java)
                        .putExtra(KEY_DATA, item)
                    startActivity(intent)
                })
            binding.rvJenis.apply {
                this.layoutManager = LinearLayoutManager(this@JenisActivity)
                this.adapter = adapterJenis
            }
        })

        viewModel.getStatus().observe(this@JenisActivity, Observer {
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