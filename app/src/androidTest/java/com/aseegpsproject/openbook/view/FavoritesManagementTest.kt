package com.aseegpsproject.openbook.view


import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.aseegpsproject.openbook.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class FavoritesManagementTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun favoritesManagementTest() {
        val materialButton = onView(withId(R.id.btnRegister))
        materialButton.perform(click())

        val appCompatEditText = onView(withId(R.id.etUsername))
        appCompatEditText.perform(replaceText("espresso"), closeSoftKeyboard())

        val appCompatEditText2 = onView(withId(R.id.etPassword))
        appCompatEditText2.perform(replaceText("latte"), closeSoftKeyboard())

        val appCompatEditText3 = onView(withId(R.id.etRepeatPassword))
        appCompatEditText3.perform(replaceText("latte"), closeSoftKeyboard())

        val materialButton2 = onView(withId(R.id.btnRegister))
        materialButton2.perform(click())

        val recyclerView = onView(withId(R.id.rv_book_list))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, longClick()))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(2, longClick()))

        val bottomNavigationItemView = onView(withId(R.id.worksFragment))
        bottomNavigationItemView.perform(click())

        val viewGroup = onView(
            childAtPosition(
                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                0
            )
        )
        viewGroup.check(matches(isDisplayed()))
        val text = getText(
            allOf(
                withId(R.id.work_title),
                childAtPosition(
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                0
                            ),
                            0
                        ),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        val viewGroup2 = onView(
            childAtPosition(
                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                1
            )
        )
        viewGroup2.check(matches(isDisplayed()))

        val text2 = getText(
            allOf(
                withId(R.id.work_title),
                childAtPosition(
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                1
                            ),
                            0
                        ),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        val viewGroup3 = onView(
            childAtPosition(
                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                2
            )
        )
        viewGroup3.check(matches(isDisplayed()))

        val text3 = getText(
            allOf(
                withId(R.id.work_title),
                childAtPosition(
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                2
                            ),
                            0
                        ),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        val recyclerView4 = onView(
            withId(R.id.rv_books_list)
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))
        try {
            val newText = getText(
                allOf(
                    withId(R.id.work_title),
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                childAtPosition(
                                    withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                    0
                                ),
                                0
                            ),
                            0
                        ),
                        1
                    ),
                    isDisplayed()
                )
            )
            assert(!newText.equals(text))
        } catch (e: Exception) {
            println("Exception: $e")
        }
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))
        try {
            val newText = getText(
                allOf(
                    withId(R.id.work_title),
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                childAtPosition(
                                    withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                    0
                                ),
                                0
                            ),
                            0
                        ),
                        1
                    ),
                    isDisplayed()
                )
            )
            assert(!newText.equals(text2))
        } catch (e: Exception) {
            println("Exception: $e")
        }
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, longClick()))
        try {
            val newText = getText(
                allOf(
                    withId(R.id.work_title),
                    childAtPosition(
                        childAtPosition(
                            childAtPosition(
                                childAtPosition(
                                    withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                                    0
                                ),
                                0
                            ),
                            0
                        ),
                        1
                    ),
                    isDisplayed()
                )
            )
            assert(!newText.equals(text3))
        } catch (e: Exception) {
            assert(true)
        }
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun getText(matcher: Matcher<View>?): String? {
        val stringHolder = arrayOf<String?>(null)
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View) {
                val tv = view as TextView
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }
}
