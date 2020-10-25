package com.stylingandroid.mdcmotion.widget

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

internal class ShapedBottomNavigationEdgeTreatment(
    private val dotRadius: Float,
    dotPadding: Float
) : EdgeTreatment() {

    var horizontalOffset: Float = 0f

    private val radius = dotPadding + dotRadius * 2f

    override fun getEdgePath(
        length: Float,
        center: Float,
        interpolation: Float,
        shapePath: ShapePath
    ) {
        if (dotRadius == 0f) {
            shapePath.lineTo(length, 0f)
        } else {
            shapePath.buildCradle(length, interpolation)
        }
    }

    private fun ShapePath.buildCradle(length: Float, interpolation: Float) {
        val leftPosition = (horizontalOffset - radius) * interpolation
        val rightPosition = (horizontalOffset + radius) * interpolation
        lineTo(leftPosition, 0f)
        addArc(leftPosition, -radius, rightPosition, radius, START, SWEEP)
        lineTo(length, 0f)
    }

    private companion object {
        private const val START = 180f
        private const val SWEEP = 180f
    }
}
