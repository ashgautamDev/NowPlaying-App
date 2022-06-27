package com.ashish.nowplayingapp.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ashish.nowplayingapp.model.FavMovie
import junit.framework.Assert.assertTrue
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavMoviesDatabaseTest {


    private lateinit var favDb: FavMoviesDatabase
    private lateinit var favDao: FavMovieDao

    @Before
     fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        favDb = Room.inMemoryDatabaseBuilder(context, FavMoviesDatabase::class.java).build()
        favDao = favDb.getFavMovieDao()
    }

    @After
    fun closeUp() {
        favDb.close()
    }

    @Test
    fun insertWrite() = runBlocking {
        val favMovie = FavMovie(23443L)
        favDao.insertFavMovie(favMovie)
        var movie : String? = null
      val mov = favDao.getAllFavouritesMovies().collect{
           movie = it.lastIndex.toString()

        }
        assertTrue(mov.equals(movie).toString(), true )
    }
}