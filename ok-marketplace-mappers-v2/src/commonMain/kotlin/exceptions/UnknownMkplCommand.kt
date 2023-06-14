package ru.vohmin.marketplace.mappers.v2.exceptions

import ru.vohmin.marketplace.common.models.MkplCommand

class UnknownMkplCommand(command: MkplCommand) : Throwable("Wrong command $command at mapping toTransport stage")
