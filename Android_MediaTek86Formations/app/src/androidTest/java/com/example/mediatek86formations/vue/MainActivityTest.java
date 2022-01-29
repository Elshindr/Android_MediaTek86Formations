package com.example.mediatek86formations.vue;

import static android.os.SystemClock.sleep;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Scenario testant les fonctionnalités de l'application
     */
    @Test
    public void scenario() {
        ////  Vers les toutes les formations
        // MainActivity
        sleep(5000); // Permet de donner le temps au serveur Heroku de s'activer et à l'application de se connecter à la base de donnée distante

        onView(withId(R.id.btnFormations)).perform(click());
        assertEquals("all", Controle.getChoix());

        // FormationsActivity
        onView(withId(R.id.txtFiltre)).perform(typeText("4"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        for (int i = 0; i < 7; i++) {
            onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(1).onChildView(withId(R.id.btnListFavori)).perform(click());
        }
        onView(withId(R.id.txtFiltre)).perform(typeText("5"), closeSoftKeyboard());
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
        onView(withId(R.id.txtFiltre)).perform(typeText("cLiP"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        for (int i = 0; i < 6; i++) {
            int nbRd = (int) ((Math.random()) * 10);
            onData(anything()).inAdapterView(withId(R.id.lstFormations)).atPosition(nbRd).onChildView(withId(R.id.btnListFavori)).perform(click());
        }
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(isRoot()).perform(ViewActions.pressBack());


        //// Vers les favoris
        // MainActivity
        onView(withId(R.id.btnFavoris)).perform(click());
        assertEquals("favoris", Controle.getChoix());

        // FormationsActivity
        onView(withId(R.id.txtFiltre)).perform(typeText("e"), closeSoftKeyboard());
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
        onView(withId(R.id.txtFiltre)).perform(typeText("uMl"), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onView(withId(R.id.txtFiltre)).perform(clearText(), closeSoftKeyboard());
        onView(withId(R.id.btnFiltrer)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());


        // MainActivity
        sleep(1000);
        // End
    }
}