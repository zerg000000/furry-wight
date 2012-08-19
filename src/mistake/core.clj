(ns mistake.core
  (:use [mistake io render]))

(def config
  {:layouts-dir "layouts/"
   :matters-dir "matters/"
   :resources-dir "resources/"
   :posts-dir "posts/"
   :published-dir "published/"})

(defn prepare-config
  [handler & [opts]]
  (fn [req]
    (handler (assoc req :config config))))

(defn prepare-constants
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler (assoc req :site {:time (java.util.Date.)})))))

(defn prepare-templates
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler
        (assoc req :layouts (into {} (map
                                (fn [[k v]] [k (doc-template k v)])
                                (doc-map (file-map (:layouts-dir config))))))))))

(defn process-matters
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler (assoc req :matters
                 (into {} (map (fn [[k v]] [k (doc-template k v)])
                   (doc-map (file-map (:matters-dir config))))))
        ))))

;(defn process-pages
;  [handler & [opts]]
;  (fn [req]
;    (let [config (:config req)
;          post {:url (make-url doc)
;                :date (make-date doc)
;                :id (make-id doc)
;                :categories (make-categories doc)
;                :tags (make-tags doc)
;                :content (make-content doc)}]
;      (handler req))))
;
;(defn generate-posts
;  [handler & [opts]]
;  (fn [req]
;    (let [config (:config req)]
;      ()
;      (handler req))))