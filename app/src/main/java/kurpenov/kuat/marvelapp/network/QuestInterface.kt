package kurpenov.kuat.marvelapp.network

import kurpenov.kuat.marvelapp.BuildConfig.PUBLIC_KEY_MARVEL
import kurpenov.kuat.marvelapp.core.Hashing
import kurpenov.kuat.marvelapp.network.model.MarvelAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuestInterface {

    @GET("/v1/public/characters?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getAllHeroes(@Query("hash") hash: String): Response<MarvelAPI>

    @GET("/v1/public/characters/{characterId}?ts=1&apikey=${PUBLIC_KEY_MARVEL}")
    suspend fun getHeroInfo(
        @Path("characterId") characterId: Int,
        @Query("hash") hash: String,
    ): Response<MarvelAPI>
}
