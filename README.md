# ProductStock

Cuando se pinta una parrilla de productos en los frontales web de las tiendas de comercio electrónico es necesario
filtrar aquellos productos que se han quedado sin stock para facilitar al usuario el encontrar productos que pueda
comprar,

Para ello se deben comprobar todas las tallas de cada producto para ver si tienen stock, y en caso de que ninguna
talla tenga stock, ese producto no deberá mostrarse en la parrilla.

Existen dos casuísticas especiales:

+ La primera es cuando una talla está marcada como back soon, en este caso, aunque la talla no tenga stock el
producto debe mostrarse igual, puesto que es un producto que va a volver a estar a la venta cuando vuelva a
entrar stock.

+ La segunda es cuando un producto tiene tallas “especiales”, en este caso el producto solo estará visible si al
menos una talla especial y una no especial tiene stock (o está marcada como back soon). Si solo tienen stock (o
están marcadas como back soon) tallas de uno de los dos grupos el producto no debe mostrarse. Esta
casuística se utiliza en productos compuestos, por ejemplo, un cojín que consta de un relleno y una funda, solo
se muestra si hay stock tanto del relleno como de la funda, si no hay stock de ninguno o solo del relleno o solo
de la funda, entonces el cojín no se muestra.

Se pide desarrollar un algoritmo que lea tres ficheros en formato csv que simulan las tablas en base de datos:

esvi fichero con los siguientes campos:
id:identificador de producto.
sequence: posición del producto en la parrilla.
esvi fichero con los siguientes campos:
id: identificador de talla.
productid: identficador de producto.
backSoon: true si la talla es back soon o felse en caso contrario.
special: true si la talla es especial o false en caso contrario.
esvi fichero con los siguientes campos:
seld: identificador de talla.
quantity: unidades disponibles en almacén de dicha talla.
  

Y que ofrezca como salida la lista de identificadores de producto, ordenados por el campo sequence, que cumplan
las condiciones de visibilidad explicadas anteriormente y separados por comas.

Ejemplo:

product.csv:
1,10
2,7
3,15
4,13
5,6

size.csv:

11, 1, true, false
12, 1, false, false
13,1, true, false
21, 2 false, false
22,2 false, false
23, 2, true, true
31, 3, true, false
32, 3, true, false
33, 3, false, false
41,4, false, false
42, 4 false, false
43 4 false false
