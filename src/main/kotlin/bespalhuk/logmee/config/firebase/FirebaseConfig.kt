package bespalhuk.logmee.config.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream

@Configuration
class FirebaseConfig {

    init {
        if (FirebaseApp.getApps().isEmpty()) {
            val inputStream = FileInputStream("./secrets/firebase-adminsdk.json")
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()
            FirebaseApp.initializeApp(options)
        }
    }
}
