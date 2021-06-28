package com.example.photodaily.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photodaily.R
import com.example.photodaily.adpater.DayAdapter
import com.example.photodaily.data.Photo
import com.example.photodaily.data.PhotoDatabase
import kotlinx.android.synthetic.main.fragment_day_diary.*
import kotlinx.android.synthetic.main.item_day_one.*
import java.util.jar.Manifest

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DayDiary.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayDiary : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var db : PhotoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var recyclerView: RecyclerView
    fun updateRecyclerView(){
        recyclerView.adapter?.notifyDataSetChanged()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        db = PhotoDatabase.getinstance(requireContext())!!
        val dayList = db.photoDao().getAll()
        Log.d("jeongjin", "onCreateView:" + dayList.size)
        val rootView = inflater.inflate(R.layout.fragment_day_diary,container,false)
        // Inflate the layout for this fragment
        recyclerView = rootView.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DayAdapter(dayList)
        rootView.findViewById<Button>(R.id.button_day_daiary_add).setOnClickListener{
        val intent = Intent(this.requireContext(),DayPhotoAdd::class.java)
        startActivity(intent)
    }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DayDiary.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DayDiary().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}