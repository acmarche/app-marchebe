package be.marche.www.utils

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject

class Analytics @Inject constructor(
    private var firebaseAnalytics: FirebaseAnalytics
) {

    fun logItem(id: String, name: String, type: String) {

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, type)
        }
    }

    fun log(id: String, name: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
            param(FirebaseAnalytics.Param.ITEM_ID, id)
            param(FirebaseAnalytics.Param.ITEM_NAME, name)
        }
    }

    fun logEvent(id: String, nameEvent: String, name: String, fullText: String) {
        firebaseAnalytics.logEvent("share_image") {
            param("image_name", name)
            param("full_text", fullText)
        }
    }

    fun logScreen(id: String, screenName: String, name: String, fullText: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }

    fun logHomePage() {
        log("homepage", "HomePage")
    }

    fun logPage(name: String) {
        log(name, "Page $name")
    }

}
