package com.my.cvapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
/*
    interface Suspendable {
        suspend fun suspendFunction()
    }

    class CallsSuspendable(val suspendable: Suspendable) {
        fun callSuspendable() {
            runBlocking {
                suspendable.suspendFunction()
            }
        }
    }

    @Test
    fun testCallingSuspendable() {
        val mockSuspendable = Mockito.mock(Suspendable::class.java)
        val callsSuspendable = CallsSuspendable(mockSuspendable)

        callsSuspendable.callSuspendable()

        runBlocking {
            Mockito.verify(mockSuspendable).suspendFunction()
        }
    }*/
}
