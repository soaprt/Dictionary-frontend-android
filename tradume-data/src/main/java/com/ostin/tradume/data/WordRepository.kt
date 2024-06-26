package com.ostin.tradume.data

import com.ostin.tradume.api.TradumeApi
import com.ostin.tradume.api.models.ResponseDTO
import com.ostin.tradume.api.models.WordDTO
import com.ostin.tradume.data.models.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val api: TradumeApi,
) {
    fun getAll(): Flow<RequestResult<List<Word>>> {
        val apiRequest = flow { emit(api.words()) }
            .map {it.toRequestResult() }
        val start = flowOf<RequestResult<ResponseDTO<WordDTO>>>(
            RequestResult.InProgress()
        )

        return merge(apiRequest, start).map { result: RequestResult<ResponseDTO<WordDTO>> ->
            result.map { response ->
                response.data.map { it.toWord() }
            }
        }
    }
}
