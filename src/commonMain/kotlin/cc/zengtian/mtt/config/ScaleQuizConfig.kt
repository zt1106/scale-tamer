package cc.zengtian.mtt.config

import cc.zengtian.mtt.theory.Key
import cc.zengtian.mtt.theory.Scale
import cc.zengtian.mtt.util.containsNot
import kotlinx.serialization.Serializable

/**
 * Created by ZengTian on 2019/9/21.
 */
@Serializable
data class ScaleQuizConfig (
    val selectedKeys: Set<String> = Key.values().filter {
        val excluded = listOf(Key.C, Key.G_FLAT, Key.C_FLAT, Key.C_SHARP)
        excluded.containsNot(it)
    }.map { it.name }.toSet(),
    val selectedScales: Set<String> = setOf(Scale.MAJOR.name),
    val questionCount: Int = 0,
    val selectedAnswerType: Set<ScaleQuestionAnswerType> = setOf(ScaleQuestionAnswerType.NOTE, ScaleQuestionAnswerType.NUM),
    val selectedNoteDisplayType: NoteDisplayType = NoteDisplayType.LATIN
) {
    init {
        require(selectedKeys.isNotEmpty())
        require(selectedScales.isNotEmpty())
        require(selectedAnswerType.isNotEmpty())
        require(questionCount >= 0)
    }
}


enum class ScaleQuestionAnswerType {
    KEY, SCALE, NUM, NOTE
}

enum class NoteDisplayType {
    LATIN, STAFF
}
