# comandos

para guardar la consola

```bash

rlwrap lein repl

```

y linkear

```bash

sudo ln -s /home/okadath/.lein/repl-history /home/okadath/Desktop/clojure/learn_clojure/repl-hist.clj
```

ìnstalar lein y rlwrap para poder trabajar bien

las comas las trata como espacios :O

cargar scripts de clojure y ejecutarlos en consola

```bash
$ 
lein repl

=>
(load-file "history_clean.clj")
```

el repl-history es todo el de lein asi que hay que copiarlo(x q se recarga) y limpiarlo de errores antes de cargarlo

la ```'``` significa que la siguiente lista son datos (= "no evalues clojure pls!!")

todo se vuelve a copiar, arrays o cvectores o mapas, si es mas facil se usa []
hash-map se usa para {}

assoc y dissoc agregan elementos al {} y funcionan con vectores

los keywords son como strings pero apra indicar nombres :key

## Luminuse

```clj
lein new myapp
lein run A
```

agregar al `project.clj`:

```clj
  :main myapp.core/foo
```

crear proyecto con las librerias y una plantilla:

```sh
#se puede omitir -template-version 3.91
lein new luminus guestbook --template-version 3.91 -- +h2 +http-kit

# para agregar sqlite 

# +sqlite
```

en guestbook/env/dev/clj/user.clj vive el namespace de user, ahi hay funciones utiles

con esto cambiamos al ns del usuario para iniciar a migrar
```clj
(in-ns 'guestbook.user);si no jala regresar con (in-ns 'user)
(start);inicia la conexion con la db

```

agregar al up de la migracion(espero que sea el unico sql a mano):

```sql
CREATE TABLE guestbook
(id INTEGER PRIMARY KEY AUTOINCREMENT,
name VARCHAR(30),
message VARCHAR(200),
timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);


-- al .down

DROP TABLE guestbook;
```

y migrar

```clj
(migrate)
...
(restart)
```

agregar al `guestbook/resources/sql/queries.sql`, hugsql leera la metadata y asi creara la info de las funciones :

```sql
-- :name save-message! :! :n
-- :doc creates a new message using the name and message keys
INSERT INTO guestbook
(name, message)
VALUES (:name, :message)
-- :name get-messages :? :*
-- :doc selects all available messages
SELECT * from guestbook
```

Notice that the save-message! query name is followed by :! and :n flags. The first
flag indicates the query is destructive. The second flag indicates that the
query returns the number of affected rows.
The get-messages query uses the :? flag to indicate that the query does a select,
and the :* flag indicates that multiple rows are returned.

cambiar de ns y recargar las funciones sql creadas

```clj
(in-ns 'guestbook.db.core)
(conman/bind-connection *db* "sql/queries.sql");recargar las funciones
(keys (ns-publics 'user));lista las funciones del ns
(get-messages)
(save-message! {:name "bob" :message "hii"})
 ```
 