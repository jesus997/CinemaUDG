# CinemaUDG
Proyecto #1 para la clase Seminario de Solución de Problemas de Sistemas Operativos

## Instrucciones:
La empresa “Cinema UDG” actualmente presenta problemas de asignación de asientos y cobro de tarifas para el
ingreso de sus clientes. Por lo tanto, se solicita mantener un control ordenado y coordinado para llevar la
administración, por lo tanto se debe ajustar a las siguientes políticas:
Modo de operación:

1. Cuenta con 5 cajas de cobro de las cuales se realiza la venta de boletos y la asignación de espacios para
disfrutar de la película. Actualmente cuenta con 6 salas de cine con capacidad máxima de 60 personas, cada sala
cuenta con 10 filas asignadas con las letras del alfabeto que corresponden desde la letra A hasta la letra J.
1. El ingreso a la fila para realizar la compra de boletos será de manera aleatoria de un intervalo de 0 a 15
segundos en la cual pueden llegar los clientes desde 0 a 8 personas. Cada cliente deberá seleccionar una película
aleatoria de la cual se asignará a la sala correspondiente. El programa deberá registrar los nombres de las
películas y en que sala se estará proyectando.
1. Dependiendo de la disponibilidad de la sala se asignara de manera automática el número de asiento y la fila
que corresponde de forma aleatoria. El sistema dejará de funcionar cuando las 6 salas se encuentren llenas, en
el caso de que un cliente requiera o solicita el ingreso a una sala y está se encuentre llena, el sistema deberá de
reasignar a la película o sala que cuente con más espacios a fin de poder estar en posición de llenarse.
1. Cada cliente puede comprar desde 1 a 4 boletos, en caso de que desee comprar 4 boletos y la sala sólo cuente
con espacio para 2 o 1, sólo se venderá el boleto que represente en este caso la disponibilidad de la sala.
1. Cuando se finalice el sistema deberá mostrar por cajeros el monto recabado, la cantidad de clientes atendidos
y el tiempo estimado desde la apertura del cinema hasta el último cliente disponible para el ingreso a la sala. Si
existen clientes que están en espera de ser atendidos y el cinema se encuentre cerrado estos solo serán
mostrados.
