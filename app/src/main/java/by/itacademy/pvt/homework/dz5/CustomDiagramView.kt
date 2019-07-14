package by.itacademy.pvt.homework.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import by.itacademy.pvt.homework.R

class CustomDiagramView : View {

    private var diagramCenterX = 0f
    private var diagramCenterY = 0f
    private var diagramRadius = 0f
    private var diagramMargin = 0f
    private var diagramData = arrayListOf<Item>()
    private var colorsList = listOf<Int>(
        R.color.color1,
        R.color.color2,
        R.color.color3,
        R.color.color4,
        R.color.color5,
        R.color.color6,
        R.color.color7,
        R.color.color8,
        R.color.color9,
        R.color.color10,
        R.color.color11,
        R.color.color12
    )

    private var rectF: RectF = RectF()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    init {
        diagramMargin = resources.getDimension(R.dimen.diagram_margin)

        var item0: Item
        item0 = Item(1, "1")
        diagramData.add(item0)

        textPaint.style = Paint.Style.FILL
        textPaint.textSize = resources.getDimension(R.dimen.diagram_text_size)
        textPaint.color = resources.getColor(R.color.black)
    }

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheight: Int) {
        super.onSizeChanged(Math.min(width, height), Math.min(width, height), oldwidth, oldheight)

        diagramCenterX = Math.min(width, height) / 2f
        diagramCenterY = Math.min(width, height) / 2f
        diagramRadius = Math.min(width, height) / 2 - diagramMargin
        rectF.set(
            diagramMargin,
            diagramMargin,
            Math.min(width, height).toFloat() - diagramMargin,
            Math.min(width, height).toFloat() - diagramMargin
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        drawDiagram(canvas)

        invalidate()
    }

    private fun drawDiagram(canvas: Canvas) {
        // отрисовка диаграммы
        val nItems = diagramData.size
        var sumItems: Int = 0
        var tempAngle = 0f
        var textAngle = tempAngle

        // считаем сумму данных всех элементов списка
        for (i in 0..nItems - 1) {
            sumItems = sumItems + diagramData.get(i).weight
        }

        // отрисовка секторов
        for (i in 0..nItems - 1) {
            drawSectors(
                canvas,
                tempAngle.toFloat(),
                ((diagramData.get(i).weight.toFloat() / sumItems).toFloat() * 360f).toFloat(),
                getMyColor(i)
            )

            // определяем координаты подписей
            textAngle = ((diagramData.get(i).weight.toFloat() / sumItems).toFloat() * 180f)
            var textX =
                diagramCenterX + 1.2f * diagramRadius * Math.cos(Math.toRadians(textAngle.toDouble() + tempAngle.toDouble())).toFloat()
            var textY =
                diagramCenterY + 1.2f * diagramRadius * Math.sin(Math.toRadians(textAngle.toDouble() + tempAngle.toDouble())).toFloat()
            // рисуем текст
            canvas.drawText(
                diagramData.get(i).data,
                textX, textY,
                textPaint
            )

            tempAngle = tempAngle.toFloat() + ((diagramData.get(i).weight.toFloat() / sumItems.toFloat()) * 360f)
        }
    }

    private fun drawSectors(canvas: Canvas, startAngle: Float, angle: Float, colorOfSector: Int) {
        // отрисовка конкретного сектора заданного цвета
        val diagramPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        diagramPaint.style = Paint.Style.FILL
        diagramPaint.color = resources.getColor(colorOfSector)
        diagramPaint.flags = Paint.DITHER_FLAG

        canvas.drawArc(rectF, startAngle, angle, true, diagramPaint)
    }

    private fun getMyColor(i: Int): Int {
        // возвращает цвет из массива
        var tmp = 0
        tmp = colorsList.get(i)
        return tmp
    }

    public fun setList(list: ArrayList<Item>) {
        diagramData = list
        invalidate()
    }
}