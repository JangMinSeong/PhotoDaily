package com.example.photodaily.data

import androidx.room.*

@Dao
interface PhotoDao {
    @Insert
    fun insert(photo: Photo)

    @Update
    fun update(photo: Photo)

    @Delete
    fun delete(photo: Photo)

    @Query("SELECT * from photo")
    fun getAll() : List<Photo>

}