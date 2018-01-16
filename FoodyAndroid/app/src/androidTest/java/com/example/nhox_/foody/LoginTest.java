package com.example.nhox_.foody;

import android.support.test.espresso.FailureHandler;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.nhox_.foody.Activity.Login_Activity;
import com.example.nhox_.foody.Activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import Model.TypeRestaurant;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
/**
 * Created by nhox_ on 20/12/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    public final ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void beforeTest(){
        onView(withId(R.id.user)).perform(click());
        onView(withId(R.id.login_btn)).perform(click());
        onView(withId(R.id.show_include_login_email)).perform(click());
    }
    @Test
    public void should1LoginFailWithWrongUsername(){
        //fail
        onView(withId(R.id.username)).perform(typeText("something"),closeSoftKeyboard());
        onView(withId(R.id.login_email_btn)).perform(click());
        onView(withText("No fill password")).inRoot(withDecorView(not(is(activity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void should2LoginFailWithWrongPassword(){
        //fail
        onView(withId(R.id.password)).perform(typeText("something"),closeSoftKeyboard());
        onView(withId(R.id.login_email_btn)).perform(click());
        onView(withText("No fill username")).inRoot(withDecorView(not(is(activity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
    @Test
    public void should3LoginSuccessWithTrueData(){
        //pass
        onView(withId(R.id.username)).perform(typeText("kien"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("1234"),closeSoftKeyboard());
        onView(withId(R.id.login_email_btn)).perform(click());
        onView(withText("User không tồn tại")).inRoot(withDecorView(not(is(activity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
    @Test
    public void should4LoginSuccessWithTrueData(){
        //pass

        onView(withId(R.id.username)).perform(typeText("kien"),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123"),closeSoftKeyboard());
        onView(withId(R.id.login_email_btn)).perform(click());
        onView(withText("Xem hoạt động")).check(matches(isDisplayed()));
    }

}
