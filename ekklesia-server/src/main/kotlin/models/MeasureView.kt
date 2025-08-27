package com.kcanoe.ekklesia.models

import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
Key to Legislative Citations

S.	Bill originating in the Senate	S. 1
H.R.	Bill originating in the House of Representatives	H.R. 2002
S.Res.	Simple resolution originating in the Senate	S.Res. 2
H.Res.	Simple resolution originating in the House of Representatives	H.Res. 3
S.J.Res.	Joint resolution originating in the Senate	S.J. Res.4
H.J.Res.	Joint resolution originating in the House of Representatives	H.J. Res.5
S.Con.Res.	Concurrent resolution originating in the Senate	S.Con.Res.6
H.Con.Res.	Concurrent resolution originating in the House of Representatives	H.Con.Res.7
P.L.	A bill or joint resolution, originating in either the Senate or House of Representatives, may become a public law	P.L.101-336
Stat.	United States Statutes at Large is a chronological listing of the laws enacted each Congress. They are published in volumes numbered by Congress.	80 Stat.371
U.S. Code	United States Code is the codification by subject matter of the general and permanent laws of the United States.	5 U.S.C.552
S.Rpt.	Report issued by a Senate committee, usually to accompany legislation that has passed in committee.	S.Rpt.97-10
H.Rpt.	Report issued by a House of Representatives or conference committee, usually to accompany legislation that has passed in committee.	H.Rpt.99-22
*/

@Suppress("unused")
@Serializable(with = MeasureOrigin.MeasureOriginSerializer::class)
enum class MeasureOrigin(val abbreviation: String) {
    SENATE_BILL("S."),
    HOUSE_BILL("H.R."),
    SENATE_RESOLUTION("S.Res."),
    HOUSE_RESOLUTION("H.Res."),
    SENATE_JOINT_RESOLUTION("S.J.Res."),
    HOUSE_JOINT_RESOLUTION("H.J.Res."),
    SENATE_CONCURRENT_RESOLUTION("S.Con.Res."),
    HOUSE_CONCURRENT_RESOLUTION("H.Con.Res."),
    PUBLIC_LAW("P.L."),
    STATUTES_AT_LARGE("Stat."),
    UNITED_STATES_CODE("U.S. Code"),
    SENATE_REPORT("S.Rpt."),
    HOUSE_REPORT("H.Rpt.");

    companion object MeasureOriginSerializer : KSerializer<MeasureOrigin> {
        private val originsMap = MeasureOrigin.entries.associateBy(MeasureOrigin::abbreviation)

        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("MeasureOrigin", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: MeasureOrigin) {
            encoder.encodeString(value.abbreviation)
        }

        override fun deserialize(decoder: Decoder): MeasureOrigin {
            val status = decoder.decodeString()
            return originsMap[status] ?: HOUSE_BILL
        }
    }
}

@Suppress("unused")
@Serializable(with = MeasureSubject.MeasureSubjectSerializer::class)
enum class MeasureSubject(val subject: String) {
    TAXES("Taxes"),
    HEALTH("Health"),
    CRIME("Crime"),
    LABOR("Labor"),
    ENERGY("Energy"),
    TRADE("Trade"),
    CYBER("Cyber"),
    EDUCATION("Education"),
    HOUSING("Housing"),
    DEFENSE("Defense"),
    BUDGET("Budget"),
    ENVIRONMENT("Environment"),
    VETERANS("Veterans"),
    AGRICULTURE("Agriculture"),
    JUDICIARY("Judiciary");

    companion object MeasureSubjectSerializer : KSerializer<MeasureSubject> {
        private val subjectsMap = MeasureSubject.entries.associateBy(MeasureSubject::subject)

        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("MeasureSubject", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: MeasureSubject) {
            encoder.encodeString(value.subject)
        }

        override fun deserialize(decoder: Decoder): MeasureSubject {
            val status = decoder.decodeString()
            return subjectsMap[status] ?: BUDGET
        }
    }
}

@Suppress("unused")
@Serializable(with = MeasureStatus.MeasureStatusSerializer::class)
enum class MeasureStatus(val status: String) {
    INTRODUCED("Introduced"),
    REFERRED_TO_COMMITTEE("Referred to Committee"),
    REPORTED_BY_COMMITTEE("Reported by Committee"),
    PASSED_CHAMBER("Passed Chamber"),
    VETOED("Vetoed"),
    BECAME_LAW("Became Law"),
    DIED_IN_COMMITTEE("Died in Committee");

    companion object MeasureStatusSerializer : KSerializer<MeasureStatus> {
        private val statusesMap = entries.associateBy(MeasureStatus::status)

        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("MeasureStatus", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: MeasureStatus) {
            encoder.encodeString(value.status)
        }

        override fun deserialize(decoder: Decoder): MeasureStatus {
            val status = decoder.decodeString()
            return statusesMap[status] ?: INTRODUCED
        }
    }
}

@JvmInline
@Serializable(with = IntroductionDate.IntroductionDateSerializer::class)
value class IntroductionDate(val value: String) {
    @OptIn(ExperimentalTime::class)
    constructor(date: Instant) : this (
        date.toString()
    )
    object IntroductionDateSerializer : KSerializer<IntroductionDate> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor("IntroductionDate", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: IntroductionDate) {
            encoder.encodeString(value.value)
        }

        @OptIn(ExperimentalTime::class)
        override fun deserialize(decoder: Decoder): IntroductionDate {
            val instant = Instant.parse(decoder.decodeString())
            return IntroductionDate(instant)
        }
    }
}

@Serializable
data class Representative(val name: String)

@Serializable
@ExperimentalTime
data class MeasureView(
    val number: Int,
    val title: String,
    val origin: MeasureOrigin,
    val sponsor: Representative,
    val cosponsors: List<Representative>,
    val introducedAt: IntroductionDate,
    val status: MeasureStatus,
    val source: String,
    val subjects: List<MeasureSubject>
)
