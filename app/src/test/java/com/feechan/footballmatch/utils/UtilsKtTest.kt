package com.feechan.footballmatch.utils

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class UtilsKtTest {
    @Test
    fun testToSimpleString() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("09/12/2018")
        assertEquals("Wed, 12 Sep 2018", toSimpleString(date))
    }
}