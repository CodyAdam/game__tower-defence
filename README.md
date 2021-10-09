
# About

One of our **school project** was to make a **tower defence game** and the restriction were to only use **Java** and the **StdDraw** library
This project was made in groups of 2 (my teamate : [Fgdou](https://github.com/Fgdou))

# Features and demo :

![preview0](https://github.com/CodyAdam/Tower-Defence/edit/main/preview0.gif)
![preview1](https://github.com/CodyAdam/Tower-Defence/edit/main/preview1.gif)
![preview2](https://github.com/CodyAdam/Tower-Defence/edit/main/preview2.gif)
![preview3](https://github.com/CodyAdam/Tower-Defence/edit/main/preview3.gif)
![preview4](https://github.com/CodyAdam/Tower-Defence/edit/main/preview4.gif)
![preview5](https://github.com/CodyAdam/Tower-Defence/edit/main/preview5.gif)

# Projet Tower Defence

## Concept du projet :

Quand nous avons vu que le thème du projet était sur les jeux de type Tower defence, nous avons eu l'idée de faire une version simplifiée du jeu "Bloons TD 5" avec les outils mis à notre disposition, ce qui a été un challenge vraiment enrichissant.

La majorité des [assets](https://en.wikipedia.org/wiki/Digital_asset) sont donc issus de ce même jeu, les images ne sont donc pas libre de droit. C'est pourquoi nous n'allons aucunement rendre ce jeu public, celui-ci restera dans le cadre du projet de PO. Quelles ont été nos motivations pour avoir pris les [sprites](<https://en.wikipedia.org/wiki/Sprite_(computer_graphics)>) de ce jeu ? Le projet de PO a pour but de tester nos capacités en programmation et non en graphisme, c'est pourquoi emprunter les sprites de ce jeu nous permet de gagner du temps en ayant des images prêtes à être utilisé tout en rendant notre projet original.

## Concept du gameplay :

En tant que joueur, nous posons des singes sur le terrain dans le but de se défendre contre des vagues de ballons appelés "Bloons". Les différents singes possèdent des capacités diverces et variées. La particularité de ce jeu est la mécanique des ballons, car quand un ballon éclate, il libère les ballons qu'il contient.

#### Hiérarchie des ballons :

| Type de Bloons      | Capacité                                      | Spécialité                                          |
| ------------------- | --------------------------------------------- | --------------------------------------------------- |
| Ballon rouge        | Ne contient aucun ballon                      | 1pv et lent                                         |
| Ballon bleu         | Contient 1 ballon rouge                       | 1pv et moyennement lent                             |
| Ballon vert         | Contient 1 ballon bleu                        | 1pv et moyennement rapide                           |
| Ballon jaune        | Contient 1 ballon vert                        | 1pv et rapide                                       |
| Ballon rose         | Contient 1 ballon jaune                       | 1pv et très rapide                                  |
| Ballon noir         | Contient 2 ballon rose                        | 1pv et invincible au explosions                     |
| Ballon de glace     | Contient 2 ballon rose                        | 1pv et invincible contre le froid                   |
| Ballon d'acier      | Contient 2 ballon noir                        | 1pv et invincible contre les projectiles pointu     |
| Ballon zebré        | Contient 1 ballon noir et un ballons de glace | 1pv et invincible contre les explosions et le froid |
| Ballon arc-en-ciel  | Contient 2 ballon zebré                       | 1pv et rapide                                       |
| Ballon céramic      | Contient 2 ballon arc-en-ciel                 | 10pv et lent                                        |
| Ballon aérien bleu  | Contient 4 ballon céramique                   | 200pv et très lent                                  |
| Ballon aérien rouge | Contient 4 ballon aérien bleu                 | 700pv et méga lent                                  |
| Ballon aérien noir  | Contient 4 ballon aérien rouge                | 4000pv et giga lent                                 |

![Voir image d'explication des Bloons "explication_ballon.png"](/explication_ballon.png)


## Changement effectué sur le projet par défaut :

-   **C**: Les ennemies ont été renommées en tant que "Bloon" et les tours en tant que "Monkey"
-   **C**: Changement total des unités ennemies et alliées (Bomb et Arrow Tower remplacés par les différents Singes et les monstres sont remplacés par le système de Bloons)
-   **F**: Fenêtre de résolution fixée de 1240 x 720 px avec une grille de jeu de 31 x 18 tuiles (une tuile fait 40 x 40 px)
-   **C**: Sprite des tours, ennemies, Police ainsi qu'arrière-plan customisé (arrière-plan sans tuile mais en une seule image)
-   **C**: Système de niveaux avec chacun des chemins, environnements et difficultés différentes (4 disponibles + 1 procédural)
-   **F**: Générateur procédural de niveaux intelligent avec 3 modes de génération ainsi qu'une animation de génération
-   **F**: Un mode débug avancé avec un affichage de multiples informations et accès à des commandes de "triche"
-   **C**: Ajout d'une interface visuelle interactive d'achat et d'amélioration. Le jeu peu donc être joué uniquement à la souris.
-   **F**: Système de popup textuel animé avec du [easing](https://easings.net/fr). Permet d'afficher pleins d'informations tel que des infos. de vagues ou bien des gains d'argents
-   **F**: Amélioration de la classe Position par l'ajout de fonctions utiles tel que : normalized, angle, rotate, multi, plus, minus, etc.
-   **C**: Système de grille avec prévisualisation des emplacements disponible quand une tour souhaite être placée permetant d'avoir des tours qui prennent plus ou moins de place que d'autres
-   **C**: Système de tile (tuile en français) couplé à la grille, tout objet interactif du jeu est représenté par une tile dans la grille à l'éxception des ballons
-   **C**: Ajout de passages pour les ballons dans des tunnels, empêchant les tours de les attaqués
-   **F**: Système de projectiles capteur de collision par hitbox circulaire
-   **C**: Système d'amélioration de tour via l'interface graphique avec changement des compétences des tours quand elles sont améliorées (Voir la séction Implémentation des tours)
-   **C**: Système de changement de l'ennemie ciblé par la tour via l'interface graphique (Choix entre : cible la plus proche, cible la plus loins dans le chemin, cible la moins loins dans le chemin, cible la plus forte)
-   **C**: Menu d'accueil avec sélection de niveaux via l'interface graphique avec des petites animations
-   **F**: Séparation des TPS (tick/update par secondes) et FPS (frame/draw par secondes) qui permet de facilement géré le temps dans le jeu
-   **F**: Possibilité pour le joueur d'accélérer le temps et jouer en vitesse x4

#### Implémentation des tours :

Chaque tour possède 6 améliorations dont 2 améliorations "unique" (Une tour peux avoir une seule amélioration unique à la fois), les améliorations non-unique change uniquement quelques statistiques de la tour alors que les améliorations uniques change totalement les effets d'une tour ainsi que sont apparence.

-   **F**: "Dart Monkey" : Tour qui lance des fléchettes sur la cible et peut être amélioré en une catapulte à piques ou en arché à triple fléchettes
-   **F**: "Tack Shooter" : Tour qui lance des petites punaises dans toutes les directions et peut être amélioré en expulseur de flames ou en lanceur de scies
-   **C**: "Dartling Gun" : Tour qui lance des fléchettes mais elles les lancent vers la position de la souris les directions et peut être amélioré en cracheur de flames ou en lanceur de scies
-   **C**: "Bomb Shooter" : Tour qui lance des bombes sur la cible, les bombes explose et font des dégâts de zone. La tour peux devenir un lanceur de bombes fragmenté de mini-bombes ou bien un lanceur de rocket anti-ballons-aérien
-   **FC**: "Super Monkey" : Tour qui lance des fléchettes très rapidement mais qui est aussi très couteuse, elle peut devenir un giga-robot qui tire des rayons plasma de ses deux mains ou bien devenir le TEMPLE DE DIEUX SINGES !

## Touches utiles en jeu :

-   Tout se joue avec la souris à l'exception de quelques touches qui vous seront indiqué en jeu

## Bibliographie :

-   Sprites utilisés : [Jeu Bloons TD 5](https://store.steampowered.com/app/306020Bloons_TD_5/)
-   Image de fond de l'écran d'accueil : [Bloons TD 5 Playstation](https://store.playstation.com/fr-fr/product/EP2575-CUSA08065_00-BTD5000000000001)
-   Image de fond pour le niveau de génération procédural : [Plants vs. Zombies Wiki](https://plantsvszombies.fandom.com/wiki/Day)
-   Police utilisée : [Fredoka One (Regular)](https://fonts.google.com/specimen/Fredoka+One)
-   Police 1 de l'écran d'accueil : [Azo Sans](https://fonts.adobe.com/fonts/azo-sans)
-   Police 2 de l'écran d'accueil : [Nunito](https://fonts.google.com/specimen/Nunito)
