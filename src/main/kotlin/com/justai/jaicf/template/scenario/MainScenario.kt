package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.context.manager.mongo.MongoBotContextManager
import com.justai.jaicf.template.helloWorldBot
import com.mongodb.client.MongoClients

val HelloWorldScenario = Scenario {

    val client = MongoClients.create("mongodb://localhost:27017")
    val userCollection = client.getDatabase("database_for_tz").getCollection("users")

    state("main") {
        activators {
            regex("/start")
        }

        action {
            val message = request.telegram?.message
            reactions.telegram?.say("Здравствуйте ${message?.chat?.firstName}", listOf("Mini-app", "Заполнить анкету"))
        }
    }
    state("mini-app"){
        activators {
            regex("Mini-app")
        }

        action {
            reactions.telegram?.say("Запускаю...")
            val users = userCollection.find()
            for(user in users)
                reactions.telegram?.say(user.toJson())



        }
    }

    state("admin-panel") {
        activators {
            regex("/form")
        }

        action {
//            userCollection.find({role: "admin"})
//
//            if()
//                reactions.telegram?.say("Вы админ")
//            else
//                reactions.telegram?.say("${message?.chat?.username}")
        }
    }
}