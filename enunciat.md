# Calculadora d'enters

Volem desenvolupar una calculadora d'enters seguint l'arquitectura de tres capes (presentació, domini i persistencia), tal que apart de fer les operacions aritmètiques clàssiques pugi també fer calculs de funcions lògiques i que pugi operar amb numeros decimals, binaris i hexadecimals.

A més, es vol mantenir una base de dades d'usuaris amb estadistiques per poder saber l'us d'aquesta eina en un sistema concret: total d'operacions i total d'excepcions per cada usuari i per tot el sistema.

La calculadora ha de ser capaç també d'operar amb fitxers, on per cada linia s'espera una operacio, seguin el format tradicional i que escriuran els resultats en un fitxer amb el mateix nom, afegint *_solved*, i on per cada linia apareixera el resultat

# Requeriments de la calculadora: 

L'aplicació ha de permetre a l'usuari entrar amb unes credencials (usuari i clau de pas) abans de fer cualsevol operació, deixarem que un administrador sigui el que crei nous usuaris (no cal implementar aquesta funcionalitat).

La calculadora ha de poder calcular sumes, restes, multiplicacions, divisions, mod amb numeros enters, els resultats hauran de ser també numeros enters. Per simplicitat, no cal tenir cura d'expressions parentitzades, les operacions tenen un o dos operadors i utilitzen el format standard (p.e. 5 * 7).

Com a afegit, la calculadora haura de tenir la funcionalitat de memoria (tecles M-, M+, M i RST) que permeten guardar valors temporals, que s'esborrarien si el usuari surt de l'aplicació i mantenir un historial del les operacions amb el seu resultat i el temps que ha emprat per calcular-les. També cal tenir una tecla Ans que permeti operar amb l'ultim valor calculat de cada usuari.

El mòdul lógic de la calculadora haurà d'implementar les operacions and, or, not i xor.

Com a petició expressa d'un usuari, la calculadora haura també de poder calcular la sequencia de fibonacci (per un valor N) utilitzant programació dinàmica (i poguent compartir els resultats intermedis entre tots els usuaris); el resultat d'aquesta operació sera el ultim numero de la serie.

# Representacio dels numeros

L'aplicació ha de usar numeros decimals, binaris i hexadecimals. Els operadors *to_int*, *to_decimal*, *to_hexa* seguits d'un numero serviran per fer conversions.

L'aplicació ha d'entendre que un numero que comenci per *b* seguit de ceros i uns, i per *0x* seguit de digits del 0 al F sera binari o hexadecimal respectivament (i el resultat de la operació haura de ser retornat amb aquest format).

# Excepcions

La calculadora haura de controlar les excepcions de 
* divisió per 0
* numero invàlid de arguments per una operació 
* memoria invalida (si s'utilitza Ans o la memoria abans de definir un valor).
* Format invàlid al introduir un numero amb un format erroni

# Interficie gràfica

S'espera una interficie grafica que permeti a l'usuari entrar a l'aplicació amb un nom d'usuari i una contrasenya. Un cop entrat a l'aplicació, es necesita que hi hagi una pantalla amb la classica forma d'una calculadora, que permeti introduir els numeros i operacions amb botons i també directament amb el teclat.

Hi haura un boto amb una icona que representi unes estadistiques, que reportara les estadistiques totals del usuari i del sistema.


