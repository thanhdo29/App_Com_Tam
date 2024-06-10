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
import com.example.app_com_tam.room.DAO.CardDAO
import com.example.app_com_tam.screens.TestScreen
//import com.example.app_com_tam.database.DBApp

import com.example.app_com_tam.ui.theme.App_Com_TamTheme
import com.example.app_com_tam.viewModel.CartViewModel
import com.example.app_com_tam.viewModel.DishViewModel
import com.example.app_com_tam.viewModel.OrderViewModel
import com.example.app_com_tam.viewModel.TypeDishViewModel

class MainActivity : ComponentActivity() {

    lateinit var database: DBApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            DBApp::class.java,
            "comtam.db"
        ).addMigrations(MIGRATION_1_2).build()

        val repository = Repository(database)
        val repo=Repository(database)
        val typeDishViewModel=TypeDishViewModel(repo)
        val dishViewModel=DishViewModel(repo)
        val orderViewModel=OrderViewModel(repo)
        val cartViewModel=CartViewModel(repo)

        setContent {
            App_Com_TamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppCom(repository)
//                    TestScreen(
//                        typeDishViewModel = typeDishViewModel,
//                        dishViewModel = dishViewModel,
//                        orderViewModel = orderViewModel,
//                        cartViewModel =cartViewModel
//                    )

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

val MIGRATION_1_2 = object : Migration(16, 19) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE dishs RENAME TO temp_dishs")

        database.execSQL("ALTER TABLE temp_dishs ADD COLUMN sales INTEGER NOT NULL DEFAULT 0")

        database.execSQL("""
            CREATE TABLE IF NOT EXISTS dishs (
                idDish INTEGER PRIMARY KEY NOT NULL,
                nameDish TEXT NOT NULL,
                priceDish REAL NOT NULL,
                idTypeDish INTEGER NOT NULL,
                imgDish TEXT NOT NULL,
                desDish TEXT NOT NULL,
                sales INTEGER NOT NULL DEFAULT 0
            )
        """)

        database.execSQL("""
            INSERT INTO dishs (idDish, nameDish, priceDish, idTypeDish, imgDish, desDish, sales)
            SELECT idDish, nameDish, priceDish, idTypeDish, imgDish, desDish, sales FROM temp_dishs
        """)

        database.execSQL("DROP TABLE IF EXISTS temp_dishs")

        database.execSQL("ALTER TABLE TypeDish RENAME TO temp_TypeDish")
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS TypeDish (
                idTypeDish INTEGER PRIMARY KEY NOT NULL,
                nameType TEXT NOT NULL
            )
        """)
        database.execSQL("""
            INSERT INTO TypeDish (idTypeDish, nameType)
            SELECT idTypeDish, nameType FROM temp_TypeDish
        """)
        database.execSQL("DROP TABLE IF EXISTS temp_TypeDish")

        database.execSQL("""
            CREATE TABLE IF NOT EXISTS Cart (
                idCart INTEGER PRIMARY KEY NOT NULL,
                idDish INTEGER NOT NULL,
                quantity INTEGER NOT NULL,
                idCustomer INTEGER NOT NULL
            )
        """)

        database.execSQL("""
            CREATE TABLE IF NOT EXISTS CartDishCrossRef (
                id INTEGER PRIMARY KEY NOT NULL,
                cartId INTEGER NOT NULL,
                dishId INTEGER NOT NULL
            )
        """)
    }
}
