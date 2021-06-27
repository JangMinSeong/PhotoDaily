package com.example.photodaily.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1,exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao() : PhotoDao

    companion object{
        private var instance : PhotoDatabase? = null

        @Synchronized
        fun getinstance(context: Context):PhotoDatabase?{
            if(instance == null){
                synchronized(PhotoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotoDatabase::class.java,
                        "photo-database"
                    ).allowMainThreadQueries() // 강제로 메인 쓰레드에서 실행 시키도록 함.
                        .build()
                }
            }
            return instance
        }
    }
}
/*
*
*
 */