package br.com.pedromoura.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pedromoura.R

class BookActivity : AppCompatActivity() {

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //endregion
}