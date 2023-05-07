package com.github.kot512.surrounded_and_hunted.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.github.kot512.surrounded_and_hunted.tools.Point

class Player(
    texture: Texture,
    width: Float,
    height: Float,
    position: Point,
) : BaseEntity(texture, width, height, position) {
    private val inputController = InputController()

    fun setInputController() {
        Gdx.input.inputProcessor = inputController
    }

    fun checkUserInput(
        joystickDeviation: Float,
        joystickDirectionX: Float,
        joystickDirectionY: Float
    ) {

    }

    override fun update() {
        TODO("Not yet implemented")
    }


    fun move() {

    }

    override fun dealDamage() {
        TODO("Not yet implemented")
    }

    override fun receiveDamage() {
        TODO("Not yet implemented")
    }

    override fun die() {
        TODO("Not yet implemented")
    }








}
