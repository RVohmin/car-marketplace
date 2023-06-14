package ru.vohmin.marketplace.common.helpers

import ru.vohmin.marketplace.common.MkplContext
import ru.vohmin.marketplace.common.models.MkplCommand

fun MkplContext.isUpdatableCommand() =
    this.command in listOf(MkplCommand.CREATE, MkplCommand.UPDATE, MkplCommand.DELETE)
