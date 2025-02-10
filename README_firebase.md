# Read Me First
- Keep your Firebase credentials JSON secure.
- The **secrets** folder is for testing purposes only.

# Getting Started
- Docker folder: **docker-compose up -d**
- Enable **Firebase Sign-in methods**: **Email/Password** and **Google**
- Run: **./gradlew bootRun --args='--spring.profiles.active=local'**
- Access [firebase login page](http://localhost:8080/firebase/index.html)

## Generating Firebase Credentials (Admin SDK)
Used on the server-side to verify **Firebase tokens**.

### Steps
1. Go to **Firebase console** -> select your project
2. Go to **Project settings** -> **Service accounts tab**
3. **Generate new private key**
4. Rename the generated .json file to **firebase-adminsdk.json**
5. Place the **firebase-adminsdk.json** file inside the **./secrets** folder


   ```kotlin
class FirebaseConfig {
    ...
        val inputStream = FileInputStream("./secrets/firebase-adminsdk.json")
    ...
}
   ```

---

## 2. Registering an App to Get Firebase Config
Used on the client-side to initialize **Firebase integration**.

### Steps:
1. Go to **Firebase console** -> select your project
2. Go to **Project settings** -> **General tab**
3. Scroll down to **Your apps** -> **`</>` Web App**
4. Register the app -> Copy the **Firebase Config**
5. Paste the **Firebase Config** inside the **app.js** file

```javascript
const firebaseConfig = {
    apiKey: "...",
    authDomain: "...",
    projectId: "...",
    storageBucket: "...",
    messagingSenderId: "...",
    appId: "..."
};
...
```
