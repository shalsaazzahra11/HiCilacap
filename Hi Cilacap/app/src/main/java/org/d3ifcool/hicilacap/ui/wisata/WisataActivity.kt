package org.d3ifcool.hicilacap.ui.wisata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_wisata.*
import kotlinx.android.synthetic.main.item_main.view.*
import org.d3ifcool.hicilacap.AdapterUtil
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.databinding.ActivityWisataBinding
import org.d3ifcool.hicilacap.model.Wisata
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.KEY_DATA

class WisataActivity : AppCompatActivity() {
    private lateinit var adapterWisata: AdapterUtil<Wisata>
    private lateinit var binding: ActivityWisataBinding
    private lateinit var viewModel: WisataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(WisataViewModel::class.java)

        viewModel.getDataWisata().observe(this, Observer {
            adapterWisata = AdapterUtil(R.layout.item_main, it, { itemView, item ->
                itemView.nama.text = item.nama_wisata
                val requestOptions = RequestOptions()
                Glide.with(this@WisataActivity)
                    .load(item.img_wisata)
                    .apply(requestOptions.frame(1))
                    .into(itemView.img)
            }, { _, item ->
                val intent = Intent(this@WisataActivity, WisataDetailActivity::class.java)
                    .putExtra(KEY_DATA,item)
                startActivity(intent)
            })

            binding.rvWisata.apply {
                this.layoutManager = LinearLayoutManager(this@WisataActivity)
                this.adapter=adapterWisata
            }
        })


        viewModel.getStatus().observe(this@WisataActivity, Observer {
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