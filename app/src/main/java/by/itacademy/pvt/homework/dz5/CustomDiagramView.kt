package by.itacademy.pvt.homework.dz5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
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

    private val diagramPaint = Paint(Paint.ANTI_ALIAS_FLAG)

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
        var item0 = Item(1, "1")
        diagramData.add(item0)
        System.out.println("item : weight = " + diagramData.get(0).weight + " data = " + diagramData.get(0).weight)
        item0 = Item(2, "2")
        diagramData.add(item0)
        System.out.println("item : weight = " + diagramData.get(1).weight + " data = " + diagramData.get(1).weight)
        item0 = Item(3, "3")
        diagramData.add(item0)
        diagramPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        diagramPaint.style = Paint.Style.STROKE
        diagramPaint.strokeWidth = resources.getDimension(R.dimen.watch_width)
        diagramMargin = resources.getDimension(R.dimen.diagram_margin)

        resetColor()
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
        resetColor()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        drawDiagram(canvas)

        invalidate()
    }

    private fun drawDiagram(canvas: Canvas) {

        val nItems = diagramData.size
        var sumItems: Int = 0
        var tempWeight = 0f

        for (i in 0..nItems - 1) {
            sumItems = sumItems + diagramData.get(i).weight
            System.out.println("sumitems =" + sumItems)
        }

        for (i in 0..nItems - 1) {
            System.out.println("draw sector" + i + "!")
            drawSectors(
                canvas,
                tempWeight.toFloat(),
                ((diagramData.get(i).weight.toFloat() / sumItems).toFloat() * 360).toFloat() + tempWeight.toFloat(),
                getMyColor(i)
            )
            tempWeight = tempWeight.toFloat() + ((diagramData.get(i).weight.toFloat() / sumItems.toFloat()) * 360f)
            System.out.println(diagramData)
        }
    }

    private fun drawSectors(canvas: Canvas, startAngle: Float, finishAngle: Float, colorOfSector: Int) {

        diagramPaint.style = Paint.Style.FILL
        diagramPaint.color = resources.getColor(colorOfSector)

        canvas.drawArc(rectF, startAngle, finishAngle, true, diagramPaint)
        System.out.println("startAngle = " + startAngle + " finishAngle = " + finishAngle)
    }

    private fun getMyColor(i: Int): Int {
        var tmp = 0
        tmp = colorsList.get(i)
        System.out.println("tmp color = " + tmp)
        return tmp
    }

    private fun resetColor() {
        colorsList = listOf<Int>(
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
    }
}