##### Binôme : Cody ADAM et Fabien GOARDOU

# Page Github du projet : [Tower-Defence](https://github.com/CodyAdam/Tower-Defence)

## TODO

-   [ ] Ajout de tours
-   [ ] Pop animation
-   [ ] Animation on hover

## Concept du projet :

Quand nous avons vu que le thème du projet était sur les jeux de type Tower defence, nous avons eu l'idée de faire une sandbox simplifiée du jeu "Bloons TD 5" avec les outils mis à notre disposition, ce qui a été un challenge vraiment enrichissant.

La majorité des [assets](https://en.wikipedia.org/wiki/Digital_asset) sont donc issus de ce même jeu, les images ne sont donc pas libre de droit. C'est pourquoi nous n'allons aucunement rendre ce jeu public, celui-ci restera dans le cadre du projet de PO. Quelles ont été nos motivations pour avoir pris les [sprites](<https://en.wikipedia.org/wiki/Sprite_(computer_graphics)>) de ce jeu ? Le projet de PO a pour but de tester nos capacités en programmation et non en graphisme, c'est pourquoi emprunter les sprites de ce jeu nous permet de gagner du temps en ayant des images prêtes à être utilisé tout en rendant notre projet original.

## Concept du gameplay :

En tant que joueur, nous posons des singes sur le terrain dans le but de se défendre contre des vagues de ballons appelés "Bloons". Les différents singes possèdent des capacités diverces et variées. La particularité de ce jeu est la mécanique des ballons, car quand un ballon éclate, il libère les ballons qu'il contient.

#### Hiérarchie des ballons :

| Type de Bloons      | Capacité                                      | Spécialité                                      |
| ------------------- | --------------------------------------------- | ----------------------------------------------- |
| Ballon rouge        | Ne contient aucun ballon                      | 1pv et lent                                     |
| Ballon bleu         | Contient 1 ballon rouge                       | 1pv et moyennement lent                         |
| Ballon vert         | Contient 1 ballon bleu                        | 1pv et moyennement rapide                       |
| Ballon jaune        | Contient 1 ballon vert                        | 1pv et rapide                                   |
| Ballon rose         | Contient 1 ballon jaune                       | 1pv et très rapide                              |
| Ballon noir         | Contient 2 ballon rose                        | 1pv et invincible au explosions                 |
| Ballon de glace     | Contient 2 ballon rose                        | 1pv et invincible contre le froid               |
| Ballon d'acier      | Contient 2 ballon noir                        | 1pv et invincible contre les projectiles pointu |
| Ballon zebré        | Contient 1 ballon noir et un ballons de glace | 1pv et invincible contre les projectiles pointu |
| Ballon arc-en-ciel  | Contient 2 ballon zebré                       | 1pv et très rapide                              |
| Ballon céramic      | Contient 2 ballon arc-en-ciel                 | 10pv et lent                                    |
| Ballon aérien bleu  | Contient 4 ballon céramique                   | 200pv et très lent                              |
| Ballon aérien rouge | Contient 4 ballon aérien bleu                 | 700pv et méga lent                              |
| Ballon aérien noir  | Contient 4 ballon aérien rouge                | 4000pv et giga lent                             |

![Image d'explication des Bloons](/explication_ballon.png)

## Changement effectué sur le projet par défaut :

-   Les ennemies ont été renommés en tant que "Bloon" et les tours en tant que "Monkey"
-   Fenêtre de résolution fixée de 1240 x 720 px
-   Grille de 31 x 18 tuiles (une tuile fait 40 x 40 px)
-   Sprite des tours et ennemies customisées
-   Arrière-plan customisé (pas de tuile mais une seule image)
-   Police customisée (voir bibliographie)
-   Changement total des unités ennemies et alliées (Bomb et Arrow Tower remplacés par les différents Singes et les monstres sont remplacés par le système de Bloons)
-   Système de niveaux avec chacun des chemins, environnements et difficultés différentes (4 disponibles)
-   Générateur procédural de niveaux intelligent avec 3 modes de génération et animation de génération
-   Un mode débug avancé avec un affichage de multiples informations et accès à des commandes de "triche"
-   Ajout d'une interface visuelle interactive d'achat et d'amélioration. Le jeu ce joue à la souris
-   Système de popup textuel animé avec du [easing](https://easings.net/fr) modulable
-   Amélioration de la classe Position par l'ajout de fonctions utiles
-   Possibilité pour le joueur d'accélérer le temps et jouer en vitesse x2.5
-   Système de grille évolué avec prévisualisation des emplacements disponible interactive quand une tour souhaite être placée
-   Système de tile (tuile en français) couplé à la grille, tout objet interactif du jeu est représenté par une tile dans la grille à l'éxception des ballons
-   Ajout de passages pour les ballons dans des tunnels, empêchant les tours de les attaqués
-   Système de projectiles, avec un capteur de collision modulable
-   Système d'amélioration de tour via l'interface graphique avec changement des compétence des tours quand elles sont améliorées
-   Système de changement de cible via l'interface graphique
-   Menu d'accueil avec sélection de niveaux via l'interface graphique avec des petites animations

## Touches utiles en jeu :

-   Tout se joue avec la souris à l'exception de deux touches qui vous seront indiqué en jeu

## Bibliographie :

-   Sprites utilisés : [Jeu Bloons TD 5](https://store.steampowered.com/app/306020Bloons_TD_5/)
-   Image de fond de l'écran d'accueil : [Bloons TD 5 Playstation](https://store.playstation.com/fr-fr/product/EP2575-CUSA08065_00-BTD5000000000001)
-   Image de fond pour le niveau de génération procédural : [Plants vs. Zombies Wiki](https://plantsvszombies.fandom.com/wiki/Day)
-   Police utilisée : [Fredoka One (Regular)](https://fonts.google.com/specimen/Fredoka+One)
-   Police 1 de l'écran d'accueil : [Azo Sans](https://fonts.adobe.com/fonts/azo-sans)
-   Police 2 de l'écran d'accueil : [Nunito](https://fonts.google.com/specimen/Nunito)
