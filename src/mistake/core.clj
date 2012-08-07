(ns mistake.core
  (:use [mistake io render]))

(def config
  {:layout-dir "layouts/"
   :site-dir "site/"
   :public-dir "public/"
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
      (handler (assoc :val {})))))

(defn prepare-templates
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler
        (assoc req :templates (into {} (map
                                (fn [[k v]] [k (doc-template k v)])
                                (doc-map (file-map (:template-dir config))))))))))

(defn process-site
  [handler & [opts]]
  (fn [req]
    (let [config (:config req)]
      (handler (assoc req :site-pages
                 (into {} (map (fn [[k v]] [k (doc-template k v)])
                   (doc-map (file-map (:site-dir config))))))
        ))))