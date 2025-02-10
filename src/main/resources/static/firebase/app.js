import {initializeApp} from 'https://www.gstatic.com/firebasejs/11.3.0/firebase-app.js';
import {
    createUserWithEmailAndPassword,
    getAuth,
    GoogleAuthProvider,
    signInWithEmailAndPassword,
    signInWithPopup
} from 'https://www.gstatic.com/firebasejs/11.3.0/firebase-auth.js';

const firebaseConfig = {
    apiKey: "",
    authDomain: "",
    projectId: "",
    storageBucket: "",
    messagingSenderId: "",
    appId: ""
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

document.getElementById('signUp').addEventListener('click', () => {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    createUserWithEmailAndPassword(auth, email, password)
        .then(userCredential => {
            console.log('User signed up: ', userCredential.user);
            displayToken();
        })
        .catch(error => {
            console.error('Error signing up: ', error.message);
        });
});

document.getElementById('signIn').addEventListener('click', () => {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    signInWithEmailAndPassword(auth, email, password)
        .then(userCredential => {
            console.log('User signed in: ', userCredential.user);
            displayToken();
        })
        .catch(error => {
            console.error('Error signing in: ', error.message);
        });
});

document.getElementById('signInGoogle').addEventListener('click', () => {
    const provider = new GoogleAuthProvider();

    signInWithPopup(auth, provider)
        .then(result => {
            console.log('Google Sign in successful: ', result.user);
            displayToken();
        })
        .catch(error => {
            console.error('Error with Google Sign in: ', error.message);
        });
});

function displayToken() {
    const user = auth.currentUser;
    if (user) {
        user.getIdToken(true)
            .then(idToken => {
                document.getElementById('tokenDisplay').value = idToken;
                console.log('ID Token: ', idToken);
            })
            .catch(error => {
                console.error('Error fetching token: ', error.message);
            });
    } else {
        console.log('No user is signed in.');
    }
}
