package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.template.scenario.HelloWorldScenario

val helloWorldBot = BotEngine(
    scenario = HelloWorldScenario,
    activators = arrayOf(
        RegexActivator,
        CatchAllActivator
    )
)
