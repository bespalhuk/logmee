package bespalhuk.logmee.persistence.user

import org.bson.types.ObjectId
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("users")
data class UserDocument(

    @Id
    val id: String? = ObjectId.get().toString(),

    @Field("idAuth")
    @Indexed(unique = true)
    @NotNull
    val idAuth: String,

    @Field("username")
    @Indexed(unique = true)
    @NotNull
    val username: String,
)
