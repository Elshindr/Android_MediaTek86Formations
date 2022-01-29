package com.example.mediatek86formations.vue;

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.JMock1Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;

@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Scenario testant les fonctionnalités de l'application
     */
    @Test
    public void scenario() {
        final String strDate = "2020-12-28 22:00:29";
        final String expectedPattern = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        Date date = new Date();

        {
            try {
                date = formatter.parse(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ////  Vers les toutes les formations
        // MainActivity
        sleep(4000); // Permet de donner le temps au serveur Heroku de s'activer et à l'application de se connecter à la base de donnée distante
        onView(withId(R.id.btnFormations)).perform(click());
        assertEquals("all", Controle.getChoix());

        // FormationsActivity
        onView(withId(R.id.txtFiltre)).perform(typeText("4"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        for (int i = 0; i < 7; i++) {
            onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(1).onChildView(withId(R.id.btnListFavori)).perform(click());
        }
        onView(withId(R.id.txtFiltre)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());

        // UneFormationActivity
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.txtListPublishedAt)).perform(click());
        onView(withId(R.id.btnPicture)).perform(click());

        // VideoActivity
        onView(withId(R.id.wbvYoutube)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        // UneFormationActivity
        onView(isRoot()).perform(ViewActions.pressBack());


        // FormationsActivity
        onView(withId(R.id.txtFiltre)).perform(typeText("u"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        for (int i = 0; i < 5; i++) {
            onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(i).onChildView(withId(R.id.btnListFavori)).perform(click());
        }
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(isRoot()).perform(ViewActions.pressBack());


        //// Vers les favoris
        // MainActivity
        onView(withId(R.id.btnFavoris)).perform(click());
        assertEquals("favoris", Controle.getChoix());

        // FormationsActivity
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(1).onChildView(withId(R.id.btnListFavori)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(typeText("6"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());

        // UneFormationActivity
        onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(0).onChildView(withId(R.id.txtListPublishedAt)).perform(click());
        onView(withId(R.id.btnPicture)).perform(click());

        // VideoActivity
        onView(withId(R.id.wbvYoutube)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        // UneFormationActivity
        onView(isRoot()).perform(ViewActions.pressBack());

        // FormationsActivity
        onView(withId(R.id.txtFiltre)).perform(typeText("e"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        sleep(3000);
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        sleep(3000);
        onView(isRoot()).perform(ViewActions.pressBack());


        // MainActivity
        sleep(1000);
        // End

    }
}