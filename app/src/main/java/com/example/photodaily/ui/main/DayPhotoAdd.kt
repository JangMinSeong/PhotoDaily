package com.example.photodaily.ui.main

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.photodaily.R
import com.example.photodaily.adpater.DayAdapter
import com.example.photodaily.data.Photo
import com.example.photodaily.data.PhotoDatabase
import kotlinx.android.synthetic.main.activity_day_photo_add.*
import java.net.URI
import java.net.URL

class DayPhotoAdd : AppCompatActivity() {
    var photoURL: Uri? = null
    private lateinit var db : PhotoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_photo_add)
        getContent.launch("image/*")

        button_day_photo_add.setOnClickListener {
            if(photoURL == null)
                getContent.launch("image/*")
            else{
                db = PhotoDatabase.getinstance(this)!!
                db.photoDao().insert(Photo(photoURL.toString(),editTextdate_day_photo_add.text.toString(), editText_day_photo_add.text.toString()))
                Toast.makeText(this,photoURL.toString(),Toast.LENGTH_SHORT).show()
               val fragment =  supportFragmentManager.findFragmentById(R.id.recyclerView) as DayDiary
                fragment.updateRecyclerView()
                finish()
            }

        }
    }
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent())  {
            uri ->
        if(uri != null){
            Toast.makeText(this,uri.toString(), Toast.LENGTH_SHORT).show()
            Glide.with(this).load(uri).into(imageView_day_photo_add)
            photoURL = uri
        }else
        {
            Toast.makeText(this,"사진 못불러옴", Toast.LENGTH_SHORT).show()

        }
    }
}