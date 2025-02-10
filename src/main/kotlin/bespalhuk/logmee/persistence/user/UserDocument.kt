package bespalhuk.logmee.persistence.user

import org.bson.types.ObjectId
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("users")
data class UserDocument(

    @Id
    val id: String? = ObjectId.get().toString(),

    @CreatedDate
    @Field("created_date")
    val createdDate: java.time.Instant? = null,

    @LastModifiedDate
    @Field("last_modified")
    val lastModified: java.time.Instant? = null,

    @Field("idAuth")
    @Indexed(unique = true)
    @NotNull
    val idAuth: String,

    @Field("username")
    @Indexed(unique = true)
    @NotNull
    val username: String,
)
