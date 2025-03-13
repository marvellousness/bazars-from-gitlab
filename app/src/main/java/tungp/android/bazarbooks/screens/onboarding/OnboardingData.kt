package tungp.android.bazarbooks.screens.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import tungp.android.bazarbooks.R

internal object OnboardingData {

    fun introItemList(): List<IntroItem> {
        return listOf(
            IntroItem(
                R.string.intro_1_title,
                R.string.intro_1_subtitle,
                R.string.continue_text,
                R.drawable.intro_1
            ),
            IntroItem(
                R.string.intro_2_title,
                R.string.intro_2_subtitle,
                R.string.get_started,
                R.drawable.intro_2
            ),
            IntroItem(
                R.string.intro_3_title,
                R.string.intro_3_subtitle,
                R.string.get_started,
                R.drawable.intro_3
            )
        )
    }
}

data class IntroItem(
    @StringRes var title: Int,
    @StringRes val subtitle: Int,
    @StringRes val buttonText: Int,
    @DrawableRes val drawableRes: Int,
)