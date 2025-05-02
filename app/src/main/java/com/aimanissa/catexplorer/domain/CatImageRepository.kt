package com.aimanissa.catexplorer.domain

import com.aimanissa.catexplorer.data.utils.RequestResult
import com.aimanissa.catexplorer.domain.model.CatImage
import kotlinx.coroutines.flow.Flow

interface CatImageRepository {

    fun getRandomCatImage(): Flow<RequestResult<CatImage>>
}