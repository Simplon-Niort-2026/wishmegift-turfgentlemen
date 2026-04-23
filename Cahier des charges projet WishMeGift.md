## Email du client : première demande
- Un utilisateur connecté peut créer une liste de souhaits, qui aura un nom, un thème, une description et une date d'événement.
- Les thèmes seront les suivants : Liste de Noël, Liste de naissance, Liste d'anniversaire, Liste de mariage et Autre.
- L'utilisateur à l'origine de la liste de souhaits pourra ajouter des cadeaux à sa liste : chaque cadeau sera composé d'un nom, d'une description, d'un lien, d'un niveau d'envie (entre 1 et 5) et d'un prix approximatif.
- L'utilisateur à l'origine de sa liste de souhaits pourra aussi supprimer des cadeaux de sa liste.
- Un utilisateur pourra créer un compte avec les données suivantes : son email (unique) et son mot de passe. Le mot de passe devra être haché, avec au minimum 12 caractères comprenant des majuscules, des minuscules, des chiffres et des caractères spéciaux.
- Un utilisateur connecté pourra partager une liste de souhaits avec d'autres utilisateurs, à partir de leurs adresses email.
- Un utilisateur connecté pourra voir les listes de souhaits qui lui ont été partagées.
- Un utilisateur connecté pourra réserver un cadeau d'une liste à laquelle il a accès (et dont il n'est pas l'auteur).
## Premier rdv
Le client a besoin d'une application uniquement en backend (la partie frontend sera réalisée par quelqu'un d'autre).
L'application devra permettre à un utilisateur (connecté) de créer une liste de cadeaux pour un évènement particulier (mariage, anniversaire, noël, naissance, autre) . 
Les évènements seront choisis dans une liste déroulante par exemple (donc non modifiables), avec un évènement autre qui pourra être précisé dans la partie description.
L'auteur de la liste peut ajouter des cadeaux et renvoyer vers des liens pour illustrer sont propos. Ces liens ne sont pas forcément des liens d'achats.
Il peut ajouter un prix approximatif et un niveau d'envie qui ira de 1 à 5.
L'auteur peut partager par email sa liste d'achat avec d'autres personnes.
Les personnes ayant accès à la liste de cadeaux peuvent réserver un cadeau. Ce dernier ne sera plus réservable par d'autre personne.

