# Application de gestion des emplois du temps

## Prérequis

- **Type de l’application :** application web dynamique
- **Description de l’objectif :** réalisation d’une application web pour création et génération des emplois de temps.
- **Ciblage :** Les étudiants et les professeurs d’ENSIAS
- **Contraintes :** Seul l'admin qui peut créer ou générer les emplois du temps. Un étudiant ne peut consulter que les emplois du temps des classes. Un prof a le droit de voir ses emplois du temps mais ne peut pas les modifier.

## Création

### Adjectifs sur la forme et l’apparence :
Une interface d’utilisation simple,  facile à manipuler pour la création des emplois de temps personnalisés, se compose des cartes contenant le titre du cours, la salle, le nom du professeur et la class concernée par ce cours. L’utilisateur qui va créer un emploi du temps spécifique doit glisser et déposer ces cartes dans une table dont les colonnes représentent les périodes (par exemple p1 indique la période de 8h jusqu’à 10h), et la 1ere colonne contient les jours de la semaine.

## Fonctionnalités et Technique 

### Arborescence :
![arborescence](https://user-images.githubusercontent.com/31375294/110205641-7ab8b500-7e79-11eb-83cb-7ad4b76a41e1.png)

### Fonctionnalités :

Les fonctionnalités qui doivent être offertes par l’application sont :
- Afficher les emplois du temps des différentes classes sans passer par l’authentification
- Pour visualiser ses emplois du temps, un professeur doit avoir un compte pour accéder à son espace.
- Un administrateur doit s’authentifier pour visionner ses emplois de temps.
- Un administrateur peut créer, modifier, supprimer ou générer un emploi du temps (Timetables page) ou bien une session dans un emploi existant.
- Un administrateur peut créer, modifier, supprimer une classe.
- Un administrateur peut créer, modifier, supprimer une salle.
- Un administrateur peut créer, modifier, supprimer un professeur.
- Un administrateur peut créer, modifier, supprimer une filière.
- Un administrateur peut créer, modifier, supprimer un cours. 

### Technologie : 
JEE
