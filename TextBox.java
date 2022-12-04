class TextBox extends Program {

  Coordonnees newCoordonnees(int ligne, int colonne) {
    Coordonnees result = new Coordonnees();
    result.ligne = ligne;
    result.colonne = colonne;
    return result;
  }

  Rectangle newRectangle(Coordonnees coordHautGauche, int largeur, int hauteur, char delimiteur) {
    Rectangle result = new Rectangle();
    result.largeur = largeur;
    result.hauteur = hauteur;
    result.delimiteur = delimiteur;
    result.coord = new Coordonnees[4];
    result.coord[0] = coordHautGauche;
    result.coord[1] = newCoordonnees(coordHautGauche.ligne, coordHautGauche.colonne + largeur);
    result.coord[2] = newCoordonnees(coordHautGauche.ligne + hauteur - 1, coordHautGauche.colonne);
    result.coord[3] = newCoordonnees(coordHautGauche.ligne + hauteur - 1, coordHautGauche.colonne + largeur);
    return result;
  }

  void dessinerRectangle(Rectangle rectangle) {
    //On se place au bon endroit pour imprimer le rectangle
    cursor(rectangle.coord[0].ligne, rectangle.coord[0].colonne);
    //Dessin de la premiere ligne
    print(ligneComplete(rectangle.largeur, rectangle.delimiteur));
    String dessin = "";
    for (int i = 1; i < rectangle.hauteur - 1; i++) {
      //On change de ligne
      cursor(rectangle.coord[0].ligne + i, rectangle.coord[0].colonne);
      //On dessines les lignes intérieurs
      print(extremite(rectangle.largeur, rectangle.delimiteur));
    }
    //On se déplace une dernière fois
    cursor(rectangle.coord[2].ligne, rectangle.coord[2].colonne);
    //Dessin de la dernière ligne
    print(ligneComplete(rectangle.largeur, rectangle.delimiteur));
  }

  //Permet d'imprimer lettre par lettre avec un délai
  void print(String str, int msDelay) {
    int tailleStr = length(str);
    for (int i = 0; i < tailleStr; i++) {
      char lettreCourante = charAt(str, i);
      delay(msDelay);
      print(lettreCourante);
    }
  }

  void ecrireDansRectangle(Rectangle rect, Paragraphe parag, int ligneDebut, int msDelay) {
    int nbLigneUtilise = 0;
    //On regarde toutes les lignes
    for (int i = 0; i < length(parag.lignes) && i <= rect.hauteur - 2; i++) {
      //On se positionne correctement
      int ligneIndex = rect.coord[0].ligne + i + ligneDebut + nbLigneUtilise;
      int colonneIndex = rect.coord[0].colonne + 2;
      cursor(ligneIndex, colonneIndex);
      //S'il y a suffisamment de place sur la ligne
      if (length(parag.lignes[i].ligneStr) <= rect.largeur - 4) {
        print(parag.lignes[i].ligneStr, msDelay);
      } else {
        //Lorsqu'il n'y a pas assez de place pour tout écrire
        //On parcoure tous les mots
        int tailleUtilisee = 0;
        for (int j = 0; j < length(parag.lignes[i].mots); j++) {
          //On écrit le prochain mot uniquement s'il y a la place
          int tailleMot = length(parag.lignes[i].mots[j]);
          int tailleNecessaire = (rect.largeur - 4) * (1 + nbLigneUtilise);
          if (tailleMot + tailleUtilisee < tailleNecessaire) {
            print(parag.lignes[i].mots[j], msDelay);
            tailleUtilisee = tailleUtilisee + tailleMot;
            //On essaye tout de même de l'afficher si c'est le dernier espace qui bloque
          } else if (tailleMot + tailleUtilisee - 1 < tailleNecessaire && charAt(parag.lignes[i].mots[j], tailleMot - 1) == ' ') {
            //Dans ce cas, on peut imprimer sans l'espace final
            print(substring(parag.lignes[i].mots[j], 0, tailleMot - 1), msDelay);
          } else {
            //Si non, il faut changer de ligne
            nbLigneUtilise++;
            //Et comptabiliser les caractères non utilisés
            tailleUtilisee = (rect.largeur - 4) * nbLigneUtilise;
            //On se déplace
            cursor(ligneIndex + nbLigneUtilise, colonneIndex);
            //On décrémente j pour retenter d'écrire le mot
            j = j - 1;
          }
        }
      }
    }
  }

  String ligneComplete(int taille, char delimiteur) {
    String result = "" + delimiteur;
    for (int i = 0; i < (taille - 2) / 2; i++) {
      result = result + " " + delimiteur;
    }
    return result + " " + delimiteur + "\n";
  }

  String extremite(int taille, char delimiteur) {
    String result = "" + delimiteur;
    for (int i = 0; i < taille - 2; i++) {
      result = result + " ";
    }
    return result + delimiteur + "\n";
  }

  int compterOccurence(String texte, char c) {
    int result = 0;
    int tailleTexte = length(texte);
    for (int i = 0; i < tailleTexte; i++) {
      char lettreCourante = charAt(texte, i);
      if (lettreCourante == c) {
        result++;
      }
    }
    return result;
  }

  String[] separerTexte(String texte, char c, int total) {
    String[] result = new String[total];
    int tailleTexte = length(texte);
    int nbLigne = 0;
    int j = 0;
    String ligne = "";
    while (nbLigne < total && j < tailleTexte) {
      char lettreCourante = charAt(texte, j);
      ligne = ligne + lettreCourante;
      if (lettreCourante == c || j == tailleTexte - 1) {
        result[nbLigne] = ligne;
        nbLigne++;
        ligne = "";
      }
      j++;
    }
    return result;
  }

  Ligne newLigne(String texte) {
    texte = enleverNonImprimable(texte);
    Ligne result = new Ligne();
    result.ligneStr = texte;
    int totalMot = compterOccurence(texte, ' ') + 1;
    result.mots = separerTexte(texte, ' ', totalMot);
    return result;
  }

  String enleverNonImprimable(String str) {
    //Si le dernier caractère est non imprimable, on l'enlève
    char derniereLettre = charAt(str, length(str) - 1);
    while (derniereLettre < ' ' && length(str) > 0) {
      str = substring(str, 0, length(str) - 1);
      if (length(str) > 0) {
        derniereLettre = charAt(str, length(str) - 1);
      }
    }
    return str;
  }

  Paragraphe newParagraphe(String texte) {
    texte = enleverNonImprimable(texte);
    int tailleTexte = length(texte);
    Paragraphe result = new Paragraphe();
    int totalLignes = compterOccurence(texte, '\n') + 1;
    if (totalLignes == 0) {
      totalLignes++;
    }
    result.lignes = new Ligne[totalLignes];
    String[] contenuLignes = separerTexte(texte, '\n', totalLignes);
    for (int i = 0; i < totalLignes; i++) {
      result.lignes[i] = newLigne(contenuLignes[i]);
    }
    return result;
  }

  void changerTailleTerminal(int hauteur, int largeur) {
    print("\033[8;" + hauteur + ";" + largeur + "t");
  }

  //void effacerLigne(Coordonnees coordDebut, )

  void algorithm() {
    clearTerminal();
    changerTailleTerminal(23, 41);
    Rectangle rectanglePrincipal = newRectangle(newCoordonnees(1, 1), 41, 20, '#');
    dessinerRectangle(rectanglePrincipal);
    //Paragraphe story = newParagraphe("le choix entre 3 voeux :\n   1- Etre belle\n   2-Etre riche\n   3-Les deux\n\nEt elle choisissa...");
    Paragraphe story = newParagraphe("\nIl était une fois une princesse nomée Maxime qui avait le choix entre 3 voeux :\n   1- Etre belle\n   2- Etre riche\n   3- Les deux\n\nEt elle choisissa...\n");
    ecrireDansRectangle(rectanglePrincipal, story, 1, 20);
    Rectangle rectangleSecondaire = newRectangle(newCoordonnees(13, 4), 15, 3, '*');
    dessinerRectangle(rectangleSecondaire);
    cursor(rectangleSecondaire.coord[0].ligne + rectangleSecondaire.hauteur / 2, rectangleSecondaire.coord[0].colonne + 2);
    while (true) {

    }
  }

  //Ecrire dans un rectangle
    //--> Besoin coordonnées début et coordonnées fin ?
  //Cas du saut de ligne ?
  //Cas d'une ligne tronqué ?
    //--> Faire un tableau avec tous les mots ?
}
