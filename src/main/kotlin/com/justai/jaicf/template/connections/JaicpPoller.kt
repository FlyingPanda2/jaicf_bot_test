package com.justai.jaicf.template.connections

import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.template.helloWorldBot

fun main() {
    TelegramChannel(helloWorldBot, "6980623804:AAG_yoZ_vluqRpZ39-TYQfkoQjkp06A8xPA").run()
}