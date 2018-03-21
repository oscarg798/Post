package co.com.core

import co.com.core.di.RepositoryComponent
import org.junit.Rule
import org.junit.Test

import it.cosenonjaviste.daggermock.DaggerMock

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class UsecaseTest {



    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}