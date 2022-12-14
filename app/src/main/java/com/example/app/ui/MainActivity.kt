package com.example.app.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.app.R
import com.example.app.viewtypeparser.JsonLayoutInflaterProvider
import com.example.app.viewtypeparser.LayoutInflater
import com.example.app.databinding.ActivityMainBinding
import com.example.app.ui.dialogs.DialogFragmentCallback
import com.example.app.ui.dialogs.LayoutChooser
import com.example.app.viewtypeparser.value.Layout
import com.example.app.widget.MyTextView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), DialogFragmentCallback {
    private lateinit var binding: ActivityMainBinding

    lateinit var layoutInflater: LayoutInflater
    val gson = GsonBuilder().create()
    val type = object : TypeToken<Map<String, Any>>() {}.type

    var currentLayout: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(this.getLayoutInflater(), null, false)
        setContentView(binding.root)
        layoutInflater = (applicationContext as JsonLayoutInflaterProvider).jsonLayoutInflater

        val myTextView = MyTextView(this)
        Toast.makeText(this, myTextView.text.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.reload -> {
//                if(currentLayout != null) {
//                    b.removeAllViews()
//                    val attributeMap = gson.fromJson<Map<String, Any>>(JsonReader(InputStreamReader(assets.open(currentLayout))), type)
//                    val layout = Layout(attributeMap.toList().map { Pair(it.first, it.second) })
//                    val view = layoutInflater.inflate(this, layout, b)
//                    b.addView(view)
//                }
                return true
            }
            R.id.load -> {
                LayoutChooser.newInstance(0).show(supportFragmentManager, "layout_chooser")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        if (resultCode == DialogFragmentCallback.RESULT_OK && data != null) {
            val container = binding.container
            container.removeAllViews()
            val path = data.getString("path") ?: return
            currentLayout = path
            val attributeMap = gson.fromJson<Map<String, Any>>(JsonReader(InputStreamReader(assets.open(path))), type)
            val layout = Layout(attributeMap.toList().map { Pair(it.first, it.second) })
            val view = layoutInflater.inflate(this, layout, container)
            container.addView(view)
        }
    }
}
