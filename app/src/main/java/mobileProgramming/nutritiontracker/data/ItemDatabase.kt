package mobileProgramming.nutritiontracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Item::class), version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context
        ): ItemDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "item_info"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}