package br.edu.infnet.dr3_tp1_gabriel_couto

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginUITest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun verificarTelaLogin(){
        onView(withHint("Email"))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))

        onView(withHint("Senha"))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))

        onView(withId(R.id.btnEntrar))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))

        onView(withId(R.id.btnRedirectCadastro))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))

    }

    @Test
    fun verificaSeNaoPermiteLogarComCamposEmBranco(){
        onView(withHint("Email"))
            .perform(clearText())

        onView(withHint("Senha"))
            .perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.btnEntrar))
            .perform(click())

        onView(withId(R.id.textView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verificarSeUsuarioValidoConsegueLogar(){
        onView(withHint("Email"))
            .perform(typeText("gabriel@email.com"))

        onView(withHint("Senha"))
            .perform(typeText("123456"), closeSoftKeyboard())

        onView(withId(R.id.btnEntrar))
            .perform(click())

        onView(withId(R.id.listaFuncionarios))
            .check(matches(isDisplayed()))
    }

    @Test
    fun verificaSeUsuarioInvalidoSeLoga(){
        onView(withHint("Email"))
            .perform(typeText("gabriel@email.com"))

        onView(withHint("Senha"))
            .perform(typeText("123456"), closeSoftKeyboard())

        onView(withId(R.id.btnEntrar))
            .perform(click())

        onView(withId(R.id.listaFuncionarios))
            .check(matches(isDisplayed()))
    }

}
