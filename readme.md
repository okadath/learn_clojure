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