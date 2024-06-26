package com.ostin.tradume.data

import com.ostin.tradume.api.models.WordDTO
import com.ostin.tradume.data.models.Word

internal fun WordDTO.toWord() = Word(
    id = wordId,
    word = word,
    languageId = languageId
)
