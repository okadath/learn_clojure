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

  (log/info (db/get-messages))
  (layout/render request "home.html"
                 (merge {:messages (db/get-messages)}
                        (select-keys flash [:name :message :errors]))))

(def message-schema
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
  (first (st/validate params message-schema)))


(defn save-message! [{:keys [params]}]
  (if-let [errors (validate-message params)]
    (-> (response/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/save-message! params)
      (response/found "/"))))

(defn about-page [request]
  (layout/render request "about.html"))



(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/message" {:post save-message!}]])

