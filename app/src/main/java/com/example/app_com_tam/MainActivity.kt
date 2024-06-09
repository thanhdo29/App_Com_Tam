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

lateinit var DATABASE_INSTANCE : DBApp
class MainActivity : ComponentActivity() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            DBApp::class.java,"comtam.db"
        ).addMigrations(MIGRATION_10_15).build()
    }

    val MIGRATION_10_15 = object : Migration(10, 15) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE dishs RENAME TO temp_tableName")
            database.execSQL("CREATE TABLE IF NOT EXISTS dishs (idDish INTEGER PRIMARY KEY, nameDish TEXT, priceDish REAL, idTypeDish INTEGER, imgDish TEXT, desDish TEXT)")
            database.execSQL("INSERT INTO dishs (idDish, nameDish, priceDish, idTypeDish, imgDish, desDish) SELECT idDish, nameDish, priceDish, idTypeDish, imgDish, CAST(desDish AS TEXT) FROM temp_tableName")
            database.execSQL("DROP TABLE IF EXISTS temp_tableName")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DATABASE_INSTANCE=database
        val repository:Repository=Repository(database)
        setContent {
            App_Com_TamTheme {

                // A surface container using the 'background' color from the theme
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
    val navController= rememberNavController()
    AppNavHost(navHostController = navController,repository)
}