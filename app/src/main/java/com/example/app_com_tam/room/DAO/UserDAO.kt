package com.example.app_com_tam.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_com_tam.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Query("SELECT * FROM users")
    fun getAllUser():Flow<List<User>>

    @Query("Select * From users Where username = :userName and password = :pass")
    fun getUserByUsernamePass(userName: String, pass: String) : Flow<User>
    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}