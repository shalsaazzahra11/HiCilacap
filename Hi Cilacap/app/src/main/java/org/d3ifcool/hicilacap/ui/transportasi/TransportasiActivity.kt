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
import org.d3ifcool.hicilacap.databinding.ActivityTransportasiBinding
import org.d3ifcool.hicilacap.model.Transportasi
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.KEY_DATA

class TransportasiActivity : AppCompatActivity() {

    private lateinit var adapterTransportasi: AdapterUtil<Transportasi>
    private lateinit var binding: ActivityTransportasiBinding
    private lateinit var viewModel: TransportasiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransportasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TransportasiViewModel::class.java)

        viewModel.getDataTransportasi().observe(this, Observer {
            adapterTransportasi = AdapterUtil(R.layout.item_main, it, { itemView, item ->
                itemView.nama.text = item.nama_transportasi
                val requestOptions = RequestOptions()
                Glide.with(this@TransportasiActivity)
                    .load(item.img_transportasi)
                    .apply(requestOptions.frame(1))
                    .into(itemView.img)
            }, { position, item->
                val intent = Intent(this@TransportasiActivity, JenisActivity::class.java)
                    .putExtra(KEY_DATA, item)
                startActivity(intent)
            })

            binding.rvTransportasi.apply {
                this.layoutManager = LinearLayoutManager(this@TransportasiActivity)
                this.adapter=adapterTransportasi
            }
        })


        viewModel.getStatus().observe(this@TransportasiActivity, Observer {
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