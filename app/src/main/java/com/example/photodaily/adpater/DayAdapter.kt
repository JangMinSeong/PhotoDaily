package com.example.photodaily.adpater

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photodaily.R
import com.example.photodaily.data.Photo
import com.example.photodaily.ui.main.DayDiaryOne

class DayAdapter(val dayList : List<Photo>) : RecyclerView.Adapter<DayAdapter.CustomViewHolder>() {

    //뷰홀더가 처음 생성될때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return CustomViewHolder(view).apply { //클릭시 액션
            itemView.setOnClickListener { //여기서 itemview는 뷰홀더의 아이템들을 의미한다.
                val curPos = adapterPosition //누른 뷰의 순서값
                val profile: Photo = dayList.get(curPos) //객체형태로 번호에 맞게 가져오기
                Toast.makeText(
                    parent.context,
                    "이름 : ${profile.subject}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        //뷰홀더에 뷰를 넘겨주고 이 것을 반환한다.
    }

    //재활용해주는 곳 및 값을 넣어주는 곳
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val photo : Photo = dayList.get(position)
        Log.d("jeong", "onBindViewHolder: "+dayList.get(position).photo)
        Glide.with(holder.itemView).load(dayList.get(position).photo.toUri()).into(holder.profile)
        //holder.profile.setImageResource(dayList.get(position).photo)
        holder.name.text = dayList.get(position).subject
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DayDiaryOne::class.java)
            intent.putExtra("ID", dayList.get(position).subject)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    //리스트의 갯수를 적어준다
    override fun getItemCount(): Int {
        return dayList.size
    }

    //뷰홀더 클래스(음료수처럼 잡아주는 홀더)
    //이곳에서 파인드뷰아이디로 리스트 아이템에 있는 뷰들을 참조한다.
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profile = itemView.findViewById<ImageView>(R.id.imageView_day) //사진
        val name = itemView.findViewById<TextView>(R.id.textView_day) //이름
    }

}