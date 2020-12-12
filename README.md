#### /!\ Pour la bonne compréhension du projet merci de lire entièrement ce fichier

## TODO

-   [ ] image "explication_grille.gif"

## Concept du projet :

Quand j'était petit j'ai beaucoup jouer au jeu tower defence appeller Bloons TD5 c'est pourquoi nous avons eu l'idée de faire une récréation simplifier de ce jeu avecles outils mis à notre disposition.

Tout les sprites sont tirée de ce même jeu, je vous renvoie donc vers la page Steam si vous voulez en savoir plus : https://store.steampowered.com/app/306020Bloons_TD_5/

## Concept du gameplay :

En tant que joueur, nous posons des singes sur le terrain dans le but de se défendre contre des vague de ballons. Les différents singes possèdes des capacités diverce et varier. L'originalité de ce jeu, c'est les ballon car quand un ballon éclate, il fait apparaitre les ballons qu'il contient

#### Hierarchie des ballons :

-   1 ballon rouge -> ne contient aucun ballon (1pv et lent)
-   1 ballon bleu -> contient 1 ballon rouge (1pv et moyennement lent)
-   1 ballon vert -> contient 1 ballon bleu (1pv et moyennement rapide)
-   1 ballon jaune -> contient 1 ballon vert (1pv et rapide)
-   1 ballon rose -> contient 1 ballon jaune (1pv et très rapide)
-   1 ballon noir -> contient 2 ballon rose (1pv et invincible au explosions)
-   1 ballon glacé -> contient 2 ballon rose (1pv et invincible contre le froid)
-   1 ballon d'acier -> contient 2 ballon noir (1pv et invincible contre les projectiles pointu)

![Image d'explication des ballons](/explication_ballon.png)

## Changement effectuer sur le projet par defaut (\* = triée par difficulté de façon subjective) :

-   Les enemies ont été renommé en tant que "Bloon" et les tour en tant que "Singe"
-   Fenetre de résolution : 1240 x 720 px
-   Grille : 31 x 18 -> une tuile fait 40 x 40 px
-   Point de vie de départ : 200
-   Sprite des tours et enemies customiser
-   -   Arrière plan customiser (pas de tuile mais en une seul image)
-   Système de niveaux avec chacun des pathing, environnements et difficulté différentes
-   Système de grille évolué avec prévisualisation des emplacements disponible intéractive quand une tour veux etre placé (voir ![Image d'explication grille](/explication_grille.png))
-   Ajout d'une interface visuel intéractive d'achat et d'amélioration sur le panel de droite

## Touches utiles en jeu :

-   [D] passe en mode DEBUG
