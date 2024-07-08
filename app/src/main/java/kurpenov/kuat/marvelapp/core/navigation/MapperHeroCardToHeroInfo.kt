package kurpenov.kuat.marvelapp.core.navigation.model

import kurpenov.kuat.marvelapp.core.model.HeroInfo
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.model.HeroCard

fun HeroCard.asHeroInfo(): HeroInfo {
    return HeroInfo (
        id = id,
        heroName = title,
        photoUrl = photoURL,
        descriptionHero = descriptionHero
    )
}
