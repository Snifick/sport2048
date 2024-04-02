package game.matchtwo.sportbm.yespye

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import game.matchtwo.sportbm.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BochonkiGame(
    refresh: MutableState<Boolean>, score: MutableState<Int>,
    bestScore: MutableState<Int>,
    revert: MutableState<Boolean>,
    bochonki: MutableState<Int>
) {


    val bochonok = remember(refresh.value) {
        (0..15).map {
            mutableStateOf(0)
        }

    }
    val lastSavedField = remember(refresh.value) {
        (0..15).map {
            mutableStateOf(0)
        }
    }
    LaunchedEffect(key1 = revert.value, block = {
        if (revert.value) {
            (0..15).forEach { pos ->
                bochonok[pos].value = lastSavedField[pos].value
            }
        }
    })

    LaunchedEffect(key1 = refresh.value, block = {
        bochonok[0].value = 2
        bochonok[1].value = 2


    })
    val context = LocalContext.current as Activity

    val sharedPreferences = remember {
        context.getSharedPreferences("bochonki", Context.MODE_PRIVATE)
    }
    LaunchedEffect(key1 = Unit, block = {
        bestScore.value = sharedPreferences.getInt("bestscore", 0)
    })
    val weAlreadyDrag = remember {
        mutableStateOf(false)
    }
    val startPoint = remember {
        mutableStateOf(Offset.Zero)
    }
    val selectedDirection: MutableState<Direction> = remember {
        mutableStateOf(Direction.NONE)
    }
    val pixelsOffset = remember {
        mutableStateOf(0f)
    }
    val completedSwap = remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current.density
    val block = remember {
        mutableStateOf(false)
    }
    LazyVerticalGrid(
        userScrollEnabled = false, columns = GridCells.Fixed(4),
        modifier = Modifier
            .padding(16.dp)
            .size(360.dp)
            .surface(
                RoundedCornerShape(16.dp),
                Color(0xFF2B2B2B),
                BorderStroke(1.dp, Color(0xFFFF4C4C)),
                4.dp
            )
            .drawBehind {

                var startPointX = 0f
                repeat(4) {
                    drawLine(
                        Color(0xFFFF4C4C),
                        Offset(startPointX, 0f),
                        Offset(startPointX, this.size.height)
                    )
                    startPointX += this.size.width / 4
                }
                var startPointY = 0f
                repeat(4) {
                    drawLine(
                        Color(0xFFFF4C4C),
                        Offset(0f, startPointY),
                        Offset(this.size.width, startPointY)
                    )
                    startPointY += this.size.height / 4
                }

            }
            .pointerInteropFilter {
                if (!block.value) {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            if (!block.value) {
                                weAlreadyDrag.value = true
                                startPoint.value = Offset(it.x, it.y)


                                (0..15).forEach { pos ->
                                    lastSavedField[pos].value = bochonok[pos].value
                                }

                            }

                        }

                        MotionEvent.ACTION_MOVE -> {

                            if (selectedDirection.value == Direction.NONE) {


                                if (it.x - startPoint.value.x > 25f) {
                                    selectedDirection.value = Direction.RIGHT
                                    block.value = true
                                }
                                if (it.x - startPoint.value.x < -25f) {
                                    selectedDirection.value = Direction.LEFT
                                    block.value = true
                                }
                                if (it.y - startPoint.value.y < -25f) {
                                    selectedDirection.value = Direction.TOP
                                    block.value = true
                                }
                                if (it.y - startPoint.value.y > 25f) {
                                    selectedDirection.value = Direction.BOT
                                    block.value = true
                                }

                            }
                            when (selectedDirection.value) {
                                Direction.BOT, Direction.TOP -> {
                                    pixelsOffset.value = (it.y - startPoint.value.y) / density
                                }

                                Direction.RIGHT, Direction.LEFT -> {
                                    pixelsOffset.value = (it.x - startPoint.value.x) / density
                                }

                                else -> {

                                }
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            if (block.value) {

                                pixelsOffset.value = 0f
                                completedSwap.value = false
                                weAlreadyDrag.value = false
                            }


                        }
                    }
                }
                true
            }) {
        itemsIndexed(
            bochonok,
            key = { index: Int, item: MutableState<Int> -> index }) { index, it ->

            val scale = remember {
                Animatable(1f)
            }
            val canDelete = remember {
                mutableStateOf(false)
            }
            val individualOffsetX = remember {
                Animatable(0f)
            }
            val individualOffsetY = remember {
                Animatable(0f)
            }
            val weAreMovingItems = remember {
                mutableStateOf(false)
            }
            LaunchedEffect(key1 = selectedDirection.value, block = {

                when (selectedDirection.value) {
                    Direction.LEFT -> {
                        repeat(4) { count ->
                            try {

                                if (it.value != 0 && bochonok[index - 1].value == 0 &&
                                    index % 4 != 0 && !weAreMovingItems.value
                                ) {
                                    weAreMovingItems.value = true


                                    individualOffsetX.animateTo(-90f, tween(120))
                                    bochonok[index - 1].value = bochonok[index].value
                                    bochonok[index].value = 0
                                    individualOffsetX.snapTo(0f)
                                    weAreMovingItems.value = false

                                } else {
                                    delay(140)
                                }
                            } catch (e: Exception) {

                            }
                        }
                        try {
                            if (selectedDirection.value == Direction.LEFT &&
                                bochonok[index - 1].value == it.value && (index) % 4 != 0
                                && it.value != 0
                            ) {
                                if (index + 1 < 16) {
                                    if (bochonok[index + 1].value == it.value) {
                                        throw Exception()
                                    }
                                }


                                scale.animateTo(0f, tween(300))
                                bochonok[index - 1].value *= 2
                                bochonok[index].value = 0
                                weAlreadyDrag.value = false
                                canDelete.value = true
                                scale.snapTo(1f)

                            }
                        } catch (e: Exception) {

                        }
                    }

                    Direction.RIGHT -> {
                        repeat(4) { count ->
                            try {
                                if (it.value != 0 && bochonok[index + 1].value == 0 &&
                                    (index + 1) % 4 != 0 && !weAreMovingItems.value
                                ) {
                                    weAreMovingItems.value = true



                                    individualOffsetX.animateTo(90f, tween(120))
                                    bochonok[index + 1].value = bochonok[index].value
                                    bochonok[index].value = 0
                                    individualOffsetX.snapTo(0f)
                                } else {
                                    delay(140)
                                }
                                weAreMovingItems.value = false

                            } catch (e: Exception) {

                            }

                        }
                        try {
                            if (selectedDirection.value == Direction.RIGHT &&
                                bochonok[index + 1].value == it.value && (index + 1) % 4 != 0
                                && it.value != 0
                            ) {
                                if (index - 1 >= 0) {
                                    if (bochonok[index - 1].value == it.value) {
                                        throw Exception()
                                    }
                                }
                                scale.animateTo(0f, tween(300))
                                bochonok[index + 1].value *= 2
                                bochonok[index].value = 0
                                weAlreadyDrag.value = false
                                canDelete.value = true
                                scale.snapTo(1f)

                            }
                        } catch (_: Exception) {

                        }

                    }

                    Direction.TOP -> {
                        repeat(4) { count ->
                            try {
                                if (it.value != 0 && bochonok[index - 4].value == 0 &&
                                    index > 3 && !weAreMovingItems.value
                                ) {
                                    weAreMovingItems.value = true


                                    individualOffsetY.animateTo(-90f, tween(120))
                                    bochonok[index - 4].value = bochonok[index].value
                                    bochonok[index].value = 0
                                    individualOffsetY.snapTo(0f)

                                    weAreMovingItems.value = false
                                } else {
                                    delay(140)
                                }
                            } catch (e: Exception) {

                            }
                        }
                        try {
                            if (selectedDirection.value == Direction.TOP &&
                                bochonok[index - 4].value == it.value && (index) > 3
                                && it.value != 0
                            ) {

                                if (index + 4 < 16) {
                                    if (bochonok[index + 4].value == it.value) {
                                        throw Exception()
                                    }

                                }


                                scale.animateTo(0f, tween(300))
                                bochonok[index - 4].value *= 2
                                bochonok[index].value = 0
                                weAlreadyDrag.value = false
                                canDelete.value = true
                                scale.snapTo(1f)

                            }
                        } catch (e: Exception) {

                        }

                    }

                    Direction.BOT -> {
                        repeat(4) { count ->
                            try {
                                if (it.value != 0 && bochonok[index + 4].value == 0 &&
                                    index < 12 && !weAreMovingItems.value
                                ) {
                                    weAreMovingItems.value = true


                                    individualOffsetY.animateTo(90f, tween(120))
                                    bochonok[index + 4].value = bochonok[index].value
                                    bochonok[index].value = 0
                                    individualOffsetY.snapTo(0f)
                                    weAreMovingItems.value = false

                                } else {
                                    delay(140)
                                }
                            } catch (e: Exception) {

                            }
                        }
                        try {
                            if (selectedDirection.value == Direction.BOT &&
                                bochonok[index + 4].value == it.value && (index) < 12
                                && it.value != 0
                            ) {

                                if (index - 4 >= 0) {
                                    if (bochonok[index - 4].value == it.value) {
                                        throw Exception()
                                    }
                                }

                                scale.animateTo(0f, tween(300))
                                bochonok[index + 4].value *= 2
                                bochonok[index].value = 0
                                weAlreadyDrag.value = false
                                canDelete.value = true
                                scale.snapTo(1f)
                            }
                        } catch (e: Exception) {

                        }


                    }

                    else -> {

                    }

                }
                if (selectedDirection.value != Direction.NONE) {

                    delay(1300)

                    if (selectedDirection.value != Direction.NONE) {
                        try {

                            if (bochonok.isNotEmpty()) {
                                bochonok
                                    .filter { it.value == 0 }
                                    .random().value = -1

                                var scoremini = 0
                                bochonok.forEach {
                                    scoremini += it.value
                                }
                                score.value = scoremini
                                if (bestScore.value < score.value) {
                                    bestScore.value = score.value
                                    sharedPreferences
                                        .edit()
                                        .putInt("bestscore", bestScore.value)
                                        .apply()
                                }
                                bochonki.value = scoremini / 3

                            }
                        } catch (_: Exception) {
                            Toast
                                .makeText(
                                    context,
                                    "Конец игры!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        block.value = false
                        selectedDirection.value = Direction.NONE

                    }
                }

            })

            when (selectedDirection.value) {
                Direction.NONE -> {}
                Direction.LEFT, Direction.RIGHT -> {
                }

                Direction.TOP, Direction.BOT -> try {
                    if (selectedDirection.value == Direction.TOP &&
                        bochonok[index - 4].value == it.value && (index) > 3
                        && it.value != 0 && bochonok[index + 4].value != it.value
                    ) {
                        weAlreadyDrag.value = false
                        canDelete.value = true


                    } else {
                        if (selectedDirection.value == Direction.BOT &&
                            bochonok[index + 4].value == it.value && (index) < 12
                            && it.value != 0 && bochonok[index - 4].value != it.value
                        ) {
                            weAlreadyDrag.value = false
                            canDelete.value = true
                        } else {
                        }

                    }


                } catch (e: Exception) {
                    Offset.Zero
                }
            }
            LaunchedEffect(key1 = it.value, block = {
                if(it.value == 128){
                    Toast.makeText(context,"YOU WIN!",Toast.LENGTH_SHORT).show()
                }
            })

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .offset(individualOffsetX.value.dp, individualOffsetY.value.dp)
                    .padding(10.dp)
                    .scale(scale.value)
                    .then(
                        if (it.value >0)
                            Modifier.surface(
                                CircleShape,
                                Color(0xFFE2C8A2),
                                BorderStroke(1.dp, Color(0xFFF3AC1C)),
                                4.dp
                            ) else Modifier
                    ), contentAlignment = Alignment.Center
            ) {

                if (it.value >0) {
                    Box(contentAlignment = Alignment.Center) {
                        Canvas(modifier = Modifier.size(90.dp), onDraw = {
                            drawCircle(Color(0xFFFF9838), radius = 90f)
                            drawCircle(Color.White, 78f)
                        })
                       Image(painter = painterResource(id = when(it.value){

                           2->R.drawable.p0001
                           4->R.drawable.p0002
                           8->R.drawable.p0004
                           16->R.drawable.p0003
                           32->R.drawable.p00005
                           64->R.drawable.p00007
                           else -> {R.drawable.p0001}
                       }), contentDescription = "",
                           modifier = Modifier.fillMaxSize(0.8f)
                           )
                    }

                }
                AnimatedVisibility(
                    visible = it.value == -1,
                    enter = fadeIn(tween(1100)),
                    exit = ExitTransition.None
                ) {
                    Canvas(modifier = Modifier.size(90.dp), onDraw = {
                        drawCircle(Color(0xFFFDA049), radius = 90f)
                        drawCircle(Color.White, 78f)
                    })
                    LaunchedEffect(key1 = Unit, block = {
                        it.value = 2
                    })
                }

            }


        }
    }

}
