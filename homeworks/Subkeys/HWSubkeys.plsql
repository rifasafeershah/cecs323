CREATE TABLE Nationality (
	NameOfNation VARCHAR(50) NOT NULL,
	CONSTRAINT nationality_pk PRIMARY KEY (NameOfNation)
);

CREATE TABLE Composer (
	NameOfComposer VARCHAR(50) NOT NULL,
	NameOfNation VARCHAR(50),
	YearOfBirth VARCHAR(50),
	CONSTRAINT nationality_fk FOREIGN KEY (NameOfNation)
	REFERENCES Nationality (NameOfNation),
	CONSTRAINT composer_pk PRIMARY KEY (NameOfComposer)
);

CREATE TABLE Composition (
	TitleOfComposition VARCHAR(50) NOT NULL,
	NameOfComposer VARCHAR(50) NOT NULL,
	CONSTRAINT composer_fk FOREIGN KEY (NameOfComposer)
	REFERENCES Composer (NameOfComposer),
	CONSTRAINT composition_pk PRIMARY KEY (TitleOfComposition, NameOfComposer)
);

INSERT INTO Nationality (NameOfNation)
VALUES ('German'), ('Russian'), ('Czech');

INSERT INTO Composer (NameOfComposer, NameOfNation, YearOfBirth)
VALUES ('Beethoven', 'German', '1770'), ('Brahms', 'German', '1841'), ('Shostakovich', 'Russian', '1906'), ('Mozart, W.A.', 'German', '1756'), ('Dvorak', 'Czech', '1841');

INSERT INTO Composition (TitleOfComposition, NameOfComposer)
VALUES ('Symphony #9', 'Beethoven'), ('Violin Concerto', 'Beethoven'),('Violin Concerto', 'Brahms'), ('Symphony #9', 'Shostakovich'), ('Clarinet Concerto', 'Mozart, W.A.'), ('Symphony #9', 'Dvorak');

SELECT *
FROM Composition C
INNER JOIN Composer c ON C.Composer = c.NameOfComposer;
