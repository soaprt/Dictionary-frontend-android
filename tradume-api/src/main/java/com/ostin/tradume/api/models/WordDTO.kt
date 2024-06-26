package com.ostin.tradume.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordDTO(
    @SerialName("id") val wordId: Int,
    @SerialName("word") val word: String,
    @SerialName("languageId") val languageId: Int
)
