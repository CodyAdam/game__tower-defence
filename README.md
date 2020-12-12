#### /!\ Pour la bonne compréhension du projet merci de lire entièrement ce fichier /!\

## TODO

-   [ ] image "explication_grille.gif"
-   [ ] commandes de "triche" en mode DEBUG

## Concept du projet :

Quand j'était petit j'ai beaucoup jouer au jeu tower defence appeller Bloons TD5 c'est pourquoi nous avons eu l'idée de faire une récréation simplifier de ce jeu avecles outils mis à notre disposition.

Tout les sprites sont tirée de ce même jeu, je vous renvoie donc vers la page Steam si vous voulez en savoir plus : https://store.steampowered.com/app/306020Bloons_TD_5/

## Concept du gameplay :

En tant que joueur, nous posons des singes sur le terrain dans le but de se défendre contre des vague de ballons appellé "Bloons". Les différents singes possèdes des capacités diverce et varier. La particularité de ce jeu c'est la mécanique des ballons, car quand un ballon éclate, il libère les ballons qu'il contient.

#### Hierarchie des ballons :

| Type de Bloons | Capacité                 | Spécialité                                      |
| -------------- | ------------------------ | ----------------------------------------------- |
| Ballon rouge   | Ne contient aucun ballon | 1pv et lent                                     |
| Ballon bleu    | Contient 1 ballon rouge  | 1pv et moyennement lent                         |
| Ballon vert    | Contient 1 ballon bleu   | 1pv et moyennement rapide                       |
| Ballon jaune   | Contient 1 ballon vert   | 1pv et rapide                                   |
| Ballon rose    | Contient 1 ballon jaune  | 1pv et très rapide                              |
| Ballon noir    | Contient 2 ballon rose   | 1pv et invincible au explosions                 |
| Ballon glacé   | Contient 2 ballon rose   | 1pv et invincible contre le froid               |
| Ballon d'acier | Contient 2 ballon noir   | 1pv et invincible contre les projectiles pointu |

![Image d'explication des Bloons](/explication_ballon.png)

## Changement effectuer sur le projet par defaut :

-   Les enemies ont été renommé en tant que "Bloon" et les tour en tant que "Singe"
-   Fenetre de résolution : 1240 x 720 px
-   Grille : 31 x 18 -> une tuile fait 40 x 40 px
-   Point de vie de départ : 200
-   Sprite des tours et enemies customiser
-   Arrière plan customiser (pas de tuile mais en une seul image)
-   Système de niveaux avec chacun des pathing, environnements et difficulté différentes
-   Système de grille évolué avec prévisualisation des emplacements disponible intéractive quand une tour souhaite être placé
    ![Image d'explication de la grille](/explication_grille.png)
-   Un mode débug avancé avec un affichage de multiple informations et accès à des commandes de "triche"
-   Ajout d'une interface visuel intéractive d'achat et d'amélioration sur le panel de droite

## Touches utiles en jeu :

-   [D] passe en mode DEBUG
