package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.template.classes.Colors
import com.justai.jaicf.template.classes.botUser
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Filters
import org.bson.Document
import java.awt.Color

val HelloWorldScenario = Scenario {

    val client = MongoClients.create("mongodb://localhost:27017")
    val userCollection = client.getDatabase("database_for_tz").getCollection("users")
    val dataColeection = client.getDatabase("database_for_tz").getCollection("form")

    state("main") {
        activators {
            regex("/start")
        }

        action {
            val message = request.telegram?.message

            val user = botUser(context.clientId, message?.chat?.firstName, message?.chat?.username, "user")

            reactions.telegram?.say("Здравствуйте ${user.firstName}", listOf("Mini-app", "Заполнить анкету"))
            val document = Document("name", user.firstName).append("username", user.userName).append("role", user.role).append("_id", context.clientId)
            userCollection.insertOne(document)
        }
    }
    state("mini-app"){
        activators {
            regex("Mini-app")
        }

        action {
            reactions.telegram?.say("Запускаю...")
        }
    }
    state("admin-panel") {
        activators {
            regex("/form")
        }

        action {
            val query = Filters.and(listOf(
                Filters.eq("_id", context.clientId),
                Filters.eq("role", "admin")))
            val user = userCollection.find(query).limit(1)
            if(user.count() != 0){
                reactions.telegram?.say("Вы админ!")
                val allUsers = userCollection.find()
                for(user in allUsers)
                    reactions.telegram?.say(user.toJson())
            }
            else{
                reactions.telegram?.say("Вы не админ")
            }
        }
    }
    state("Анкета"){
        activators {
            regex("Заполнить анкету")
        }

        action {
            reactions.telegram?.say("Ответьте на 3 вопроса")
        }
        state("test"){
            activators {
                catchAll()
            }

            action {
                reactions.say("I have nothing to say yet...")
                reactions.sayRandom()
            }
        }
    }
//            reactions.telegram?.say("Ваш город?")
//            val country_message = request
//            val country = country_message
//            reactions.telegram?.say("Ваш год рождения?")
//            val date_message = request.telegram?.message
//            val date = country_message
//            reactions.telegram?.say("Ваш цвет?")
//            reactions.buttons(Colors.Blue.toString(), Colors.Red.toString())
//            val color_message = request.telegram?.message
//            val color = color_message


}