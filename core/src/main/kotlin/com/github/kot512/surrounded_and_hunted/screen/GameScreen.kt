package com.github.kot512.surrounded_and_hunted.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.github.kot512.surrounded_and_hunted.SurroundedAndHunted.Companion.JOYSTICK_KNOB_TXTR
import com.github.kot512.surrounded_and_hunted.controls.AimJoystick
import com.github.kot512.surrounded_and_hunted.controls.MovementJoystick
import com.github.kot512.surrounded_and_hunted.entities.EnemyEntity
import com.github.kot512.surrounded_and_hunted.entities.Player
import com.github.kot512.surrounded_and_hunted.tools.Point

class GameScreen: KtxScreen {
//    параметры мира
    companion object {
//    параметры камеры
        val SCREEN_WIDTH = Gdx.graphics.width.toFloat()
        val SCREEN_HEIGHT = Gdx.graphics.height.toFloat()
//    параметры игрока
        val PLAYER_POS =
            Point(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2)

//    параметры
        val BOUNDS_X = 0f..SCREEN_WIDTH
        val BOUNDS_Y = 0f..SCREEN_HEIGHT

        val ENEMY_LIST: MutableList<EnemyEntity> = mutableListOf()

    }
//    константы
    val L_JOYSTICK_POS = Point(150f, 95f)
    val R_JOYSTICK_POS = Point(SCREEN_WIDTH - 550f, 95f)

//    для экрана
    private val camera: Camera = OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT) // камера
    private val hudCamera: Camera = OrthographicCamera() // камера, рисующая интерфейс
    private val viewport: Viewport = FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, hudCamera)

//    графика
    private val batch: SpriteBatch = SpriteBatch()
    private val locationTexture: Texture = Texture("graphics/test_image/pixel_grass_background.png")
    private val jBaseTexture: Texture = Texture("graphics/test_image/joystick_base.png")
    private val jKnobTexture: Texture = Texture("graphics/test_image/joystick_knob.png")
    private val entityTexture: Texture = Texture("graphics/test_image/red_dot_png.png")

//    игровые объекты
    private val stage: Stage = Stage(viewport) // сцена, ответственная за рендер UI
    private val joystick: MovementJoystick =
        MovementJoystick(L_JOYSTICK_POS)
    private val joystick2: AimJoystick =
        AimJoystick(R_JOYSTICK_POS)
    private val player: Player =
        Player(
            entityTexture,
            PLAYER_POS,
            joystick,
            joystick2
        )
    private val enemyTest: EnemyEntity =
        EnemyEntity(
            entityTexture,
            PLAYER_POS,
            player
        )

    init {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_CLEAR_VALUE)

//        рендер игровых объектов
        batch.begin() // начало рендера
        batch.projectionMatrix = camera.combined

        camera.position.set(player.originBasedX, player.originBasedY, 0f)  // обновление позиции камеры
        camera.update()

        batch.draw(locationTexture, 0f, 0f, SCREEN_WIDTH, SCREEN_HEIGHT) // рендерим текстуру локации
        player.draw(batch)

        enemyTest.draw(batch)

        batch.end() // конец рендера

//        рендер HUD
        stage.addActor(joystick)
        stage.addActor(joystick2)
        stage.draw()
    }

    override fun dispose() {
        locationTexture.dispose()
        jBaseTexture.dispose()
        jKnobTexture.dispose()
        entityTexture.dispose()
        JOYSTICK_KNOB_TXTR.dispose()
    }

    override fun resume() {
        super.resume()
    }

    override fun show() {
        super.show()
    }

    override fun hide() {
        super.hide()
    }

    override fun pause() {
        super.pause()
    }
}
