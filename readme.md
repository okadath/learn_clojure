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

 y agregamos en los tests

 ```clj
 (deftest test-messages
  (jdbc/with-transaction [t-conn *db* {:rollback-only true}]
    (is (= 1 (db/save-message!
              t-conn
              {:name "Bob"
               :message "Hello, World"}
              {:connection t-conn})))
    (is (= {:name "Bob"
            :message "Hello, World"}
           (-> (db/get-messages t-conn {})
               (first)
               (select-keys [:name :message]))))))
               ```

corremos los tests con 

lein test
lein test-refresh


los templates son iguales que en Django, los Views de django son como los routes de Luminus

al parecer cuando corres un server no es posible imprimir en consola... pero si usar logging

```clj
(ns guestbook.routes.home
  (:require
   ...
   [clojure.tools.logging :as log]))
```

en el routes se agrega la logica

```clj
(ns guestbook.routes.home
  (:require
   [guestbook.layout :as layout]
   [guestbook.db.core :as db]
  ;;  [clojure.java.io :as io]
   [guestbook.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]
   [struct.core :as st]
   [clojure.tools.logging :as log]))

(defn home-page [{:keys [flash] :as request}]
  ;; (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))
;; asi se debugguea informacion
  (log/info (db/get-messages))
  (layout/render request "home.html"
                 (merge {:messages (db/get-messages)}
                ;;  los mensajes flash son para indicar errores, es como el paso de contextos
                        (select-keys flash [:name :message :errors]))))

(def message-schema
;; es el filtro para la validacion de mensajes
  [[:name
    st/required
    st/string]
   [:message
    st/required
    st/string
    {:message "message debe tener minimo  10 caracteres"
     :validate (fn [msg] (>= (count msg) 10))}]])

(defn validate-message [params]
  ;; (print (first (st/validate params message-schema)))
;;   llama a la funcion
  (first (st/validate params message-schema)))


(defn save-message! [{:keys [params]}]
  (if-let [errors (validate-message params)]
;;   si hay errores envia una flash session, vive solo en ese momento para el paso de mensajes

    (-> (response/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do
    ;; si no hay errores guarda en la db y regresa la pagina inicial
      (db/save-message! params)
      (response/found "/"))))

(defn about-page [request]
  (layout/render request "about.html"))



(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
    ;;aqui se agregan las rutas  
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/message" {:post save-message!}]])
```

la plantilla del home es similar a Jinja2

```html
{% extends "base.html" %}
{% block content %}
<div class="content">
  <div class="columns is-centered">
    <div class="column is-two-thirds">
      <!-- Content -->
      <div class="columns">
        <div class="column">
          <h3>Messages</h3>
          <ul class="messages">
            {% for item in messages %}
            <li>
              <time>
                {{item.timestamp }}
                <!--  |date:"yyyy-MM-dd HH:mm:ss"}} por alguna razon no evalua el filtro -->
              </time>
              <p>{{item.message}}</p>
              <p>- {{item.name}}</p>
            </li>
            {% endfor %}
          </ul>
        </div>
        <div class="columns">
          <div class="column">
          <form method="POST" action="/message">
          {% csrf-field %}
          <div class="field">
          <label class="label" for="name">
          Name
          </label>
          {% if errors.name %}
          <div class="notification is-danger">
          {{errors.name|join}}
          </div>
          {% endif %}
          <input class="input"
          type="text"
          name="name"
          value="{{name}}" />
          </div>
          <div class="field">
          <label class="label" for="message">
          Message
          </label>
          {% if errors.message %}
          <div class="notification is-danger">
          {{errors.message|join}}
          </div>
          {% endif %}
          <textarea
          class="textarea"
          name="message">{{message}}</textarea>
          </div>
          <input type="submit"
          class="button is-primary"
          value="comment" />
          </form>
          </div>
          </div>
      </div>
    </div>
  </div>
</div>
{% endblock %}

```

crear el .jar

```sh
lein uberjar
```

en el dev-config.edn estan las variables que se ejecutan en el server, hay que pasar la de la db al entorno del SO y correr

```sh
export DATABASE_URL="jdbc:sqlite:guestbook_dev.db"

java -jar target/uberjar/guestbook.jar
```

## ClojureScript

```clj
lein new figwheel figwheel-project
lein  figwheel ; correr con
```

crear en resources un html

```html
<html>
<body>
    <script src="js/compiled/figwheel_project.js" type="text/javascript"></script>
</body>
</html>

```

```clj
;; en la consola podemos correr codigo que se vera en las paginas que cargue el js
(js/alert "Hi from Figwheel Again!")
```
