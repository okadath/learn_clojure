; (1)
; ("1")
; 2
(+ 3 4)
(+ 4 4)
(+ 2 2)
; ("22")
; ("a")
3
(+ 3 4)
(+ 8888888888888888888888888888888888 00000000000000000000000000444444444444444444444444444444444444444444444444444000000)
; (def hello-world[] (println "hello))
; (defn hello-world[] (println "hello))
(defn hello-world [] (println "hello"))
(hello-world)
(defn say-wlcome [what]
  (println "welcome" what))
(say-wlcome "aa")
(defn average [a b]
  (/ (+ a b) 2))
(average 5 10)
(average 5 10.0)
(average 5 ,10.0)
(average 5,10.0)
(average,5,10.0)
(defn chatty-average [a b]
  (println "average")
  (println "*first" a)
  (println "*second* " b)
  (/ (+ a b) 2))
(chatty-average 10,20)
(+ 8 8)
; (load-file "repl-history.clj")
(vector true 2 "asd " 5)
(vector)
(def novels ["emma" "coma" "war and peace"])
(first novels)
(rest novels)
; (rest(rest(novels))
; )
(rest (rest novels))
(novels 2)
(novels 0)
(nth novels 2)
(nth novels 1)
(conj novels "carrie")
(cons novels "a")
(cons "a " novels)
'(1 2 3)
(hash-map "title" "Oliver Twist"
          "author" "Dickens"
          "published" 1838)
(def book
  (hash-map "title" "Oliver Twist"
            "author" "Dickens"
            "published" 1838))
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
(def c (dissoc book :page-count 362))
c
(def c (dissoc book :pub))
c
(:keys book)
(:keys c)
(keys c)
(keys book)
(vals book)
(def authors #{"Dickens" "Austen" "King"})
authors

(def book
  {:title "Oliver Twist"
   :author "Dickens"
   :published 1838})
book
(book :title)
(book "title")
(first book)
(rest book)
(count book)
(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back to Blotts Books!")
    (println "Welcome to Blotts Books!")))
(print-greeting "a")
; (print-greeting )
; (print-greeting  )
(print-greeting  ())
(= 1 1)
(= 2 (+ 1 1))
(= "Anna Karenina" "Jane Eyre")
(= "Emma" "Emma")
(= (+ 2 2) 4 (/ 40 10) (* 2 2) (- 5 1))
(= 2 2 2 2 3 2 2 2 2 2)
(= 2 2 2 2 2 2 2 2 2 2)
(not= "Anna Karenina" "Jane Eyre")
(number? 1984)
(number? "Anna Karenina")
(string? "Anna Karenina")
(keyword? "Anna Karenina")
(keyword? :anna-karenina)
(map? :anna-karenina)
(map? {:title 1984})
(vector? 1984)
(vector? [1984])
(defn shipping-charge [preferred-customer order-amount]
  (if preferred-customer
    (do
      (println "Preferred customer, free shipping!")
      0.0)
    (do
      (println "Regular customer, charge them for shipping.")
      (* order-amount 0.10))))
; (defn customer-greeting [status]
; (case status
; :gold
; "Welcome, welcome, welcome back!!!"
; :preferred "Welcome back!"
; "Welcome to Blotts Books"))
; (shipping-charge :gold)
; (shipping-charge [:gold "a"] )
; (customer-greeting [])
; (customer-greeting [:gold])
; (customer-greeting [:gold "a"])
; (customer-greeting :gold )
; (customer-greeting :prefered )
; (shipping-charge [nil 8] )
; (shipping-charge (nil 8) )
; (shipping-charge (8) )
; (shipping-charge (8 8) )
; (shipping-charge [8 8] )
; shipping-charge
(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back to Blotts Books!")
    (println "Welcome to Blotts Books!")))
(print-greeting 7)
; (print-greeting  )
(print-greeting  nil)
(defn publish-book [book]
  (when (not (:title book))
    (throw
     (ex-info "A book needs a title!" {:book book})))
;; Lots of publishing stuff...
  )
; (publish-book {:a "a"}
; )
(publish-book {:title "a"})
; (publish-book {:titl "a"})
(defn greet
  ([to-whom] (println "Welcome to Blotts Books" to-whom))
  ([message to-whom] (println message to-whom)))
(greet "mensj" "name")
(greet "mensj")
(defn multi-average
  "Return the average of 2 or 3 numbers."
  ([a b]
   (/ (+ a b) 2.0))
  ([a b c]
   (/ (+ a b c) 3.0)))
(doc multi-average)
; (= x)
; (x = x)
; ( = x x)
; (= x x)
; (= 6+)
(= 6)
(def dracula {:title "Dracula"
              :author "Stoker"
              :price 1.99
              :genre :horror})
(defn run-with-dracula [f]
  (f dracula))
(defn pricey? [book]
  (when (> (:price book) 9.99)
    book))
(run-with-dracula pricey?)
(pricey? dracula)
(defn horror? [book]
  (when (= (:genre book) :horror)
    book))
(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))
(defn both? [first-predicate-f second-predicate-f book]
  (when (and (first-predicate-f book)
             (second-predicate-f book))
    book))
(both? cheap? horror? dracula)
; (both? pricey? dracula) ; Nope!
; (both? pricey? horror? dracula) ; Nope!
(both? pricey? horror? dracula)
(println "A function:" (fn [n] (* 2 n)))
; (println "A function:" (fn [5] (* 2 n)))
; (println "A function:" (fn [5] (* 2 6)))
(println "A function:" (fn [n] (* 2 n) 3))
; ((fn [n] (* 2 n) 3))
(println "A function:" (fn [n] (* 2 n)) 3)
((fn [n] (* 2 n)) 5)
(def the-function +)
(def args [1 2 3 4])
(apply the-function args)
(def v ["The number " 2 " best selling " "book."])
(apply str v)
(apply list v)
(apply vector (apply list v))
#(* 2 %11)
(defn mk-discount-price-f [user-name user-discounts min-charge]
  (let [discount-percent (user-discounts user-name)]
    (fn [amount]
      (let [discount (* amount discount-percent)
            discounted-amount (- amount discount)]
        (if (> discounted-amount min-charge)
          discounted-amount
          min-charge)))))
; (def compute-felicia-price (mk-discount-price-f "Felicia" user-discounts 10.0))
(def user-discounts
  {"Nicholas" 0.10 "Jonathan" 0.07 "Felicia" 0.05})
(def compute-felicia-price (mk-discount-price-f "Felicia" user-discounts 10.0))
(compute-felicia-price 20.0)
