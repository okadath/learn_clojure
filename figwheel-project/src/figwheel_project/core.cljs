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

(ns figwheel-project.core)
(def inc2 (fn [x] (+ x 1)))
(js/alert (str (type "A String")))
(js/alert (inc2 9))
(js/console.log "I am a side effect")
(js/console.log (str(type "A String")))
(js/alert (type nil))
(js/alert (first (quote (+ 1 2))))
(js/alert {:name "David" :age 28})