package trading.report

object Palette {

    const val blue = "#1976d2"
    const val orange = "#ff9800"
    const val green = "#009800"

    val colors = listOf(
        "#D62626",
        "#2BBB53",
        "#3CC4E1",
        "#990DBC",
        "#CA4795"
    )

    fun color(index: Int) = colors[index % colors.size]
}