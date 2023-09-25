INSERT INTO "user" (usr_id, usr_username, usr_password, usr_email, usr_is_admin)
VALUES  ('U1', 'U1', '123456$', 'u1@test.fr', 1),
        ('U2', 'U2', '123456$', 'u2@test.fr', 0);

INSERT INTO personne (per_nom)
VALUES
('F1'),
('C1'),
('F2'),
('F3');

INSERT INTO fournisseur (fou_id)
VALUES
(1),
(3),
(4);

INSERT INTO client (cli_id, cli_prenom, cli_date_naissance)
VALUES
(2, 'Prénom', '1987-11-02');


INSERT INTO produit (pro_nom, pro_prix, pro_fournisseur_id)
VALUES
('P0', 200, 1),
('P1', 50, 3),
('P2', 750, 1);