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
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.MotionEventCompat
import androidx.core.view.drawToBitmap
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

        ///경상북도
        arr_region.add(makePair(rootView.travel_daegu_image, R.drawable.travel_daegu))   //대구
        arr_region.add(makePair(rootView.travel_gyeongsan_image, R.drawable.travel_gyeongsan))  //경산
        arr_region.add(makePair(rootView.travel_chilgok_image, R.drawable.travel_chilgok))   //칠곡
        arr_region.add(makePair(rootView.travel_goryeong_image, R.drawable.travel_goryeong))   //고령
        arr_region.add(makePair(rootView.travel_seongju_image, R.drawable.travel_seongju))   //성주
        arr_region.add(makePair(rootView.travel_gimcheon_image, R.drawable.travel_gimcheon))   //김천
        arr_region.add(makePair(rootView.travel_gumi_image, R.drawable.travel_gumi))   //구미
        arr_region.add(makePair(rootView.travel_cheongdo_image, R.drawable.travel_cheongdo))   //청도
        arr_region.add(makePair(rootView.travel_gyeongju_image, R.drawable.travel_gyeongju))   //경주
        arr_region.add(makePair(rootView.travel_sangju_image, R.drawable.travel_sangju))   //상주
        arr_region.add(makePair(rootView.travel_mungyeong_image, R.drawable.travel_mungyeong))   //문경
        arr_region.add(makePair(rootView.travel_yeongcheon_image, R.drawable.travel_yeongcheon))   //영천
        arr_region.add(makePair(rootView.travel_pohang_image, R.drawable.travel_pohang))   //포항
        arr_region.add(makePair(rootView.travel_yeongdeok_image, R.drawable.travel_yeongdeok))   //영덕
        arr_region.add(makePair(rootView.travel_cheongsong_image, R.drawable.travel_cheongsong))   //청송
        arr_region.add(makePair(rootView.travel_gunwi_image, R.drawable.travel_gunwi))   //군위
        arr_region.add(makePair(rootView.travel_uljin_image, R.drawable.travel_uljin))   //울진
        arr_region.add(makePair(rootView.travel_uiseong_image, R.drawable.travel_uiseong))   //의성
        arr_region.add(makePair(rootView.travel_andong_image, R.drawable.travel_andong))   //안동
        arr_region.add(makePair(rootView.travel_yeongyang_image, R.drawable.travel_yeongyang))   //영양
        arr_region.add(makePair(rootView.travel_bonghwa_image, R.drawable.travel_bonghwa))   //봉화
        arr_region.add(makePair(rootView.travel_yeongju_image, R.drawable.travel_yeongju))   //영주
        arr_region.add(makePair(rootView.travel_yecheon_image, R.drawable.travel_yecheon))   //예천

        ///제주도
        arr_region.add(makePair(rootView.travel_jeju_image, R.drawable.travel_jeju))   //제주
        arr_region.add(makePair(rootView.travel_seogwipo_image, R.drawable.travel_seogwipo))   //서귀포


    }

    private fun findView(aX:Float, aY:Float) {

        for(i in arr_region) {

            var bitmap = i.first.drawToBitmap()

            if(bitmap.getPixel(aX.toInt(), aY.toInt()) != 0) {
                i.first.setColorFilter(Color.parseColor("#55523000"))
                break
            }
        }
    }
}