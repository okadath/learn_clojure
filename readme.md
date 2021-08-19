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
```clj
(load-file "history_clean.clj")
```

el repl-history es todo el de lein asi que hay que copiarlo(x q se recarga) y limpiarlo de errores antes de cargarlo