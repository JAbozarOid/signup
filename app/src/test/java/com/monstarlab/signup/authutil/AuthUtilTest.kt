package com.monstarlab.signup.authutil

import com.monstarlab.signup.util.AuthUtil
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class AuthUtilTest {


    @Test
    fun `is email valid with @ character returns false`() {
        val email = AuthUtil.isEmailValid("Aboz@r")
        assertFalse(email)
    }

    @Test
    fun `is email valid with email format returns true`() {
        val email = AuthUtil.isEmailValid("abozar.raghibdoust@gmail.com")
        assertTrue(email)
    }

    @Test
    fun `is password less than 8 char return false`() {
        val password = AuthUtil.isPasswordValid("Ar13650")
        assertFalse(password)
    }

    @Test
    fun `is password does not contain letters return false`() {
        val password = AuthUtil.isPasswordValid("13650698")
        assertFalse(password)
    }

    @Test
    fun `is password does not contain numbers return false`() {
        val password = AuthUtil.isPasswordValid("abozarraghibdoust")
        assertFalse(password)
    }

    @Test
    fun `is password at least 8 char with number and letters return true`() {
        val password = AuthUtil.isPasswordValid("ar13650319")
        assertTrue(password)
    }

}


