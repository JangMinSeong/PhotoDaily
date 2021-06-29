package com.example.photodaily.ui.main

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import com.example.photodaily.R
import kotlinx.android.synthetic.main.item_travel_map.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TravelDiary.newInstance] factory method to
 * create an instance of this fragment.
 */
class TravelDiary : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_travel_diary, container, false)

        makeMap(rootView)

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TravelDiary.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TravelDiary().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getURLForResource(resId: Int): String? {
        return Uri.parse("android.resource://" + R::class.java.getPackage().getName() + "/" + resId)
            .toString()
    }

    private fun makeMap(rootView: View) {


        val resources: Resources = this.resources

        //Daegu
        rootView.travel_daegu_btn.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event:MotionEvent):Boolean {

                if(event.action == MotionEvent.ACTION_DOWN) {

                    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.travel_daegu_icon)

                    var iX = event.getX()
                    var iY = event.getY()

                    if(bitmap.getPixel(iX.toInt(), iY.toInt()) != 0)   //클릭 좌표 != 0 --> 투명한 부분이 아닐 경우
                        activity?.let{

                            rootView.travel_daegu_btn.setColorFilter(Color.parseColor("#55ff0000"))

                            val intent = Intent(context, TravelSelectRegion::class.java)
                            startActivity(intent)
                        }
                }
                return true
            }
        })

        //Gyeongsan
        rootView.travel_gyeongsan_btn.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event:MotionEvent):Boolean {

                if(event.action == MotionEvent.ACTION_DOWN) {

                    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.travel_gyeongsan_icon)

                    var iX = event.getX()
                    var iY = event.getY()

                    if(bitmap.getPixel(iX.toInt(), iY.toInt()) != 0)   //클릭 좌표 != 0 --> 투명한 부분이 아닐 경우
                        activity?.let{

                            rootView.travel_gyeongsan_btn.setColorFilter(Color.parseColor("#55ff0000"))

                            val intent = Intent(context, TravelSelectRegion::class.java)
                            startActivity(intent)
                        }
                }
                return true
            }
        })
    }
}