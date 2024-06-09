package com.example.app_com_tam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.app_com_tam.bottonNavigation.AppNavHost
import com.example.app_com_tam.database.DBApp
import com.example.app_com_tam.repository.Repository
import com.example.app_com_tam.ui.theme.App_Com_TamTheme

class MainActivity : ComponentActivity() {

    lateinit var database: DBApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            DBApp::class.java,
            "comtam.db"
        ).addMigrations(MIGRATION_10_16).build()

        val repository = Repository(database)

        setContent {
            App_Com_TamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCom(repository)
                }
            }
        }
    }
}

@Composable
fun AppCom(repository: Repository) {
    val navController = rememberNavController()
    AppNavHost(navHostController = navController, repository)
}

val MIGRATION_10_16 = object : Migration(10, 16) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE dishs RENAME TO temp_dishs")
        database.execSQL("CREATE TABLE IF NOT EXISTS dishs (idDish INTEGER PRIMARY KEY NOT NULL, nameDish TEXT NOT NULL, priceDish REAL NOT NULL, idTypeDish INTEGER NOT NULL, imgDish TEXT NOT NULL, desDish TEXT NOT NULL)")
        database.execSQL("INSERT INTO dishs (idDish, nameDish, priceDish, idTypeDish, imgDish, desDish) SELECT idDish, nameDish, priceDish, idTypeDish, imgDish, desDish FROM temp_dishs")
        database.execSQL("DROP TABLE IF EXISTS temp_dishs")

        database.execSQL("ALTER TABLE TypeDish RENAME TO temp_TypeDish")

        // Tạo lại bảng TypeDish với cấu trúc mới
        database.execSQL("CREATE TABLE IF NOT EXISTS TypeDish (idTypeDish INTEGER PRIMARY KEY NOT NULL, nameType TEXT NOT NULL)")

        // Sao chép dữ liệu từ bảng backup vào bảng mới
        database.execSQL("INSERT INTO TypeDish (idTypeDish, nameType) SELECT idTypeDish, nameType FROM temp_TypeDish")

        // Xóa bảng backup
        database.execSQL("DROP TABLE IF EXISTS temp_TypeDish")
    }
}
