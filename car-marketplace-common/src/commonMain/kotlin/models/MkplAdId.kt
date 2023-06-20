package ru.vohmin.marketplace.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class MkplAdId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = MkplAdId("")
    }
}