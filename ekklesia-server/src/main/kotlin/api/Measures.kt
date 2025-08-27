package com.kcanoe.ekklesia.api

import com.kcanoe.ekklesia.models.IntroductionDate
import com.kcanoe.ekklesia.models.MeasureOrigin
import com.kcanoe.ekklesia.models.MeasureStatus
import com.kcanoe.ekklesia.models.MeasureSubject
import com.kcanoe.ekklesia.models.MeasureView
import com.kcanoe.ekklesia.models.Representative
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun measures(): List<MeasureView> {
    val measures = ArrayList<MeasureView>()

    measures.add(
        MeasureView(
            number = 2651,
            title = "Energy and Water Development and Related Agencies Appropriations Act",
            origin = MeasureOrigin.SENATE_BILL,
            sponsor = Representative("Sen. Scott, Tim"),
            cosponsors = ArrayList(),
            introducedAt = IntroductionDate(Instant.parse("2025-08-01T12:00:00Z")),
            status = MeasureStatus.INTRODUCED,
            source = "https://www.congress.gov/bill/119th-congress/senate-bill/2651",
            subjects = listOf(
                MeasureSubject.ENVIRONMENT,
                MeasureSubject.BUDGET,
                MeasureSubject.AGRICULTURE,
                MeasureSubject.ENERGY
            )
        )
    )

    measures.add(
        MeasureView(
            number = 4553,
            title = "ROAD to Housing Act",
            origin = MeasureOrigin.HOUSE_BILL,
            sponsor = Representative("Rep. Fleischmann, Charles"),
            cosponsors = ArrayList(),
            introducedAt = IntroductionDate(Instant.parse("2025-07-21T12:00:00Z")),
            status = MeasureStatus.INTRODUCED,
            source = "https://www.congress.gov/bill/119th-congress/house-bill/4553",
            subjects = listOf(
                MeasureSubject.HOUSING,
                MeasureSubject.TAXES
            )
        )
    )

    return measures
}
