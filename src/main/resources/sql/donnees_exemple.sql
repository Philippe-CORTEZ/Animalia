-- Script qui insère des données d'exemple --
-- Philippe CORTEZ --



-- ANIMAL --
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (741852963741852, 'SUSHI', '2016-10-12', 'MALE', 'CHIEN', 'CHIHUAHUA', 'Sushi est un chien très joueur
OK chat/enfant', false, 60);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (789456123078945, 'SPIRIT', '2015-08-15', 'FEMELLE', 'CHEVAL', 'MUSTANG', 'Spirit est une jument calme
Elle aime se dépenser dans des grands espaces', false, 150);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (951753825641025, 'ROUKI', '2019-03-06', 'FEMELLE', 'CHAT', 'AMERICAN-SHORTHAIR', 'Très espiègle rouki est un chat qui aime jouer', false, 50);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (341256987410256, 'TITI', '2021-09-16', 'MALE', 'OISEAU', 'CANARI', 'Titi est un canari tombé du nid,
Il lui manque une patte, mais cela ne l''empêche pas de chanter', true, 0);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (415263985201467, 'EPONA', '2013-01-18', 'FEMELLE', 'CHEVAL', 'ALEZANE', 'Epona vous accompagnera dans tous vos périple', false, 150);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (123005664789541, 'KERMIT', '2019-09-12', 'MALE', 'GRENOUILLE', 'HYALINOBATRACHIUM DIANAE', 'Kermit est une grenouille plein de peps
Il aime vivre dans un milieu assez tropical', true, 0);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (333214569985201, 'GRIGNOTE', '2018-11-22', 'MALE', 'LAPIN', 'MINI-LOP', 'Grignote est un lapin solitaire
Il adore manger et dormir', false, 30);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (895100224563510, 'SIRIUS', '2012-12-19', 'MALE', 'CHIEN', 'HUSKY', 'Sirius est un chien très calme
OK enfant, Incompatible chat', true, 0);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (414523654100238, 'JUPYTER', '2017-04-14', 'FEMELLE', 'SERPENT', 'PYTHON-ROYAL', 'Jupyter est un serpent calme
Il aime être dans son vivarium', false, 70);
INSERT INTO public.animal (num_puce, nom, date_naissance, sexe, espece, race, description, sos, prix) VALUES (655210047899663, 'SLEEPY', '2014-05-06', 'FEMELLE', 'CHAT', 'RAGDOLL', 'Sleepy est atteinte du FIV féline
(Aucun risque pour les humains)
Sleepy est très joueuse, elle n''attend qu''un foyer pour l''aimer', true, 0);



-- REFUGE --
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (1, 'ANIMALIA-ROUEN', '325-RUE-ALBERT-CAMUS-76-ROUEN', 'Animalerie spécialisée dans les pensionnaires domestiques type chien, chat');
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (2, 'ANIMALIA-LA-GARDE', 'AVENUE-UNIVERSITE-83-LA-GARDE', 'Spécialiste des NAC');
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (3, 'ANIMALIA-PARIS-CENTRE', 'AVENUE-BERCY-75-PARIS-CENTRE', 'Proche du stade de bercy');
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (4, 'ANIMALIA-MARSEILLE', 'RUE-DU-PRADO-13-MARSEILLE', 'Proche du prado à marseille, accueille les animaux de compagnies standard (chats, chiens, oiseaux)');
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (5, 'ANIMALIA-VALLAURIS', '2323-CHEMIN-DE-SAINT-BERNARD-06-VALLAURIS', 'A coté de l''entreprise Avisto');
INSERT INTO public.refuge (id, nom, adresse, description) VALUES (6, 'ANIMALIA-RENNES', '4785-PLACE-LOIC-35-RENNE', 'Capital de la cybersécurité, votre futur animal l''est aussi');



-- SOIN --
INSERT INTO public.soin (id, nom, description, prix, pourcentage_charge) VALUES (3, 'VERMIFUGE', 'Suppression des vers dans l''estomac', 30, 0.75);
INSERT INTO public.soin (id, nom, description, prix, pourcentage_charge) VALUES (4, 'ANTI-PUCE', 'Traitement anti puces dans le bain', 20, 0.4);
INSERT INTO public.soin (id, nom, description, prix, pourcentage_charge) VALUES (5, 'VACCIN-RAGE', 'Vaccin contre la rage', 40, 0.5);
INSERT INTO public.soin (id, nom, description, prix, pourcentage_charge) VALUES (6, 'VACCIN-PARVOVIROSE', 'Vaccin contre la parvovirose', 35, 0.3);
INSERT INTO public.soin (id, nom, description, prix, pourcentage_charge) VALUES (7, 'VACCIN-GRIPPE', 'Vaccin contr la grippe', 30, 0.1);



-- PATHOLOGIE --
INSERT INTO public.pathologie (id, nom, description) VALUES (1, 'HYDROCEPHALE', 'Eau dans la boite crânienne');
INSERT INTO public.pathologie (id, nom, description) VALUES (2, 'PARVOVIROSE', 'Gasto-entérite sévère voire mortelle');
INSERT INTO public.pathologie (id, nom, description) VALUES (3, 'LEISHMANIOSE', 'Maladie cutanée');
INSERT INTO public.pathologie (id, nom, description) VALUES (4, 'CYSTITE', 'Affection urinaire');
INSERT INTO public.pathologie (id, nom, description) VALUES (5, 'FIV-FELINE', 'Le virus de l''immunodéficience féline (FIV) est une grave infection virale chez le chat.
Même s''il est similaire au VIH (sida) humain, le FIV est spécifique à une espèce, ce qui signifie qu''il peut être uniquement transmis entre chats et non pas aux humains ou autres animaux.');



-- INFORMATION SEJOUR --
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (1, 741852963741852, 1, '2019-07-12', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (2, 789456123078945, 5, '2020-02-07', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (3, 951753825641025, 3, '2021-11-26', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (4, 341256987410256, 2, '2021-09-17', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (5, 415263985201467, 6, '2018-10-26', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (6, 123005664789541, 4, '2020-04-10', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (7, 333214569985201, 2, '2019-07-13', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (8, 895100224563510, 1, '2015-12-30', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (9, 414523654100238, 4, '2018-10-24', null, null, true);
INSERT INTO public.information_sejour (id, num_puce_animal, id_refuge, date_debut_sejour, date_fin_sejour, motif_fin_sejour, refuge_actuel) VALUES (10, 655210047899663, 6, '2016-08-18', null, null, true);




-- SOINS_ANIMAUX --
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (741852963741852, 3);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (741852963741852, 4);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (741852963741852, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (789456123078945, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (789456123078945, 7);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (951753825641025, 4);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (951753825641025, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (415263985201467, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (415263985201467, 7);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (333214569985201, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (895100224563510, 3);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (895100224563510, 4);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (895100224563510, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (655210047899663, 4);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (655210047899663, 5);
INSERT INTO public.soins_animaux (num_puce_animal, id_soin) VALUES (655210047899663, 7);



-- PATHOLOGIES_ANIMAUX
INSERT INTO public.pathologies_animaux (num_puce_animal, id_pathologie) VALUES (895100224563510, 4);
INSERT INTO public.pathologies_animaux (num_puce_animal, id_pathologie) VALUES (655210047899663, 5);
