package com.ostin.tradume.api.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO<E>(
    val data: List<E>
)
