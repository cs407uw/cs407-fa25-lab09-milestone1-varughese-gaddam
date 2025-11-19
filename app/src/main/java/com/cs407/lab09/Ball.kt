package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val prevVX = velocityX
        val prevVY = velocityY
        val prevAX = accX
        val prevAY = accY

        accX = xAcc
        accY = yAcc


        // Calculate using velocity equation (v_t1)
        velocityX = prevVX + (0.5f * (prevAX + accX) * dT)
        velocityY = prevVY + (0.5f * (prevAY + accY) * dT)

        // Calculate using distance equation
        posX += prevVX * dT + (dT * dT / 6f) * (3f * prevAX + accX)
        posY += prevVY * dT + (dT * dT / 6f) * (3f * prevAY + accY)

        // Check boundaries
        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // (Check all 4 walls: left, right, top, bottom)

        // Left
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        // Right
        if (posX + ballSize > backgroundWidth) {
            posX = backgroundWidth - ballSize
            velocityX = 0f
            accX = 0f
        }

        // Top
        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f

        }

        // Bottom
        if (posY + ballSize > backgroundHeight) {
            posY = backgroundHeight - ballSize
            velocityY = 0f
            accY = 0f
        }

    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundWidth - ballSize) / 2f

        velocityX = 0f
        velocityY = 0f

        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}