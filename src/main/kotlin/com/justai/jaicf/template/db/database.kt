//package com.justai.jaicf.template.db
//
//import com.mongodb.client.MongoClients
//import com.mongodb.client.MongoDatabase
//import org.bson.codecs.pojo.annotations.BsonId
//import org.bson.codecs.pojo.annotations.BsonProperty
//import org.bson.types.ObjectId
//
//class database {
//
//}
//fun getDatabase(): MongoDatabase{
//    val client = MongoClients.create("mongodb://localhost:27017")
//    return client.getDatabase("database_for_tz")
//}
//
//data class userInfo(
//    @BsonId
//    val id: ObjectId,
//    var firstName: String?,
//    val userName: String?,
//    var role: String?,
//    @BsonProperty("userId")
//    val userId: String?
//)
//
//fun addUser(database: MongoDatabase){
//    val info = userInfo(
//        id = ObjectId(),
//        firstName = null,
//        userName = null,
//        role = null,
//        userId = null
//    )
//
//    val collection = database.getCollection("users")
//    collection.insertOne(userInfo).also{
//
//    }
//}
