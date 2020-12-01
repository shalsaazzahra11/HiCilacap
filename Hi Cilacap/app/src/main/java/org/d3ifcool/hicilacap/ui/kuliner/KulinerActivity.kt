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
import org.d3ifcool.hicilacap.databinding.ActivityKulinerBinding
import org.d3ifcool.hicilacap.model.Kuliner
import org.d3ifcool.hicilacap.network.ApiStatus
import org.d3ifcool.hicilacap.network.KEY_DATA

class KulinerActivity : AppCompatActivity() {

    private lateinit var adapterKuliner: AdapterUtil<Kuliner>
    private lateinit var binding: ActivityKulinerBinding
    private lateinit var viewModel: KulinerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKulinerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(KulinerViewModel::class.java)

        viewModel.getDataKuliner().observe(this, Observer {
            adapterKuliner = AdapterUtil(
                R.layout.item_main,
                it,
                { itemView, item ->
                    itemView.nama.text = item.nama_kuliner
                    val requestOptions = RequestOptions()
                    Glide.with(this@KulinerActivity)
                        .load(item.img_kuliner)
                        .apply(requestOptions.frame(1))
                        .into(itemView.img)
                },
                {  _, item ->
                    val intent = Intent(this@KulinerActivity, KategoriActivity::class.java)
                        .putExtra(KEY_DATA,item)
                    startActivity(intent)
                })

            binding.rvKuliner.apply {
                this.layoutManager = LinearLayoutManager(this@KulinerActivity)
                this.adapter=adapterKuliner
            }
        })


        viewModel.getStatus().observe(this@KulinerActivity, Observer {
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