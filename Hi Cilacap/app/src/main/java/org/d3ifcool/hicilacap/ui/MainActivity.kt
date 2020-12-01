package org.d3ifcool.hicilacap.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import org.d3ifcool.hicilacap.R
import org.d3ifcool.hicilacap.databinding.ActivityMainBinding
import org.d3ifcool.hicilacap.ui.kuliner.KulinerActivity
import org.d3ifcool.hicilacap.ui.transportasi.TransportasiActivity
import org.d3ifcool.hicilacap.ui.wisata.WisataActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wisata.setOnClickListener {
            intent = Intent(this@MainActivity, WisataActivity::class.java)
            startActivity(intent)
        }

        binding.kuliner.setOnClickListener {
            intent = Intent(this@MainActivity, KulinerActivity::class.java)
            startActivity(intent)
        }

        binding.transportasi.setOnClickListener {
            intent = Intent(this@MainActivity, TransportasiActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profil -> {
                intent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.about -> {
                intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
