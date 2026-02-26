package com.example.laba2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvArtist: TextView
    private lateinit var tvYear: TextView
    private lateinit var btnPrevious: Button
    private lateinit var btnNext: Button

    private var currentIndex = 0
    private lateinit var artworks: List<Artwork>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка для edge-to-edge дисплея
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initData()

        // Восстановление состояния после поворота
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("CURRENT_INDEX", 0)
        }

        updateArtwork()
    }

    private fun initViews() {
        imageView = findViewById(R.id.imageView)
        tvTitle = findViewById(R.id.tvTitle)
        tvArtist = findViewById(R.id.tvArtist)
        tvYear = findViewById(R.id.tvYear)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)

        btnPrevious.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateArtwork()
            }
        }

        btnNext.setOnClickListener {
            if (currentIndex < artworks.size - 1) {
                currentIndex++
                updateArtwork()
            }
        }
    }

    private fun initData() {
        artworks = listOf(
            Artwork(
                getString(R.string.art_1_title),
                getString(R.string.art_1_artist),
                getString(R.string.art_1_year),
                R.drawable.art1
            ),
            Artwork(
                getString(R.string.art_2_title),
                getString(R.string.art_2_artist),
                getString(R.string.art_2_year),
                R.drawable.art2
            ),
            Artwork(
                getString(R.string.art_3_title),
                getString(R.string.art_3_artist),
                getString(R.string.art_3_year),
                R.drawable.art3
            ),
            Artwork(
                getString(R.string.art_4_title),
                getString(R.string.art_4_artist),
                getString(R.string.art_4_year),
                R.drawable.art4
            ),
            Artwork(
                getString(R.string.art_5_title),
                getString(R.string.art_5_artist),
                getString(R.string.art_5_year),
                R.drawable.art5
            )
        )
    }

    private fun updateArtwork() {
        val artwork = artworks[currentIndex]
        imageView.setImageResource(artwork.imageResId)
        tvTitle.text = artwork.title
        tvArtist.text = artwork.artist
        tvYear.text = artwork.year

        // Обновление состояния кнопок
        btnPrevious.isEnabled = currentIndex > 0
        btnNext.isEnabled = currentIndex < artworks.size - 1

        // Обновление contentDescription для доступности
        imageView.contentDescription = getString(
            R.string.artwork_description,
            artwork.title,
            artwork.artist,
            artwork.year
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Сохраняем текущий индекс при повороте экрана
        outState.putInt("CURRENT_INDEX", currentIndex)
    }
}