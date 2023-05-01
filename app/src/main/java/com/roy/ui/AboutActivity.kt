package com.roy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.roy.BuildConfig
import com.roy.R
import com.roy.databinding.ActivityAboutBinding
import com.roy.db.MyPreferences
import com.roy.ext.moreApp
import com.roy.ext.openBrowserPolicy
import com.roy.ext.rateApp
import com.roy.ext.shareApp
import com.roy.model.Themes

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        // Themes
        val themes = Themes(this)
        themes.applyDayNightOverride()
        setTheme(themes.getTheme())

        // Change the status bar color
        if (MyPreferences(this).theme == 1) { // Amoled theme
            window.statusBarColor = ContextCompat.getColor(this, R.color.amoled_background_color)
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
        }

        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set app version
        val versionName =
            this.getString(R.string.app_version_title) + " " + BuildConfig.VERSION_NAME
        binding.tvAboutAppVersion.text = versionName

        // back button
        binding.ivAboutBack.setOnClickListener {
            finish()
        }

        // Rate
        binding.tvAboutRate.setOnClickListener {
            rateApp(packageName)
        }
        binding.tvAboutMoreApp.setOnClickListener {
            moreApp()
        }
        binding.tvAboutShareApp.setOnClickListener {
            shareApp()
        }

        binding.tvAboutPrivacyPolicy.setOnClickListener {
            openBrowserPolicy()
        }

        var clickAppVersionCount = 0
        binding.tvAboutAppVersion.setOnClickListener {
            clickAppVersionCount++
            if (clickAppVersionCount > 3) {
                Toast.makeText(
                    this,
                    this.getString(R.string.thanks_for_using_opencalc),
                    Toast.LENGTH_SHORT
                ).show()
                clickAppVersionCount = 0
            }
        }
    }
}
