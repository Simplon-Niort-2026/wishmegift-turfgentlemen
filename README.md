# WishMeGift

API REST avec Spring Boot et PostgreSQL permettant de gérer des listes de souhaits pour des évènements et de les partager à d'autres utilisateurs

## Fonctionnalités
- Un utilisateur connecté pourra créer et gérer des listes avec des thèmes et des dates d'évènements
- Partager la liste à d'autres utilisateurs qui seront des invités
- Ajouter et supprimer des cadeaux à cette liste
- Système de réservation de cadeaux pour les invités

## Stack Technique
- Java 21
- Spring Boot
- PostgreSQL
- Maven

## Modèle de données

| Entité   | Description                                                |
|----------|------------------------------------------------------------|
| User     | Créer un compte avec un email et un mot de passe           |
| WishList | Une liste de souhait avec un propriétaire                  |
| Gift     | Un cadeau avec un prix, un niveau d'envie et une référence |

## Comment démarrer l'application

### Cloner le projet 

```bash
git clone https://github.com/Simplon-Niort-2026/wishmegift-turfgentlemen.git
```

### Pré-recquis
- Java 21
- PostgreSQL
- Maven

### Configuration
Créer une base de données appelée wishmegift dans PostgreSQL et mettre à jour le fichier src/main/resources/application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/wishmegift
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create
```

### Lancement

```bash
mvn spring-boot:run
```

## Structure du projet

```
WishMeGift/
├── src/
│   ├── main/
│   │   ├── java/co/simplon/wishmegift/
│   │   │   ├── config/                      # Sécurité et configuration de l'application
│   │   │   │   └── AppConfig.java
│   │   │   ├── controller/                  # REST controllers 
│   │   │   │   ├── GiftController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── WishListController.java
│   │   │   ├── entity/                      # Entités JPA 
│   │   │   │   ├── Gift.java
│   │   │   │   ├── User.java
│   │   │   │   └── WishList.java
│   │   │   ├── enums/                       # Enumerations (Level, Theme)
│   │   │   │   ├── Level.java
│   │   │   │   └── Theme.java
│   │   │   ├── repository/                  # Spring Data JPA repositories
│   │   │   │   ├── GiftRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── WishListRepository.java
│   │   │   ├── service/                     # Logique métier
│   │   │   │   ├── GiftService.java
│   │   │   │   ├── UserService.java
│   │   │   │   └── WishListService.java
│   │   │   └── WishmegiftApplication.java   # Application
│   │   └── resources/
│   │       └── application.properties       # Configuration bdd et application
│   └── test/                               
├── Cahier des charges projet WishMeGift.md  
├── Documentation.md                         # MCD / MLD
├── mvnw
├── mvnw.cmd
├── pom.xml                                  # Dépendances Maven 
└── README.md
```

## Liste des Endpoints
### Users
| Method | Endpoint       | Description         |
|--------|----------------|---------------------|
| GET    | /user          | Get all users       |
| GET    | /user/{id}     | Get user by ID      |
| POST   | /user/register | Register a new user |
| PUT    | /user/{id}     | Update a user       |
| DELETE | /user/{id}     | Delete a user       |
 
### WishLists
| Method | Endpoint                                          | Description              |
|--------|---------------------------------------------------|--------------------------|
| GET    | /wishlist                                         | Get all wishlists        |
| GET    | /wishlist/{id}                                    | Get wishlist by ID       |
| GET    | /wishlist/guest/{guestId}                         | Get wishlists for a guest|
| POST   | /wishlist/{ownerId}                               | Create a wishlist        |
| PATCH  | /wishlist/share/{wishListId}/{guestId}            | Add guest to wishlist    |
| PATCH  | /wishlist/{wishListId}/owner/{ownerId}/addgift/{giftId}    | Add gift to wishlist     |
| PATCH  | /wishlist/{wishListId}/owner/{ownerId}/removegift/{giftId} | Remove gift from wishlist|
 
### Gifts
| Method | Endpoint                                          | Description       |
|--------|---------------------------------------------------|-------------------|
| GET    | /gift                                             | Get all gifts     |
| GET    | /gift/{id}                                        | Get gift by ID    |
| POST   | /gift                                             | Create a gift     |
| PATCH  | /gift/{giftId}/wishlist/{wishlistId}/guest/{guestId} | Reserve a gift |
| DELETE | /gift/{id}                                        | Delete a gift     |


## Auteurs
TurfGentlemen
