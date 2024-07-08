package kurpenov.kuat.marvelapp.network

import androidx.compose.ui.graphics.Color
import kurpenov.kuat.marvelapp.BuildConfig.PRIVATE_KEY_MARVEL
import kurpenov.kuat.marvelapp.core.Hashing
import kurpenov.kuat.marvelapp.core.model.HeroInfo
import kurpenov.kuat.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import kurpenov.kuat.marvelapp.core.repositore.network.BaseRepo
import kurpenov.kuat.marvelapp.core.repositore.network.Resource
import kurpenov.kuat.marvelapp.network.model.MarvelAPI
import kurpenov.kuat.marvelapp.network.model.Result
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.model.HeroCard
import kotlin.random.Random

class MarvelNetworkRepositoryImpl constructor(private val quest: QuestInterface) :
    MarvelNetworkRepository,
    BaseRepo() {
    override suspend fun getAllHeroes(): Resource<List<HeroCard>> {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val response: Resource<MarvelAPI> = safeApiCall { quest.getAllHeroes(hash = hash) }
        val res: List<Result>? = response.data?.data?.results
        val error = response?.message
        val rand = Random(1828281)
        val data =  res?.map {
            HeroCard(
                id = it.id,
                title = it.name,
                descriptionHero = it.description,
                photoURL = "${it.thumbnail.path}.${it.thumbnail.extension}",
                color = Color(rand.nextLong(0xFFFFFFFF)) // пока будет случайный цвет, потом придумаую
            )
        }
        return if (data == null) Resource.Error(error!!) else Resource.Success(data)
    }

    override suspend fun getHeroInfo(characterId: Int): Resource<HeroInfo> {
        val hash = Hashing.md5("${1}${PRIVATE_KEY_MARVEL}${PUBLIC_KEY_MARVEL}")
        val response: Resource<MarvelAPI> =
            safeApiCall { quest.getHeroInfo(hash = hash, characterId = characterId) }
        val res: List<Result>? =
            response.data?.data?.results
        val data = res?.get(0)?.let {
            HeroInfo(
                id = it.id,
                heroName = it.name,
                photoUrl = "${it.thumbnail.path}.${it.thumbnail.extension}",
                descriptionHero = it.description
            )
        }
        val error = response.message
        return if (data == null) Resource.Error(error!!)
        else Resource.Success(data)
    }
}
