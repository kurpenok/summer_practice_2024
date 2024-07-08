package kurpenov.kuat.marvelapp.network

import kurpenov.kuat.marvelapp.core.model.HeroInfo
import kurpenov.kuat.marvelapp.core.repositore.network.Resource
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.model.HeroCard

interface MarvelNetworkRepository {
    suspend fun getAllHeroes() : Resource<List<HeroCard>>
    suspend fun getHeroInfo(characterId: Int) : Resource<HeroInfo>
}
