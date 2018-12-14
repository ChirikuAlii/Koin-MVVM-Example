package chrikualii.info.koinmvvmexample.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by chirikualii on 11/12/18
 * github.com/chirikualii
 */
@Database(
    entities = [MovieEntity::class], version = 1
)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}