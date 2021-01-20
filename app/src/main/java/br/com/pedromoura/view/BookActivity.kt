package br.com.pedromoura.view

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import br.com.pedromoura.R

class BookActivity : AppCompatActivity() {

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //endregion
}