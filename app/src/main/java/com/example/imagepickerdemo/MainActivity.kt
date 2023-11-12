package com.example.imagepickerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider

class MainActivity : AppCompatActivity() {
    lateinit var imagePicker : ImageView
val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
    if(it.resultCode == RESULT_OK){
        val uri = it.data?.data
        uri?.let {u ->
            imagePicker.setImageURI(u)
        }

    }}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagePicker = findViewById(R.id.picker_image)
        val gallery = findViewById<Button>(R.id.gallery)
        val camera = findViewById<Button>(R.id.camera)
        gallery?.setOnClickListener(){
            ImagePicker.with(this)
                .provider(ImageProvider.BOTH)
                .createIntentFromDialog { i ->
                    launcher.launch(i)
                }
        }
        camera.setOnClickListener(){

        }
    }
}