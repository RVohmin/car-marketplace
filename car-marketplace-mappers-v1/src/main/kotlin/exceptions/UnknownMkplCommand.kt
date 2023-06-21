package ru.vohmin.marketplace.mappers.v1.exceptions

import ru.vohmin.marketplace.common.models.MkplCommand


class UnknownMkplCommand(command: MkplCommand) : Throwable("Wrong command $command at mapping toTransport stage")
