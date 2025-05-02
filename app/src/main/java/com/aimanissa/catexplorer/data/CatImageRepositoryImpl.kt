package com.aimanissa.catexplorer.data

import android.util.Log
import com.aimanissa.catexplorer.data.network.CatApi
import com.aimanissa.catexplorer.data.network.mapper.toCatImage
import com.aimanissa.catexplorer.data.network.model.CatImageDTO
import com.aimanissa.catexplorer.data.utils.RequestResult
import com.aimanissa.catexplorer.data.utils.mapTo
import com.aimanissa.catexplorer.data.utils.toRequestResult
import com.aimanissa.catexplorer.domain.CatImageRepository
import com.aimanissa.catexplorer.domain.model.CatImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatImageRepositoryImpl @Inject constructor(
    private val api: CatApi
) : CatImageRepository {

    override fun getRandomCatImage(): Flow<RequestResult<CatImage>> {
        val request = flow { emit(api.getRandomImage()) }
            .onEach { result ->
                if (result.isFailure) {
                    Log.e(
                        "CatImageRepositoryImpl",
                        "Error getting data from server. Cause = ${result.exceptionOrNull()}"
                    )
                }
            }
            .map { it.toRequestResult() }

        val start = flowOf<RequestResult<List<CatImageDTO>>>(RequestResult.InProgress())
        return merge(request, start)
            .map { result: RequestResult<List<CatImageDTO>> ->
                result.mapTo { response -> response.first().toCatImage() }
            }
    }
}