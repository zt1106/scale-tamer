package model.theory

import assertEquals
import cc.zengtian.mtt.model.theory.*
import cc.zengtian.mtt.model.theory.ChordSonority.*
import cc.zengtian.mtt.model.theory.Note.A_FLAT
import cc.zengtian.mtt.model.theory.Note.B
import cc.zengtian.mtt.model.theory.Note.B_FLAT
import cc.zengtian.mtt.model.theory.Note.C
import cc.zengtian.mtt.model.theory.Note.D_FLAT
import cc.zengtian.mtt.model.theory.Note.D_SHARP
import cc.zengtian.mtt.model.theory.Note.E
import cc.zengtian.mtt.model.theory.Note.E_FLAT
import cc.zengtian.mtt.model.theory.Note.F
import cc.zengtian.mtt.model.theory.Note.F_SHARP
import cc.zengtian.mtt.model.theory.Note.G
import cc.zengtian.mtt.model.theory.Note.G_SHARP
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by ZengTian on 9/18/2019.
 */
class ChordTest {
    @Test
    fun `test dimished 7th annotations`() {
        val chord = ActualChord(ActualNote.C, ActualNote.DE, ActualNote.FG, ActualNote.A)
        assertEquals(4, chord.annotations.size)
        chord.annotations.forEachIndexed { idx, annotation ->
            assertEquals(annotation.chordSonority, DIMISHED_7TH)
            assertEquals(idx, annotation.inversion)
        }
    }

    @Test
    fun `test major triad with inversions`() {
        val c0 = Chord(C, G, E)
        val c1 = Chord(E, G, C)
        val c2 = Chord(G, E, C)
        val b0 = Chord(B, D_SHARP, F_SHARP)
        val b1 = Chord(D_SHARP, B, F_SHARP)
        val b2 = Chord(F_SHARP, D_SHARP, B)
        val dF0 = Chord(D_FLAT, F, A_FLAT)
        val dF1 = Chord(F, A_FLAT, D_FLAT)
        val dF2 = Chord(A_FLAT, D_FLAT, F)
        val root = listOf(c0, b0, dF0)
        val first = listOf(c1, b1, dF1)
        val second = listOf(c2, b2, dF2)
        val c = listOf(c0, c1, c2)
        val b = listOf(b0, b1, b2)
        val dF = listOf(dF0, dF1, dF2)
        val all = mutableListOf<Chord>().apply {
            addAll(root)
            addAll(first)
            addAll(second)
        }
        all.forEach {
            assertEquals(1, it.annotations.size)
            assertEquals(MAJOR_TRIAD, it.annotations[0].chordSonority)
        }
        root.forEach { assertEquals(0, it.annotations[0].inversion) }
        first.forEach { assertEquals(1, it.annotations[0].inversion) }
        second.forEach { assertEquals(2, it.annotations[0].inversion) }
        c.forEach { assertEquals(C, it.beforeInversionRootNote(it.annotations[0])) }
        b.forEach { assertEquals(B, it.beforeInversionRootNote(it.annotations[0])) }
        dF.forEach { assertEquals(D_FLAT, it.beforeInversionRootNote(it.annotations[0])) }
    }

    @Test
    fun `test chord sonority`() {
        Chord(C, E, G).annotations[0].chordSonority.assertEquals(MAJOR_TRIAD)
        Chord(C, E_FLAT, G).annotations[0].chordSonority.assertEquals(MINOR_TRIAD)
        Chord(C, E_FLAT, F_SHARP).annotations[0].chordSonority.assertEquals(DIMISHED_TRIAD)
        Chord(C, E, G_SHARP).annotations[0].chordSonority.assertEquals(AUGMENTED_TRIAD)
        Chord(C, E, G, B).annotations[0].chordSonority.assertEquals(MAJOR_MAJOR_7TH)
        Chord(C, E, G, B_FLAT).annotations[0].chordSonority.assertEquals(DOMINANT_7TH)
        Chord(C, E_FLAT, G, B).annotations[0].chordSonority.assertEquals(MINOR_MAJOR_7TH)
        Chord(C, E_FLAT, F_SHARP, B_FLAT).annotations[0].chordSonority.assertEquals(HALF_DIMISHED_7TH)
        Chord(C, E_FLAT, F_SHARP, Note.of(ActualNote.B, Accidental.DOUBLE_FLAT)).annotations[0].chordSonority.assertEquals(DIMISHED_7TH)
    }
}
