package trading.report

object Palette {

    const val blue = "#1976d2"
    const val orange = "#ff9800"
    const val green = "#009800"

    val colors = listOf(
        blue,
        orange,
        green
    )

    fun color(index: Int) = colors[index % colors.size]
}