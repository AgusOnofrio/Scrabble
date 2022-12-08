# Scrabble


Juego de Scrabble implementado en Java.


EL juego tiene la posibilidad de jugar de 2 a 4 jugadores. 

Esta basado en las reglas del juego original de Ruibal que son las siguientes:

-Cada jugador tendra un atril que contendra 7 fichas

-El primer jugador deberá combinar dos o más fichas en el tablero vertical u horizontalmente, poniendo una letra en la casilla
   central (estrella) para formar una palabra. No se permite formar
   palabras en diagonal.
  -Cada vez que finaliza el turno de un jugador, este debe completar su atril. En el caso de que la bolsa de fichas no contenga mas fichas se completará
   parcialmente.

-Una palabra puede estar repetida dentro del tablero.

-Un jugador puede usar su turno para intercambiar las fichas de su atril. Intercambiar fichas cuenta como
   un turno.

-Un jugador puede pasar y perder el turno, pueda o no formar
   una palabra. Si todos los jugadores pasan dos veces seguidas,
   se acaba el juego.
      
-El jugador pone fichas en su tablero, si la palabra no es valida vuelve a su atril segun el juego original fisico. En esta implementacion,
 si se forma una palabra no valida no se cuentan los puntos y las fichas vuelven al atril del jugador.

-Para que una palabra sea valida, todas las fichas que la forman deben formar palabras validas o no formar nada.
   En este caso de ejemplo la letra que "E" que forma "ME" tambien forma "EL" que es una palabra valida, por lo que ambas palabras son validas.

   ![image](https://user-images.githubusercontent.com/94473852/206500094-9d21a6d2-d8f1-45cc-86ab-a5fd89c3ebfa.png)

   En este caso de ejemplo la letra "S" que forma la palabra horizontal "ES" tambien forma "ELES" que es una palabra NO valida segun el diccionario,
   por lo tanto ambas palabras son invalidas.

   ![image](https://user-images.githubusercontent.com/94473852/206501919-24b42965-06c8-4fdb-8084-80ecc178018c.png)


-Las fichas especiales pueden tomar cualquier etiqueta de letra,pero su valor siempre es 0.

-Las fichas especiales no tienen ninguna etiqueta determinada durante la partida. Por ejemplo si un jugador formo la palabra "CAS.", utilizando la letra
 especial como una "A", luego el jugador siguiente podria formar "CAS.ILLO" pensando en esta letra como una "T".

-Las palabras son validadas automaticamente por un diccionario Oficial del scrabble, por lo que no existe en esta implementacion el "Desafio".


   BONUS

   El bonus especial consiste en que si un jugador utiliza todas las letras  de su atril dentro de un turno obtiene 50 puntos. Todas las letras deben formar palabras      validas (si no forman palabras validas vuelven al atril) y pueden haberse posicionado en distintas partes del tablero y en distintas palabras.



   GANADOR

   El jugador que obtiene la mayor cantidad de puntos formando palabras en el tablero

  
  FIN DEL JUEGO
   
   Si todos los jugadores pasan dos veces seguidas, se acaba el juego. 


------------------------------------


Diferencias actuales a considerar:

-El jugador que juega primero es el que primero se inicializa, todavia no se definio una forma de sortear quien comienza.

-No se considero el caso de que ya no hay fichas en la bolsa y uno de los jugadores usó todas las fichas de su atril.En este caso deberia poder
 sumarse al puntaje ganador, todas las letras que hayan quedado en los atriles no ganadores.
 
 -No se implemento persistencia

