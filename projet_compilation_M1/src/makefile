all: programmeTraduction
programmeTraduction:  grammaire.tab.o lex.yy.o translate.o tableSymboles.o tableSymbolesSecondaires.o
	gcc -o $@ $^ -lfl
grammaire.tab.o: grammaire.tab.c grammaire.tab.h
	gcc -o $@ -c grammaire.tab.c
grammaire.tab.c grammaire.tab.h: grammaire.y 
	bison grammaire.y --defines=grammaire.tab.h
tableSymboles.o: tableSymboles.h tableSymboles.c
	gcc -o $@ -c tableSymboles.c
tableSymbolesSecondaires.o: tableSymbolesSecondaires.h tableSymbolesSecondaires.c
	gcc -o $@ -c tableSymbolesSecondaires.c	
translate.o: translate.h translate.c
	gcc -o $@ -c translate.c
lex.yy.o: lex.yy.c
	gcc -o $@ -c lex.yy.c
lex.yy.c: annalyseur_lexical.l grammaire.tab.h
	flex annalyseur_lexical.l
clean:
	rm programmeTraduction *.o lex.yy.c grammaire.tab.c grammaire.tab.h