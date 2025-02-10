package bespalhuk.logmee.persistence.inventory

import org.bson.types.ObjectId
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("inventory")
data class InventoryDocument(

    @Id
    val id: String? = ObjectId.get().toString(),

    @Field("idUser")
    @Indexed(unique = true)
    @NotNull
    val idUser: String,

    @Field("items")
    val items: MutableList<String> = mutableListOf(),
)
