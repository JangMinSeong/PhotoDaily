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
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.MotionEventCompat
import androidx.fragment.app.Fragment
import com.example.photodaily.R
import kotlinx.android.synthetic.main.fragment_travel_diary.view.*
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

    private var arr_region:  ArrayList<Pair<ImageView, Int>> = ArrayList()
    private val region_num: Int = 151


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
        var iX : Float
        var iY : Float

        makeMap(rootView) //모든 지역이미지 리스트에 추가


        //최상위 뷰 : travel_space
        //클릭 이벤트 발생 시 좌표 탐색 후 리스트에서 해당 좌표가 투명하지 않은 뷰 탐색
        rootView.travel_space.requestFocus()
        rootView.travel_space.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event:MotionEvent):Boolean {
                if(event.action == MotionEvent.ACTION_DOWN) {
                    iX = event.getX()
                    iY = event.getY()

                    findView(iX,iY)
                }
                return true
            }
        })

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

    private fun makePair(first: ImageView, second: Int) : Pair<ImageView, Int>  {
        return Pair(first, second)
    }

    private fun makeMap(rootView:View) {
        arr_region.add(makePair(rootView.travel_daegu_btn, R.drawable.travel_daegu_icon))
        arr_region.add(makePair(rootView.travel_gyeongsan_btn, R.drawable.travel_gyeongsan_icon))
    }

    private fun findView(aX:Float, aY:Float) {

        var resources: Resources = this.resources

        for(i in arr_region) {
            var bitmap = BitmapFactory.decodeResource(resources, i.second)

            if(bitmap.getPixel(aX.toInt(), aY.toInt()) != 0) {
                Log.d("test","123123")
                i.first.setColorFilter(Color.parseColor("#55523000"))

                val intent = Intent(context, TravelSelectRegion::class.java)
                intent.putExtra("region",i)
                startActivity(intent)
                break
            }
        }
    }
}