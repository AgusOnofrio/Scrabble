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

-Las palabras son validadas automaticamente por un diccionario Oficial del scrabble, por lo que no existe en esta implementacion el "Desafio".


GANADOR

El jugador que obtiene la mayor cantidad de puntos formando palabras en el tablero

  
  FIN DEL JUEGO
   
   Si todos los jugadores pasan dos veces seguidas, se acaba el juego. 


------------------------------------


Faltantes actuales a considerar:

-El jugador que juega primero es el que primero se inicializa, todavia no se definio una forma de sortear quien comienza.
-Las fichas especiales no estan implementadas.

-El jugador pone fichas en su tablero, si la palabra no es valida deberia volver a su atril segun el juego original fisico. En esta implementacion,
 si se forma una palabra no valida no se cuentan los puntos pero no vuelven al atril del jugador.
 
-Por lo mencionado en el punto anterior, no existe la regla del Bonus especial: el bonua especial consiste en que si un jugador utiliza todas
 las letras  de su atril dentro de un turno obtiene 50 puntos. No se implemento esta regla ,ya que si el jugador puede dejar fichas sobre el tablero aunque
 no forme palabras validas, este podria simplemente posicionar todas en el tablero sin formar nada y recibir el bonus.
 
-En el juego original uno puede elegir la cantidad de fichas que quiere cambiar, en esta implementacion solo puede intercambiar todas las fichas de su atril.
 EL proceso de cambio es devolver todas las fichas a la bolsa,revolver y llenar nuevamente el atril, si la bolsa no contiene fichas esto devolveria 
 (obviamente) las mismas fichas.

-Considerar el caso de que ya no hay fichas en la bolsa y uno de los jugadores usó todas las fichas de su atril.En este caso deberia poder
 sumarse el puntaje ganador, todas las letras que hayan quedado en los atriles no ganadores.
 
 -La regla de fin del juego "se formaron todas las palabras posibles." este punto es medio ambiguo ya que no se entiende bien que se deberia validar.

 -No se implemento persistencia

