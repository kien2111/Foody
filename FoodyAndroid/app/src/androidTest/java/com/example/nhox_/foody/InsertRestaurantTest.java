package com.example.nhox_.foody;


import android.os.Build;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.nhox_.foody.Activity.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import Model.City;
import Model.District;
import Model.TypeRestaurant;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.EasyMock2Matchers.equalTo;

/**
 * Created by nhox_ on 19/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class InsertRestaurantTest {
    @Rule public final ActivityTestRule<MainActivity> loading = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + loading.getActivity().getPackageName()
                            + " android.permission.READ_EXTERNAL_STORAGE");
        }
    }
    @Test
    public void shouldbelauchtomainactivity(){
        onView(withText("Ăn gì")).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.plusButton)).perform(click());
        onView(withId(R.id.tao_dia_diem)).perform(click());
        onView(withId(R.id.chon_city_btn)).perform(click());
        onData(allOf(instanceOf(City.class),withContentCity("TP.HCM"))).perform(click());
        onView(withId(R.id.chon_dist_btn)).perform(click());
        onData(allOf(instanceOf(District.class),withContentDistrict("Quận 1"))).perform(click());
        onView(withId(R.id.namerest_editText)).perform(typeText("restaurant name"),closeSoftKeyboard());
        onView(withId(R.id.loaihinhdiadiem_lnr_btn)).perform(click());
        onData(allOf(is(instanceOf(TypeRestaurant.class)),withChildName("Buffet")))
                .inAdapterView(withId(R.id.expandlistviewdiadiem))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    public static Matcher<Object> withContentCity(final String content) {
        return new BoundedMatcher<Object, City>(City.class) {
            @Override
            public boolean matchesSafely(City myObj) {
                return myObj.getNamecity().equals(content);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with content '" + content + "'");
            }
        };
    }
    public static Matcher<Object> withContentDistrict(final String content) {
        return new BoundedMatcher<Object, District>(District.class) {
            @Override
            public boolean matchesSafely(District myObj) {
                return myObj.getNamedist().equals(content);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with content '" + content + "'");
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static Matcher<Object> withChildName(final String content) {
        return new BoundedMatcher<Object, TypeRestaurant>((Class<TypeRestaurant>)(Class<?>)List.class){

            @Override
            public boolean matchesSafely (final TypeRestaurant typeRestaurant){
                System.out.println("check value : "+typeRestaurant.getNametype());
                return content.matches(typeRestaurant.getNametype());
            }

            @Override
            public void describeTo (Description description){
                description.appendText("with content '" + content + "'");
            }
        } ;
    }
}
