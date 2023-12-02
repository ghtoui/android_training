package jp.co.yumemi.droidtraining.model

object City {
    private val index = 0
    private fun getCityIds(): List<Int> {
        return listOf(
            2128295,
            2129376,
            2111149,
            1855431,
            1850144,
            1856057,
            1860243,
            1853909,
            1862415,
            1859146,
            1863967,
            1860827,
            1856035,
            5128581,
            2643743
        )
    }

    fun getCityId(): Int {
        return getCityIds().random()
    }
}
