# Read Me First
Project made to test authentication providers

## Authorization-Providers
- **firebase**: README_firebase.md

___

### Open endpoints (no headers)
[ae](http://localhost:8080/ae)

### Secured endpoints

- **Headers**<br/>
`Authorization: Bearer <token>`<br/>
`Authorization-Provider: <example>`<br/>
___

- [Create user](): **POST** `/api/users`
  ```json
  {
    "username": "username"
  }

- [Find user](): **GET** `/api/users/{id}`


- [Create inventory](): **POST** `/api/users/{id}/inventory`
  ```json
  {
    "items" : [
        "hortelã",
        "banana"
    ]
  }

- [Find inventory](): **GET** `/api/users/{id}/inventory`
