package kurpenov.kuat.marvelapp.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeroInfo(
    val id: Int, // вместо int сделать другой индификатор для api
    val heroName: String,
    val photoUrl: String,
    val descriptionHero: String
) : Parcelable
