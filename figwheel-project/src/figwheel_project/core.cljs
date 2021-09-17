; (ns figwheel-project.core
;     (:require ))

; (enable-console-print!)

; (println "This text is printed from src/figwheel-project/core.cljs. Go ahead and edit it and see reloading in action.")

; ;; define your app data so that it doesn't get over-written on reload

; (defonce app-state (atom {:text "Hello world!"}))


; (defn on-js-reload []
;   ;; optionally touch your app-state to force rerendering depending on
;   ;; your application
;   ;; (swap! app-state update-in [:__figwheel_counter] inc)
; )

(ns figwheel-project.core
  (:use [jayq.core :only [$ css html]]))
;;   (:require [jayq.core :as jayq]))
  

; :only [$ css html]]))
;; (def inc2 (fn [x] (+ x 1)))
;; (js/alert (str (type "A String")))
;; (js/alert (inc2 9))
;; (js/console.log "I am a side effect")
;; (js/console.log (str(type "A String")))
;; (js/alert (type nil))
;; (js/alert (first (quote (+ 1 2))))
;; (js/alert {:name "David" :age 28})

;; (ns raw-dom.core)
(def cnt-holder (.getElementById js/document "clicks"))
(def reset-btn (.getElementById js/document "reset-btn"))
(def cnt (atom 0))
 (def $some-div ($ :#some-div))


(defn inc-clicks!
  []
  (set! (.-innerHTML cnt-holder) (swap! cnt inc)))
(defn reset-clicks!
  []
  (set! (.-innerHTML cnt-holder) (reset! cnt -1))
  (-> $some-div
    ;;   (jayq/css {:background "cyan"})
    ;;   (jayq/html "changed Inner HTML")))
      (css {:background "red"})
      (html "a")))

(set! (.-onclick reset-btn) reset-clicks!)


;;  usando jquery
;; (def $some-div (jayq/$ :#some-div))
(defn change-the-div!
  []
  (inc-clicks!)
  (-> $some-div
    ;;   (jayq/css {:background "cyan"})
    ;;   (jayq/html "changed Inner HTML")))
      (css {:background "cyan"})
      (html "b")))
;; (apply map (set! (.-onclick js/document)) [inc-clicks! change-the-div!])
;; (set! (.-onclick js/document) inc-clicks! )
(set! (.-onmousedown js/document) change-the-div!)