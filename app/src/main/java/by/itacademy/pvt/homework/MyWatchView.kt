package by.itacademy.pvt.homework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.icu.util.Calendar
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View

class MyWatchView : View {
    private val watchPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var watchCenterX = 0f
    private var watchCenterY = 0f
    private var watchRadius = 0f
    private var watchMargin = 0f

    private val lineWatchPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var lineWatchWidth = 0f
    private var lineWatchLength = 0f

    private val rect = Rect()
    private val numbersWatchPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val numbersArray = intArrayOf(3, 6, 9, 12)
    private var numbersWatchMargin = 0f

    private val hourArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val minuteArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val secondArrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)

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
        watchPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        watchPaint.style = Paint.Style.STROKE
        watchPaint.strokeWidth = resources.getDimension(R.dimen.watch_width)
        watchMargin = resources.getDimension(R.dimen.watch_margin)

        lineWatchPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        lineWatchPaint.style = Paint.Style.STROKE
        lineWatchWidth = resources.getDimension(R.dimen.line_watch_width)

        numbersWatchPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        numbersWatchPaint.style = Paint.Style.FILL
        numbersWatchPaint.textSize = resources.getDimension(R.dimen.numbers_watch_size)
        numbersWatchMargin = resources.getDimension(R.dimen.numbers_watch_margin)

        secondArrowPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        secondArrowPaint.style = Paint.Style.STROKE
        secondArrowPaint.strokeWidth = resources.getDimension(R.dimen.second_arrow_width)

        minuteArrowPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        minuteArrowPaint.style = Paint.Style.STROKE
        minuteArrowPaint.strokeWidth = resources.getDimension(R.dimen.minute_arrow_width)

        hourArrowPaint.color = ContextCompat.getColor(context, R.color.watchColor)
        hourArrowPaint.style = Paint.Style.STROKE
        hourArrowPaint.strokeWidth = resources.getDimension(R.dimen.hour_arrow_width)
    }

    private fun numberWatchDraw(canvas: Canvas) {

        val radius = watchRadius + watchMargin - numbersWatchMargin

        for (number in numbersArray) {

            val text = number.toString()
            numbersWatchPaint.getTextBounds(text, 0, text.length, rect)
            val angle = Math.PI / 2 * (number + 1)
            val x = (watchCenterX + Math.cos(angle) * radius - rect.width() / 2)
            val y = (watchCenterY - Math.sin(angle) * radius + rect.height() / 2)
            canvas.drawText(text, x.toFloat(), y.toFloat(), numbersWatchPaint)
        }
    }

    private fun linesWatchDraw(canvas: Canvas) {
        lineWatchPaint.strokeWidth = lineWatchWidth
        val startMarkingLine = (watchCenterY - watchRadius) - lineWatchLength / 2
        val endMarkingLine = (watchCenterY - watchRadius) + lineWatchLength / 2
        canvas.save()
        for (i in 0..11) {
            canvas.drawLine(watchCenterX, startMarkingLine, watchCenterX, endMarkingLine, lineWatchPaint)
            canvas.rotate(30f, watchCenterX, watchCenterY)
        }
        canvas.restore()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun arrowWatchDraw(canvas: Canvas) {
        val calendar = Calendar.getInstance()
        var hour = calendar.get(Calendar.HOUR)
        drawArrow(canvas, hourArrowPaint, ((hour + calendar.get(Calendar.MINUTE) / 60.0) * 5), watchRadius * 0.5f)
        drawArrow(canvas, minuteArrowPaint, calendar.get(Calendar.MINUTE).toDouble(), watchRadius * 0.8f)
        drawArrow(canvas, secondArrowPaint, calendar.get(Calendar.SECOND).toDouble(), watchRadius * 0.8f)
    }

    private fun drawArrow(canvas: Canvas, paint: Paint, value: Double, handLength: Float) {
        val angle = Math.PI * value / 30 - Math.PI / 2
        canvas.drawLine(
            watchCenterX,
            watchCenterY,
            (watchCenterX + Math.cos(angle) * handLength).toFloat(),
            (watchCenterY + Math.sin(angle) * handLength).toFloat(),
            paint
        )
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        lineWatchLength = Math.min(width, height) / 20f
        watchCenterX = width / 2f
        watchCenterY = height / 2f
        watchRadius = Math.min(watchCenterX, watchCenterY) - watchMargin
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawCircle(watchCenterX, watchCenterY, watchRadius, watchPaint)
        numberWatchDraw(canvas)
        linesWatchDraw(canvas)
        arrowWatchDraw(canvas)

        invalidate()
    }
}