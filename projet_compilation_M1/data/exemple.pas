program prog;

{ function f }
FUNCTION estPlusPetitQueDix(nombre:INTEGER):INTEGER;
var resultat:integer;
BEGIN
    resultat := 0;
	IF nombre <= 10 THEN
	BEGIN
		resultat := nombre;
		nombre := 0;
	END
	ELSE
	BEGIN
		resultat := 0;
	END;
	estPlusPetitQueDix := resultat;
END;

{ procedure g }
PROCEDURE afficherUnADix;
var compteur:integer;
BEGIN
	compteur := 1;
	WHILE compteur <= 10 DO
    BEGIN
		writeln(compteur);
		compteur := compteur+1;
	END;
	writeln('Termine');
END;

{ main }
var test:integer;
begin
    writeln('estPlusPetitQueDix(5)');
	writeln(estPlusPetitQueDix(5));
    writeln('afficherUnADix');
	afficherUnADix;
	test := estPlusPetitQueDix(5);
	writeln(test);
end.