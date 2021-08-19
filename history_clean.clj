; (1)
; ("1")
; 2
(+ 3 4)
(+ 4 4 )
(+ 2 2 )
; ("22")
; ("a")
3
(+ 3 4 )
(+ 8888888888888888888888888888888888 00000000000000000000000000444444444444444444444444444444444444444444444444444000000)
; (def hello-world[] (println "hello))
; (defn hello-world[] (println "hello))
(defn hello-world[] (println "hello"))
(hello-world )
(defn say-wlcome[what]
  (println "welcome" what))
(say-wlcome "aa")
(defn average [a b]
(/ (+ a b ) 2)
)
(average 5 10)
(average 5 10.0)
(average 5 ,10.0)
(average 5,10.0)
(average,5,10.0)
(defn chatty-average [a b ]
  (println "average")
  (println "*first" a)
  (println "*second* " b)
  (/ (+ a b) 2)
)
(chatty-average 10,20)
(+ 8 8)
; (load-file "repl-history.clj")
(vector true 2 "asd " 5)
(vector)
(def novels ["emma" "coma" "war and peace"])
(first novels )
(rest novels)
; (rest(rest(novels))
; )
(rest(rest novels ))
(novels 2)
(novels 0)
(nth novels 2)
(nth novels 1)
(conj novels "carrie")
(cons novels "a")
(cons "a " novels )
'(1 2 3)
(hash-map "title" "Oliver Twist"
"author" "Dickens"
"published" 1838)
(def book
(hash-map "title" "Oliver Twist"
"author" "Dickens"
"published" 1838)
)
book
(get book "title")
(book "title")
(def book {:title "oliver" :author "dickens" :pub 1838})
(println "title " (book :title))
(println "title " (:title book))
(assoc book :page-count 362 :title "War & Peace")
book
; (b (assoc book :page-count 362 :title "War & Peace"))
(def b (assoc book :page-count 362 :title "War & Peace"))
b
(def c (dissoc book :page-count 362 ))
c
(def c (dissoc book :pub ))
c
(:keys book)
(:keys c)
(keys c)
(keys book)
(vals book)
(def authors #{"Dickens" "Austen" "King"})
authors 
