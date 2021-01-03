##### Binôme : Cody ADAM et Fabien GOUARDOU

# Page Github du projet : [Tower-Defence](https://github.com/CodyAdam/Tower-Defence)

## TODO

-   [ ] Make bloons go under the bridge!!
-   [ ] Replace In grid space with the new position
-   [ ] Remove inside World In grid pos
-   [ ] Projectiles class
-   [ ] Améliorations
-   [ ] Ability to remove towers
-   [ ] commandes de "triche" en mode DEBUG

## Concept du projet :

Durant mon enfance j'ai beaucoup jouer au jeu tower defénse appeler Bloons TD 5 c'est pourquoi moi et mon binôme, avons eu l'idée de faire une récréation simplifier de ce jeu avec les outils mis à notre disposition, ce qui à été un challenge enrichissant.

Tous les assets sont issus de ce même jeu à l'éxception de la police d'écriture, nous vous renvoyions donc vers la page Steam si vous voulez en savoir plus : [Bloons TD 5 sur Steam](https://store.steampowered.com/app/306020Bloons_TD_5/)

## Concept du gameplay :

En tant que joueur, nous posons des singes sur le terrain dans le but de se défendre contre des vague de ballons appellé "Bloons". Les différents singes possèdes des capacités diverce et varier. La particularité de ce jeu c'est la mécanique des ballons, car quand un ballon éclate, il libère les ballons qu'il contient.

#### Hiérarchie des ballons :

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

## Changement effectué sur le projet par défaut :

-   Les ennemies ont été renommé en tant que "Bloon" et les tours en tant que "Monkey"
-   Fenêtre de résolution de 1240 x 720 px
-   Grille de 31 x 18 tuiles (une tuile fait 40 x 40 px)
-   Sprite des tours et ennemies customiser
-   Arrière plan customiser (pas de tuile mais une seul image)
-   Police customiser (voir bibliographie)
-   Changement total des unités ennemies et alliées (Bomb et Arrow Tower remplacés par les différents singes et les monstres sont remplacés par le système de Bloons)
-   Système de niveaux avec chacun des "pathing", environnements et difficultés différentes
-   Un mode débug avancé avec un affichage de multiples informations et accès à des commandes de "triche"
-   Ajout d'une interface visuelle interactive d'achat et d'amélioration. Le jeu ce joue à la souris
-   Système de popup textuel animée avec du [easing](https://easings.net/fr)
-   Amélioration de la classe Position par l'ajout de fonctions utiles
-   Possibilité pour le joueur d'accélérer le temps et jouer en vitesse x2.5
-   Système de grille évolué avec prévisualisation des emplacements disponible interactive quand une tour souhaite être placée
-   Système de tile (tuile en français) couplé à la grille, tout objet intéractif du jeu est représenté par une tile à l'éxception des ballons
-   Ajout de passages pour les ballons dans des tunnels, empéchant les tours de les attaquers

## Touches utiles en jeu :

-   [Q] Quitte le jeu
-   [D] alterne avec le mode DEBUG

-   [K] en mode DEBUG uniquement : tue tout les ballons à l'écran

## Bibliographie :

-   Sprites utiliser : [Jeu Bloons TD 5](https://store.steampowered.com/app/306020Bloons_TD_5/)
-   Police utiliser : [Fredoka One (Regular)](https://fonts.google.com/specimen/Fredoka+One)
