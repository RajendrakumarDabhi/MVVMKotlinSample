package com.example.ravi.mvvmsample.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ravi.mvvmsample.models.User


@Dao
interface UserDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words:List<User>)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("SELECT * from User ORDER BY id ASC")
    fun getAlphabetizedWords(): LiveData<List<User>>


}