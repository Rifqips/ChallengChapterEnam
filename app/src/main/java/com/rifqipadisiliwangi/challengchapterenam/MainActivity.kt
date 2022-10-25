package com.rifqipadisiliwangi.challengchapterenam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*
        1. Menggunakan WorkManager untuk melakukan proses blur pada foto (2 poin).
        2. Menerapkan design pattern MVVM dalam aplikasi android (2 poin).
        3. Melakukan penyimpanan data lokal dengan DataStore (2 poin).
        4. Dapat menambahkan data berupa image foto user dalam halaman user profile (2 poin).
        5. Menerapkan Dependency Injection dalam project (2 poin)
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}